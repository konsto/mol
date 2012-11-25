package engine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import ast.AssigmentNode;
import ast.BinaryOperatorNode;
import ast.BinaryOperatorType;
import ast.CommentNode;
import ast.GroupNode;
import ast.IExpressionNode;
import ast.INode;
import ast.INodeIterator;
import ast.IVisitor;
import ast.IfNode;
import ast.ImportNode;
import ast.InvocationNode;
import ast.LiteralNode;
import ast.UnaryOperatorNode;
import ast.UnaryOperatorType;
import ast.UserObjectMethodInvocationNode;
import ast.VariableNode;

import exceptions.NoSuchEvaluatorException;
import exceptions.NoSuchPluginException;

import plugin.IPlugin;
import plugin.IPluginManager;
import plugin.PluginFactory;
import plugin.PluginManager;

public class EvaluateVisitor implements IVisitor {
    private IObject value;
    private Map<String, IObject> context;
    private IPluginManager manager;
    private Map<BinaryOperatorType, IBinaryEvaluator> binaryEvaluators;
    private Map<UnaryOperatorType, IUnaryEvaluator> unaryEvaluators;

    public EvaluateVisitor() {
        context = new HashMap<String, IObject>();
        PluginManager temp = new PluginManager();
        temp.addFactory(new PluginFactory());
        manager = temp;
        binaryEvaluators = new HashMap<BinaryOperatorType, IBinaryEvaluator>();
        unaryEvaluators = new HashMap<UnaryOperatorType, IUnaryEvaluator>();
        setUp();
    }

    public IObject getContextValue(String key) {
        if (context.containsKey(key)) {
            return context.get(key);
        } else {
            throw new NoSuchElementException();
        }
    }

    public IBinaryEvaluator findBinaryEvaluator(BinaryOperatorType operator)
            throws Exception {
        IBinaryEvaluator evaluator = binaryEvaluators.get(operator);
        if (evaluator == null) {
            throw new NoSuchEvaluatorException();
        }
        return evaluator;
    }

    public IUnaryEvaluator findUnaryEvaluator(UnaryOperatorType operator)
            throws Exception {
        IUnaryEvaluator evaluator = unaryEvaluators.get(operator);
        if (evaluator == null) {
            throw new NoSuchEvaluatorException();
        }
        return evaluator;
    }

    public IObject getValue() {
        return this.value;
    }

    public Map<String, IObject> getContext() {
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
            value = new ObjectWrapper(true);
        } catch (Exception e) {
            value = new ObjectWrapper(false);
        }

    }

    @Override
    public void visit(LiteralNode node) {
        value = new ObjectWrapper(node.getValue());

    }

    @Override
    public void visit(VariableNode node) {
        IObject temp = this.context.get(node.getIdentifier());
        if (temp == null) {
            throw new RuntimeException();
        }
        this.value = new ObjectWrapper(temp);

    }

    @Override
    public void visit(BinaryOperatorNode node) {
        node.getLeftOperand().accept(this);
        IObject left = value;
        node.getRightOperand().accept(this);
        IObject right = value;
        BinaryOperatorType operator = node.getOperator();
        try {
            IBinaryEvaluator evaluator = findBinaryEvaluator(operator);
            value = evaluator.evaluate(left, right);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void visit(UnaryOperatorNode node) {
        node.getOperand().accept(this);
        IObject operand = value;
        UnaryOperatorType operator = node.getOperator();
        try {
            IUnaryEvaluator evaluator = findUnaryEvaluator(operator);
            value = evaluator.evaluate(operand);
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

    // TODO: INVOCATIONNODE!!!

    @Override
    public void visit(InvocationNode node) {
        // try {
        // List<IExpressionNode> exprs = node.getParams();
        // Object[] args = new Object[exprs.size()];
        // for (int i = 0; i < exprs.size(); i++) {
        // exprs.get(i).accept(this);
        // args[i] = this.value;
        // }
        // IPlugin module = manager.getPlugin(node.getPluginAlias());
        // this.value = module.callFunction(node.getMethod(), args);
        // } catch (NoSuchPluginException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // } catch (NoSuchMethodException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }
    }

    // TODO: wszystko co wrzucam do contextu opakkowuej w IOBJECT, Ktory ma
    // invokeMethod
    // @Override
    // public void visit(UserObjectMethodInvocationNode node) {
    // List<IExpressionNode> exprs = node.getParams();
    // Object[] args = new Object[exprs.size()];
    // for (int i = 0; i < exprs.size(); i++) {
    // exprs.get(i).accept(this);
    // args[i] = this.value;
    // }
    // UserObject instance = (UserObject) this.context.get(node
    // .getObjectVariable());
    // try {
    // // TODO: zmienic callFunction na invokeMethod
    // this.value = instance.callFunction(node.getMethod(), args);
    // } catch (NoSuchMethodException e) {
    // // TODO Auto-generated catch block
    // e.printStackTrace();
    // }
    //
    // }

    private void setUp() {
        binaryEvaluators.put(BinaryOperatorType.ADDITION,
                new AdditionEvaluator());
        binaryEvaluators.put(BinaryOperatorType.SUBSTRACTION,
                new SubtractionEvaluator());
        binaryEvaluators.put(BinaryOperatorType.MULTIPLICATION,
                new MultiplicationEvaluator());
        binaryEvaluators.put(BinaryOperatorType.DIVISION,
                new DivisionEvaluator());
        binaryEvaluators.put(BinaryOperatorType.EQUAL__TO,
                new EqualToEvaluator());
        binaryEvaluators.put(BinaryOperatorType.GREATER_THAN,
                new GreaterThanEvaluator());
        binaryEvaluators.put(BinaryOperatorType.GREATER_THAN_OR_EQUAL_TO,
                new GreaterThanOrEqualToEvaluator());
        binaryEvaluators.put(BinaryOperatorType.LESS_THAN,
                new LessThanEvaluator());
        binaryEvaluators.put(BinaryOperatorType.LESS_THAN_OR_EQUAL_TO,
                new LessThanOrEqualToEvaluator());
        binaryEvaluators.put(BinaryOperatorType.NOT_EQUAL_TO,
                new NotEqualToEvaluator());
        unaryEvaluators
                .put(UnaryOperatorType.NEGATION, new NegationEvaluator());
        unaryEvaluators.put(UnaryOperatorType.MINUS, new MinusEvaluator());
    }

    @Override
    public void visit(AssigmentNode node) {
        String identifier = node.getIdentifier();
        node.getExpression().accept(this);
        IObject expression = value;
        context.put(identifier, expression);
        // TODO: nie wiem czy tu dobrze: identifier.toString()
    }
}
