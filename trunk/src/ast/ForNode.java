package ast;

public class ForNode implements INode {
    GroupNode initialization;
    IExpressionNode condition;
    GroupNode codeBlock;

    public ForNode(GroupNode initialization, IExpressionNode condition,
            GroupNode codeBlock) {
        this.initialization = initialization;
        this.condition = condition;
        this.codeBlock = codeBlock;
    }

    public GroupNode getInitialization() {
        return initialization;
    }

    public IExpressionNode getCondition() {
        return condition;
    }

    public GroupNode getCodeBlock() {
        return codeBlock;
    }

    @Override
    public void accept(IVisitor visitor) throws Exception {
        visitor.visit(this);

    }

}
