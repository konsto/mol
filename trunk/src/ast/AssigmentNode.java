package ast;

public class AssigmentNode implements INode {
    private String identifier;
    private IExpressionNode expression;

    public AssigmentNode(String ident, IExpressionNode content) {
        this.identifier = ident;
        this.expression = content;
    }

    public String getIdentifier() {
        return identifier;
    }

    public IExpressionNode getExpression() {
        return expression;
    }

    @Override
    public void accept(IVisitor visitor) throws Exception {
        visitor.visit(this);

    }

}
