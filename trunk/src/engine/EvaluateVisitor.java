package engine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ast.AssigmentNode;
import ast.BinaryOperatorNode;
import ast.BinaryOperatorType;
import ast.CommentNode;
import ast.ForNode;
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
import ast.VariableNode;
import ast.WhileNode;

import exceptions.AliasAlreadyExistsException;
import exceptions.NoSuchEvaluatorException;

public class EvaluateVisitor implements IVisitor {
    private IObject value;
    private Context context;
    private IModuleLoader loader;
    private Map<BinaryOperatorType, IBinaryEvaluator> binaryEvaluators;
    private Map<UnaryOperatorType, IUnaryEvaluator> unaryEvaluators;

    public EvaluateVisitor() {
        context = new Context();
        loader = new ModuleLoader();
        binaryEvaluators = new HashMap<BinaryOperatorType, IBinaryEvaluator>();
        unaryEvaluators = new HashMap<UnaryOperatorType, IUnaryEvaluator>();
        setUp();
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

    public Context getContext() {
        return this.context;
    }

    public void printContext() throws Exception {
        context.print();
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
    public void visit(GroupNode node) throws Exception {
        INodeIterator iterator = node.getChildrenIterator();
        while (!iterator.isDone()) {
            INode child = iterator.currentItem();
            child.accept(this);
            iterator.next();
        }

    }

    @Override
    public void visit(ImportNode node) throws Exception {
        String alias = node.getAlias();
        String moduleName = node.getModuleName();
        ContextEntry entry = null;
        if (context.getEntry(alias) != null) {
            throw new AliasAlreadyExistsException();
        }
        entry = context.getEntry(Class.forName(moduleName));
        if (entry == null) {
            entry = new ContextEntry(loader.load(moduleName), false);
        }
        context.setEntry(alias, entry);
    }

    @Override
    public void visit(LiteralNode node) {
        value = new ObjectWrapper(node.getValue());

    }

    @Override
    public void visit(VariableNode node) {
        ContextEntry entry = context.getEntry(node.getIdentifier());
        if (entry == null) {
            throw new RuntimeException();
        }
        value = entry.getObject();
    }

    @Override
    public void visit(BinaryOperatorNode node) throws Exception {
        node.getLeftOperand().accept(this);
        IObject left = value;
        node.getRightOperand().accept(this);
        IObject right = value;
        BinaryOperatorType operator = node.getOperator();
        IBinaryEvaluator evaluator = findBinaryEvaluator(operator);
        value = evaluator.evaluate(left, right);
    }

    @Override
    public void visit(UnaryOperatorNode node) throws Exception {
        node.getOperand().accept(this);
        IObject operand = value;
        UnaryOperatorType operator = node.getOperator();
        IUnaryEvaluator evaluator = findUnaryEvaluator(operator);
        value = evaluator.evaluate(operand);
    }

    @Override
    public void visit(IfNode node) throws Exception {
        Map<IExpressionNode, GroupNode> ifs = node.getIfs();
        for (IExpressionNode key : ifs.keySet()) {
            //key.accept(this);
            //Boolean condition = (Boolean) value.getValue();
            if (evaluateBoolean(key)) {
                ifs.get(key).accept(this);
                break;
            }
        }
        node.getElseExpression().accept(this);
    }

    @Override
    public void visit(AssigmentNode node) throws Exception {
        String identifier = node.getIdentifier();
        node.getExpression().accept(this);
        IObject expression = value;
        ContextEntry entry = context.getEntry(identifier);
        if (entry != null) {
            if (!entry.isAssignable()) {
                throw new RuntimeException();
            }
        }
        context.setEntry(identifier, new ContextEntry(expression));

    }

    @Override
    public void visit(WhileNode node) throws Exception {
        while (evaluateBoolean(node.getCondition())) {
            node.getCodeBlock().accept(this);
        }
    }

    @Override
    public void visit(InvocationNode node) throws Exception {
        node.getTarget().accept(this);
        IObject target = value;
        value = target.invokeMethod(node.getMethod(),
                evaluateParams(node.getParams()));
    }
    
    @Override
    public void visit(ForNode node) throws Exception {
        node.getInitialization().accept(this);
        while (evaluateBoolean(node.getCondition())) {
            node.getCodeBlock().accept(this);
        }
        
    }


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

    private IObject[] evaluateParams(List<IExpressionNode> exprs)
            throws Exception {
        IObject[] args = new IObject[exprs.size()];
        for (int i = 0; i < exprs.size(); i++) {
            exprs.get(i).accept(this);
            args[i] = this.value;
        }
        return args;
    }

    private Boolean evaluateBoolean(IExpressionNode condition) throws Exception {
        condition.accept(this);
        if (!value.getType().equals(Boolean.class)) {
            throw new RuntimeException();
        }
        Boolean result = (Boolean) value.getValue();
        return result;
    }
}
