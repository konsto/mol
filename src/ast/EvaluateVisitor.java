package ast;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import exceptions.NoSuchVariableException;

import plugin.IPluginManager;
import plugin.PluginFactory;
import plugin.PluginManager;

public class EvaluateVisitor implements IVisitor {

    private Object value;
    private Map<String, Object> context;
    private IPluginManager manager;

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
        for (String key: context.keySet()) {
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
    public void visit(VariableNode node){
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
        switch (operator){
        case ADDITION: 
            node.getLeftOperand().accept(this);
            left = value;
            node.getRightOperand().accept(this);
            right = value;
            value = (Integer) left + (Integer) right;
            break;
        case SUBSTRACTION:
            node.getLeftOperand().accept(this);
            left = value;
            node.getRightOperand().accept(this);
            right = value;
            value = (Integer) left - (Integer) right;
            break;
        case BASIC_ASSIGMENT:
            node.getLeftOperand().accept(this);
            left = value;
            node.getRightOperand().accept(this);
            right = value;
            context.put((String) left, right);
            
        default:
            value = null;
            break;
                
        }
    }

    @Override
    public void visit(UnaryOperatorNode node) {
        // TODO Auto-generated method stub

    }
}
