package ast;

import java.util.List;

public class InvocationNode implements IExpressionNode {

    String pluginAlias;
    String method;
    List<IExpressionNode> params;

    public InvocationNode(String pluginAlias, String method,
            List<IExpressionNode> params) {
        this.params = params;
        this.method = method;
        this.pluginAlias = pluginAlias;
    }

    public String getPluginAlias() {
        return this.pluginAlias;
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
