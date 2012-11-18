package ast;

public interface IVisitor {

    public void visit(AssignmentNode node);

    public void visit(CommentNode node);

    public void visit(GroupNode node);

    public void visit(ImportNode node);

    public void visit(InvocationNode node);

    public void visit(LiteralNode node);

    public void visit(VariableNode node);
}
