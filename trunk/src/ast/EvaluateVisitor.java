package ast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import exceptions.NoSuchExecutorException;
import exceptions.NoSuchPluginException;

import plugin.IPlugin;
import plugin.IPluginManager;
import plugin.PluginFactory;
import plugin.PluginManager;

public class EvaluateVisitor implements IVisitor {
    private Object value;
    private Map<String, Object> context;
    private IPluginManager manager;
    private Map<BinaryOperatorType, AbstractBinaryOperationExecutor> binaryExecutors;
    private Map<UnaryOperatorType, AbstractUnaryOperationExecutor> unaryExecutors;

    public EvaluateVisitor() {
        context = new HashMap<String, Object>();
        PluginManager temp = new PluginManager();
        temp.addFactory(new PluginFactory());
        manager = temp;
        binaryExecutors = new HashMap<BinaryOperatorType, AbstractBinaryOperationExecutor>();
        unaryExecutors = new HashMap<UnaryOperatorType, AbstractUnaryOperationExecutor>();
        setUp();
    }

    public AbstractBinaryOperationExecutor findBinaryExecutor(
            BinaryOperatorType operator) throws Exception {
        AbstractBinaryOperationExecutor executor = binaryExecutors
                .get(operator);
        if (executor == null) {
            throw new NoSuchExecutorException();
        }
        return executor;
    }

    public AbstractUnaryOperationExecutor findUnaryExecutor(
            UnaryOperatorType operator) throws Exception {
        AbstractUnaryOperationExecutor executor = unaryExecutors.get(operator);
        if (executor == null) {
            throw new NoSuchExecutorException();
        }
        return executor;
    }

    public Object getValue() {
        return this.value;
    }

    public Map<String, Object> getContext() {
        return this.context;
    }

    public void printContext() {
        for (String key : context.keySet()) {
            System.out.println(key + "-->" + context.get(key));
        }
    }

    @Override
    public void visit(INode node) {
        // TODO Auto-generated method stub

    }

    @Override
    public void visit(IExpressionNode node) {
        // TODO Auto-generated method stub

    }

    @Override
    public void visit(CommentNode node) {
        // TODO Auto-generated method stub

    }

    @Override
    public void visit(GroupNode node) {
        INodeIterator iterator = node.getChildrenIterator();
        while (!iterator.isDone()) {
            INode child = iterator.currentItem();
            child.accept(this);
            iterator.next();
        }

    }

    @Override
    public void visit(ImportNode node) {
        try {
            manager.importPlugin(node.getAlias(), node.getPluginName());
            value = true;
        } catch (Exception e) {
            value = false;
        }

    }

    @Override
    public void visit(LiteralNode node) {
        value = node.getValue();

    }

    @Override
    public void visit(VariableNode node) {
        Object temp = this.context.get(node.getIdentifier());
        if (temp == null) {
            throw new RuntimeException();
        }
        this.value = temp;

    }

    @Override
    public void visit(BinaryOperatorNode node) {
        node.getLeftOperand().accept(this);
        Object left = value;
        node.getRightOperand().accept(this);
        Object right = value;
        BinaryOperatorType operator = node.getOperator();
        if (operator.equals(BinaryOperatorType.BASIC_ASSIGMENT)) {
            context.put(left.toString(), right);
        } else {
            try {
                AbstractBinaryOperationExecutor executor = findBinaryExecutor(operator);
                value = executor.execute(left, right);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    @Override
    public void visit(UnaryOperatorNode node) {
        node.getOperand().accept(this);
        Object operand = value;
        UnaryOperatorType operator = node.getOperator();
        try {
            AbstractUnaryOperationExecutor executor = findUnaryExecutor(operator);
            value = executor.execute(operand);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void visit(IfNode node) {
        Map<IExpressionNode, GroupNode> ifs = node.getIfs();
        for (IExpressionNode key : ifs.keySet()) {
            key.accept(this);
            if (this.value.equals(true)) {
                ifs.get(key).accept(this);
                break;
            } else {
                node.getElseExpression().accept(this);
            }
        }
    }

    @Override
    public void visit(InvocationNode node) {
        try {
            List<IExpressionNode> exprs = node.getParams();
            Object[] args = new Object[exprs.size()];
            for (int i = 0; i < exprs.size(); i++) {
                exprs.get(i).accept(this);
                args[i] = this.value;
            }
            IPlugin module = manager.getPlugin(node.getPluginAlias());
            this.value = module.callFunction(node.getMethod(), args);
        } catch (NoSuchPluginException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void setUp() {
        binaryExecutors
                .put(BinaryOperatorType.ADDITION, new AdditionExecutor());
        binaryExecutors.put(BinaryOperatorType.SUBSTRACTION,
                new SubtractionExecutor());
        binaryExecutors.put(BinaryOperatorType.MULTIPLICATION,
                new MultiplicationExecutor());
        binaryExecutors
                .put(BinaryOperatorType.DIVISION, new DivisionExecutor());
        binaryExecutors
                .put(BinaryOperatorType.EQUAL__TO, new EqualToExecutor());
        binaryExecutors.put(BinaryOperatorType.GREATER_THAN,
                new GreaterThanExecutor());
        binaryExecutors.put(BinaryOperatorType.GREATER_THAN_OR_EQUAL_TO,
                new GreaterThanOrEqualToExecutor());
        binaryExecutors.put(BinaryOperatorType.LESS_THAN,
                new LessThanExecutor());
        binaryExecutors.put(BinaryOperatorType.LESS_THAN_OR_EQUAL_TO,
                new LessThanOrEqualToExecutor());
        binaryExecutors.put(BinaryOperatorType.NOT_EQUAL_TO,
                new NotEqualToExecutor());

        unaryExecutors
                .put(UnaryOperatorType.DECREMENT, new DecrementExecutor());
        unaryExecutors
                .put(UnaryOperatorType.INCREMENT, new IncrementExecutor());
        unaryExecutors.put(UnaryOperatorType.LOGICAL_NEGATION,
                new LogicalNegationExecutor());
        unaryExecutors.put(UnaryOperatorType.UNARY_MINUS,
                new MinusExecutor());
    }
}
