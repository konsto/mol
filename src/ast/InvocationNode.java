package ast;

import java.util.List;

public class InvocationNode implements IExpressionNode {

    IExpressionNode target;
    String method;
    List<IExpressionNode> params;

    public InvocationNode(IExpressionNode target, String method,
            List<IExpressionNode> params) {
        this.params = params;
        this.method = method;
        this.target = target;
    }

    public IExpressionNode getPluginAlias() {
        return this.target;
    }

    public String getMethod() {
        return this.method;
    }

    public List<IExpressionNode> getParams() {
        return this.params;
    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }
}
