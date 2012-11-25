package ast;

public class BinaryOperatorNode implements IExpressionNode {
    private IExpressionNode leftOperand;
    private IExpressionNode rightOperand;
    private BinaryOperatorType operator;

    public BinaryOperatorNode(IExpressionNode leftOperand,
            IExpressionNode rightOperand, BinaryOperatorType operator) {
        
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
        this.operator = operator;
    }
    
    public IExpressionNode getLeftOperand() {
        return this.leftOperand;
    }
    
    public IExpressionNode getRightOperand() {
        return this.rightOperand;
    }
    
    public BinaryOperatorType getOperator() {
        return this.operator;
    }

    @Override
    public void accept(IVisitor visitor) throws Exception {
        visitor.visit(this);

    }

}
