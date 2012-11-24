package engine;

import exceptions.BadOperandsForOperationException;
import exceptions.DivideByZeroException;

public class DivisionExecutor extends AbstractBinaryOperationExecutor {

    @Override
    public Object execute(Object left, Object right) throws Exception {
        if (areNumbers(left, right)) {
            double temp1 = Double.parseDouble(left.toString());
            double temp2 = Double.parseDouble(right.toString());
            if (temp2 == 0) {
                throw new DivideByZeroException();
            }
            return temp1 / temp2;
        } else {
            throw new BadOperandsForOperationException();
        }
    }

}
