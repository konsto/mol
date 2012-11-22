package ast;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

import org.hamcrest.core.IsInstanceOf;

import exceptions.NoSuchPluginException;
import plugin.IPlugin;
import plugin.IPluginManager;
import plugin.PluginFactory;
import plugin.PluginManager;

public class EvaluateVisitor implements IVisitor {

    private Object value;
    private Map<String, Object> context;
    private IPluginManager manager;
    private OperationExecutor binaryExecutor;
    private OperationExecutor unaryExecutor;

    public EvaluateVisitor() {
        context = new HashMap<String, Object>();
        PluginManager temp = new PluginManager();
        temp.addFactory(new PluginFactory());
        manager = temp;
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
        Object left = null;
        Object right = null;
        BinaryOperatorType operator = node.getOperator();
        BinaryOperationExecutor executor = new BinaryOperationExecutor();
        switch (operator) {
        case ADDITION:
            node.getLeftOperand().accept(this);
            left = value;
            node.getRightOperand().accept(this);
            right = value;
            executor.set(left, right, operator);
            try {
                value = executor.executeOperation();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } 
//            if (Number.class.isAssignableFrom(left.getClass())
//                    && Number.class.isAssignableFrom(right.getClass())) {
//                value = (int) left + (int) right;
//            }
            break;
        case SUBSTRACTION:
            node.getLeftOperand().accept(this);
            left = value;
            node.getRightOperand().accept(this);
            right = value;
            executor.set(left, right, operator);
            try {
                value = executor.executeOperation();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } 
        case BASIC_ASSIGMENT:
            node.getLeftOperand().accept(this);
            left = value;
            node.getRightOperand().accept(this);
            right = value;
            System.out.println(right.getClass() + " OOO " + right);
            context.put(left.toString(), right);
            break;
//        case EQUAL__TO:
//            node.getLeftOperand().accept(this);
//            left = value;
//            node.getRightOperand().accept(this);
//            right = value;
//            if (left.equals(right)) {
//                value = true;
//            } else {
//                value = false;
//            }
//            break;
//        case MULTIPLICATION:
//            node.getLeftOperand().accept(this);
//            left = value;
//            node.getRightOperand().accept(this);
//            right = value;
//            value = (int) left * (int) right;
//            break;
//        case DIVISION:
//            node.getLeftOperand().accept(this);
//            left = value;
//            node.getRightOperand().accept(this);
//            right = value;
//            value = (int) left / (int) right;
//            break;
        default:
            value = null;
            break;
        }
    }

    @Override
    public void visit(UnaryOperatorNode node) {
        UnaryOperatorType operator = node.getOperator();
        int operand;

        switch (operator) {
        case INCREMENT:
            node.getOperand().accept(this);
            operand = (int) value;
            operand++;
            value = operand;
            break;
        // TODO: check if this is used properly
        case BITWISE_NOT:
            node.getOperand().accept(this);
            value = ~(Integer) value;
            break;
        case DECREMENT:
            node.getOperand().accept(this);
            operand = (int) value;
            operand--;
            value = operand;
            break;
        case LOGICAL_NEGATION:
            node.getOperand().accept(this);
            value = !(boolean) value;
            break;
        case UNARY_MINUS:
            node.getOperand().accept(this);
            operand = (int) value;
            operand = -operand;
            value = operand;
            break;
        case UNARY_PLUS:
            node.getOperand().accept(this);
            operand = (int) value;
            operand = +operand;
            value = operand;
            break;
        default:
            break;

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
}
