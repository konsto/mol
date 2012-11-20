package unitTests;

import ast.AdditionNode;
import ast.AssignmentNode;
import ast.CommentNode;
import ast.ComparisionNode;
import ast.GroupNode;
import ast.IExpressionNode;
import ast.INode;
import ast.IVisitor;
import ast.IfNode;
import ast.ImportNode;
import ast.InvocationNode;
import ast.LiteralNode;
import ast.MultiplicationNode;
import ast.VariableNode;

public class TestVisitor implements IVisitor {
    public String message;

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
        // TODO Auto-generated method stub

    }

    @Override
    public void visit(CommentNode node) {
        // TODO Auto-generated method stub

    }

    @Override
    public void visit(GroupNode node) {
        // TODO Auto-generated method stub

    }

    @Override
    public void visit(ImportNode node) {
        this.message = "ImportNode";

    }

    @Override
    public void visit(InvocationNode node) {
        // TODO Auto-generated method stub

    }

    @Override
    public void visit(LiteralNode node) {
        // TODO Auto-generated method stub

    }

    @Override
    public void visit(VariableNode node) {
        // TODO Auto-generated method stub

    }

    @Override
    public void visit(AdditionNode node) {
        // TODO Auto-generated method stub

    }

    @Override
    public void visit(MultiplicationNode node) {
        // TODO Auto-generated method stub

    }
    
    @Override
    public void visit(ComparisionNode node) {
        // TODO Auto-generated method stub

    }
    
    @Override
    public void visit(IfNode node) {
        // TODO Auto-generated method stub

    }

}
