package ast;

import exceptions.BadOperandException;
import exceptions.NoSuchOperatorException;

public class UnaryOperationExecutor implements OperationExecutor {
    private Object operand;
    private UnaryOperatorType operator;

    public void set(Object operand, UnaryOperatorType operator) {
        this.operand = operand;
        this.operator = operator;
    }

    @Override
    public Object executeOperation() throws Exception {
        switch (operator) {
        case DECREMENT:
            if (Number.class.isAssignableFrom(operand.getClass())) {
                double temp = Double.parseDouble(operand.toString());
                temp--;
                return temp;
            } else {
                throw new BadOperandException();
            }
        case INCREMENT:
            if (Number.class.isAssignableFrom(operand.getClass())) {
                double temp = Double.parseDouble(operand.toString());
                temp++;
                return temp;
            } else {
                throw new BadOperandException();
            }
        case LOGICAL_NEGATION:
            if (operand.getClass().equals(boolean.class)) {
                boolean temp = !(boolean) operand;
                return temp;
            } else {
                throw new BadOperandException();
            }
        case UNARY_MINUS:
            if (Number.class.isAssignableFrom(operand.getClass())) {
                double temp = Double.parseDouble(operand.toString());
                temp = -temp;
                return temp;
            } else {
                throw new BadOperandException();
            }
        default:
            throw new NoSuchOperatorException();
        }
    }
}
