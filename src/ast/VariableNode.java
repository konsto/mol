package ast;

public class VariableNode implements IExpressionNode {

    private String identifier;

    public VariableNode(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return this.identifier;
    }

}
