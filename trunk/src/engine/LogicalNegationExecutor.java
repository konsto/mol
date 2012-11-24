package engine;

import exceptions.BadOperandException;

public class LogicalNegationExecutor extends AbstractUnaryOperationExecutor {

    @Override
    public Object execute(Object operand) throws Exception {
        if (operand.getClass().equals(boolean.class)) {
            boolean temp = !(boolean) operand;
            return temp;
        } else {
            throw new BadOperandException();
        }
    }

}
