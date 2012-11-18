package ast;

public class LiteralNode implements IExpressionNode {

    private Object value;

    public LiteralNode(Object value) {
        this.value = value;
    }

    public Object getValue() {
        return this.value;
    }
}
