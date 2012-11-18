package ast;

public class LiteralNode implements IExpressionNode {

    private Object value;

    public LiteralNode(Object value) {
        this.value = value;
    }

    public Object getValue() {
        return this.value;
    }
    
    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);
        
    }
}
