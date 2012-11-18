package ast;


public class AssignmentNode implements IExpressionNode {

    private String identifier;
    private IExpressionNode value;
    
    public AssignmentNode(String identifier, IExpressionNode value) {
        this.identifier  = identifier;
        this.value = value;
    }
    
    public String getIdentifier() {
        return this.identifier;
    }
    
    public IExpressionNode getValue() {
        return this.value;
    }
}
