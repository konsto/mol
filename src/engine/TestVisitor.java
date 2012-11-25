package engine;

import ast.AssigmentNode;
import ast.BinaryOperatorNode;
import ast.CommentNode;
import ast.GroupNode;
import ast.IExpressionNode;
import ast.INode;
import ast.IVisitor;
import ast.IfNode;
import ast.ImportNode;
import ast.InvocationNode;
import ast.LiteralNode;
import ast.UnaryOperatorNode;
import ast.UserObjectMethodInvocationNode;
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
    public void visit(LiteralNode node) {
        this.message = "LiteralNode";
    }

    @Override
    public void visit(VariableNode node) {
        this.message = "VariableNode";
    }

    @Override
    public void visit(BinaryOperatorNode node) {
        this.message = "BinaryOperatorNode";
    }

    @Override
    public void visit(UnaryOperatorNode node) {
        this.message = "UnaryOperatorNode";
    }

    @Override
    public void visit(IfNode node) {
        this.message = "IfNode";

    }

    @Override
    public void visit(InvocationNode node) {
        this.message = "InvocationNode";

    }

    @Override
    public void visit(AssigmentNode node) {
        // TODO Auto-generated method stub

    }
}
