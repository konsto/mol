package ast;

public class LiteralNode implements IExpressionNode {

    private Object value;

    public LiteralNode(Object value) {
        if (value != null) {
            this.value = value;
        } else {
            throw new NullPointerException();
        }
    }

    public Object getValue() {
        return this.value;
    }
    
    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);
        
    }
}
