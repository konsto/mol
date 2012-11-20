package ast;

public interface IVisitor {
    
    public void visit(INode node);
    
    public void visit(IExpressionNode node);

    public void visit(AssignmentNode node);

    public void visit(CommentNode node);

    public void visit(GroupNode node);

    public void visit(ImportNode node);

    public void visit(InvocationNode node);

    public void visit(LiteralNode node);

    public void visit(VariableNode node);
    
    public void visit(AdditionNode node);
    
    public void visit(MultiplicationNode node);
    
    public void visit(ComparisionNode node);
    
    public void visit(IfNode node);
    
}
