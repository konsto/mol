package ast;

public class ForNode implements INode {
    GroupNode initialization;
    IExpressionNode condition;
    GroupNode codeBlock;
    GroupNode afterBlock;

    public ForNode(GroupNode initialization, IExpressionNode condition,
            GroupNode codeBlock, GroupNode afterBlock) {
        this.initialization = initialization;
        this.condition = condition;
        this.codeBlock = codeBlock;
        this.afterBlock = afterBlock;
    }

    public GroupNode getAfterBlock() {
        return this.afterBlock;
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
