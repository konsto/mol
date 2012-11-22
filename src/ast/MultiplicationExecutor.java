package ast;

import exceptions.BadOperandsForOperationException;

public class MultiplicationExecutor extends AbstractBinaryOperationExecutor {

    @Override
    public Object execute(Object left, Object right) throws Exception {
        if (areNumbers(left, right)) {
            double temp1 = Double.parseDouble(left.toString());
            double temp2 = Double.parseDouble(right.toString());
            return temp1 * temp2;
        } else {
            throw new BadOperandsForOperationException();
        }
    }

}
