package ast;

import exceptions.BadOperandException;

public class IncrementExecutor extends AbstractUnaryOperationExecutor {

    @Override
    public Object execute(Object operand) throws Exception {
        if (Number.class.isAssignableFrom(operand.getClass())) {
            double temp = Double.parseDouble(operand.toString());
            temp++;
            return temp;
        } else {
            throw new BadOperandException();
        }
    }

}