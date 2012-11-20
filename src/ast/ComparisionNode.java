package ast;

public class ComparisionNode implements IExpressionNode {
    
    private IExpressionNode left;
    private IExpressionNode right;
    
    public ComparisionNode(IExpressionNode left, IExpressionNode right) {
        this.left = left;
        this.right = right;
    }
    
    public IExpressionNode getLeft() {
        return this.left;
    }
    
    public IExpressionNode getRight() {
        return this.right;
    }
    
    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);

    }

}