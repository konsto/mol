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
        this.message = "INode";
    }

    @Override
    public void visit(IExpressionNode node) {
        this.message = "IExpressionNode";
    }

    @Override
    public void visit(AssignmentNode node) {
        this.message = "AssigmentNode";
    }

    @Override
    public void visit(CommentNode node) {
        this.message = "CommentNode";
    }

    @Override
    public void visit(GroupNode node) {
        this.message = "GroupNode";
    }

    @Override
    public void visit(ImportNode node) {
        this.message = "ImportNode";
    }

    @Override
    public void visit(InvocationNode node) {
        this.message = "InvocationNode";
    }

    @Override
    public void visit(LiteralNode node) {
        this.message = "LiteralNode";
    }

    @Override
    public void visit(VariableNode node) {
        this.message = "VariableNode";
    }

    @Override
    public void visit(AdditionNode node) {
        this.message = "AdditionNode";
    }

    @Override
    public void visit(MultiplicationNode node) {
        this.message = "MultiplicationNode";
    }
    
    @Override
    public void visit(ComparisionNode node) {
        this.message = "ComparisionNode";
    }
    
    @Override
    public void visit(IfNode node) {
        this.message = "IfNode";
    }

}
