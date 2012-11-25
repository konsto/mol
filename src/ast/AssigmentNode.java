package ast;

public class AssigmentNode implements INode {
    private IExpressionNode identifier;
    private IExpressionNode content;

    public AssigmentNode(IExpressionNode ident, IExpressionNode content) {
        this.identifier = ident;
        this.content = content;
    }

    public IExpressionNode getIdentifier() {
        return identifier;
    }

    public IExpressionNode getContent() {
        return content;
    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);

    }

}
