package ast;

import java.util.Map;

public class IfNode implements IExpressionNode {

    private Map<ComparisionNode, IExpressionNode> ifs;
    private IExpressionNode elseExpression;

    public IfNode(Map<ComparisionNode, IExpressionNode> ifs,
            IExpressionNode elseExpression) {

        this.ifs = ifs;
        this.elseExpression = elseExpression;
    }

    public Map<ComparisionNode, IExpressionNode> getIfs() {
        return this.ifs;
    }

    public IExpressionNode getElseExpression() {
        return this.elseExpression;
    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);

    }

}
