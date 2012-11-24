package ast;

import java.util.List;

public class UserObjectMethodInvocationNode implements IExpressionNode {
    String objectVariable;
    String method;
    List<IExpressionNode> params;
    //TODO: UserObject --> IObject??
    // Wspolny interfejs dla Plugin i UserObject.
    //Polaczyc InvokeMethod i UserObject MethodInvocation
    public UserObjectMethodInvocationNode(String objectVariable, String method,
            List<IExpressionNode> params) {
        this.params = params;
        this.method = method;
        this.objectVariable = objectVariable;
    }

    public String getObjectVariable() {
        return this.objectVariable;
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
