package ast;

import java.util.LinkedHashMap;
import java.util.Map;

public class IfNode implements IExpressionNode {

    private Map<IExpressionNode, GroupNode> ifs;
    private GroupNode elseExpression;

    //TODO: dodac metody: addIf(IExpressionNode condition, GroupNode codeBlock)
    //                    addElse(GroupNode codeBlock)
    public IfNode(Map<IExpressionNode, GroupNode> ifs,
            GroupNode elseExpression) {

        this.ifs = new LinkedHashMap<IExpressionNode, GroupNode>(ifs);
        this.elseExpression = elseExpression;
    }

    public Map<IExpressionNode, GroupNode> getIfs() {
        return this.ifs;
    }

    public GroupNode getElseExpression() {
        return this.elseExpression;
    }

    @Override
    public void accept(IVisitor visitor) throws Exception {
        visitor.visit(this);

    }

}
