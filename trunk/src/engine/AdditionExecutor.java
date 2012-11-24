package engine;

import exceptions.BadOperandsForOperationException;

public class AdditionExecutor extends AbstractBinaryOperationExecutor {

    @Override
    public Object execute(Object left, Object right) throws Exception {
        if (areNumbers(left, right)) {
            double temp1 = Double.parseDouble(left.toString());
            double temp2 = Double.parseDouble(right.toString());
            return temp1 + temp2;
        } else if (areStrings(left, right)) {
            return (String) left + (String) right;
        } else if (areStringAndNumber(left, right)) {
            return left.toString() + right.toString();
        } else {
            throw new BadOperandsForOperationException();
        }
    }
}
