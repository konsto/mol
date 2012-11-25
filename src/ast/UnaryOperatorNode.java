package ast;

public class UnaryOperatorNode implements IExpressionNode {
    private IExpressionNode operand;
    private UnaryOperatorType operator;

    public UnaryOperatorNode(IExpressionNode operand, UnaryOperatorType operator) {
        this.operand = operand;
        this.operator = operator;
    }

    public IExpressionNode getOperand() {
        return this.operand;
    }
    
    public UnaryOperatorType getOperator() {
        return this.operator;
    }
    
    @Override
    public void accept(IVisitor visitor) throws Exception {
        visitor.visit(this);

    }

}
