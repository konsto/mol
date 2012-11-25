package ast;

public interface IVisitor {

    public void visit(AssigmentNode node) throws Exception;
    
    public void visit(INode node) throws Exception;

    public void visit(IExpressionNode node) throws Exception;

    public void visit(CommentNode node) throws Exception;

    public void visit(GroupNode node) throws Exception;

    public void visit(ImportNode node) throws Exception;

    public void visit(LiteralNode node) throws Exception;

    public void visit(VariableNode node) throws Exception;

    public void visit(BinaryOperatorNode node) throws Exception;

    public void visit(UnaryOperatorNode node) throws Exception;
    
    public void visit(IfNode node) throws Exception;
    
    public void visit(InvocationNode node) throws Exception;
    
    public void visit(WhileNode node) throws Exception;
    
    public void visit(ForNode node) throws Exception;


}
