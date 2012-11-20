package unitTests;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.ldap.ManageReferralControl;

import exceptions.AliasAlreadyExistsException;
import exceptions.NoFactoryException;
import exceptions.NoSuchPluginException;

import plugin.IPlugin;
import plugin.IPluginManager;
import plugin.PluginFactory;
import plugin.PluginManager;

import ast.AdditionNode;
import ast.AssignmentNode;
import ast.CommentNode;
import ast.ComparisionNode;
import ast.GroupNode;
import ast.IExpressionNode;
import ast.INode;
import ast.INodeIterator;
import ast.IVisitor;
import ast.IfNode;
import ast.ImportNode;
import ast.InvocationNode;
import ast.LiteralNode;
import ast.MultiplicationNode;
import ast.VariableNode;

public class ToyEvaluateVisitor implements IVisitor {

    private Map<String, Object> context;

    private Object value;

    private IPluginManager manager;

    public ToyEvaluateVisitor() {
        PluginManager temp = new PluginManager();
        temp.addFactory(new PluginFactory());
        manager = temp;
        context = new HashMap<String, Object>();
    }

    public void printContext() {
        for (String key : context.keySet()) {
            System.out.format("%s --> %s%n", key, context.get(key));
        }
    }

    public Object getContextElement(String key) {
        return context.get(key);
    }

    public Object getValue() {
        return this.value;
    }

    public Map<String, Object> getContext() {
        return this.context;
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
    public void visit(AssignmentNode node) {
        node.getValue().accept(this);
        this.context.put(node.getIdentifier(), this.value);
    }

    @Override
    public void visit(CommentNode node) {
        // TODO Auto-generated method stub

    }

    @Override
    public void visit(GroupNode node) {
        INodeIterator iter = node.getChildrenIterator();
        while (!iter.isDone()) {
            INode child = iter.currentItem();
            child.accept(this);
            iter.next();
        }
    }

    @Override
    public void visit(ImportNode node) {
        context.put(node.getAlias(), node.getPluginName());
        try {
            manager.importPlugin(node.getAlias(), node.getPluginName());
            this.value = true;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
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

    @Override
    public void visit(LiteralNode node) {
        this.value = node.getValue();
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
    public void visit(AdditionNode node) {
        node.getLeft().accept(this);
        Object left = this.value;
        node.getRight().accept(this);
        Object right = this.value;
        this.value = (Integer) left + (Integer) right;
    }

    public void visit(ComparisionNode node) {
        node.getLeft().accept(this);
        Object left = this.value;
        node.getRight().accept(this);
        Object right = this.value;
        this.value = (left == right);
    }

    @Override
    public void visit(MultiplicationNode node) {
        node.getLeft().accept(this);
        Object left = this.value;
        node.getRight().accept(this);
        Object right = this.value;
        this.value = (Integer) left * (Integer) right;
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
}
