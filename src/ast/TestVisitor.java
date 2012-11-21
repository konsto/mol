package ast;

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
}
