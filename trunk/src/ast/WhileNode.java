package ast;

public class WhileNode implements INode {
    GroupNode codeBlock;
    IExpressionNode condition;

    public WhileNode(IExpressionNode condition, GroupNode codeBlock) {
        this.codeBlock = codeBlock;
        this.condition = condition;
    }

    public IExpressionNode getCondition() {
        return this.condition;
    }

    public GroupNode getCodeBlock() {
        return this.codeBlock;
    }

    @Override
    public void accept(IVisitor visitor) throws Exception {
        visitor.visit(this);

    }

}
