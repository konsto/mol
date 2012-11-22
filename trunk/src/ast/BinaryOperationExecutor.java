package ast;

import exceptions.BadOperandsForOperationException;
import exceptions.DifferentTypesArgumentsException;

public class BinaryOperationExecutor implements OperationExecutor {

    private Object left;
    private Object right;
    BinaryOperatorType operator;

    public void set(Object left, Object right, BinaryOperatorType operator) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    @Override
    public Object executeOperation() throws Exception {

        switch (operator) {
        case ADDITION:
            if (areNumbers()) {
                double temp1 = Double.parseDouble(left.toString());
                double temp2 = Double.parseDouble(right.toString());
                return temp1 + temp2;
            } else if (areStrings()) {
                return (String) left + (String) right;
            } else if (areStringAndNumber()) {
                return left.toString() + right.toString();
            } else {
                throw new BadOperandsForOperationException();
            }
        case SUBSTRACTION:
            if (areNumbers()) {
                double temp1 = Double.parseDouble(left.toString());
                double temp2 = Double.parseDouble(right.toString());
                return temp1 - temp2;
            } else {
                throw new BadOperandsForOperationException();
            }
            // case MULTIPLICATION:
            // break;
            // case DIVISION:
            // break;
            // case BASIC_ASSIGMENT:
            // break;
            // case EQUAL__TO:
            // break;
            // case GREATER_THAN:
            // break;
            // case GREATER_THAN_OR_EQUAL_TO:
            // break;
            // case LESS_THAN:
            // break;
            // case LESS_THAN_OR_EQUAL_TO:
            // break;
            // case NOT_EQUAL_TO:
            // break;
        default:
            return null;
        }

    }

    private boolean areNumbers() {
        if (Number.class.isAssignableFrom(left.getClass())
                && Number.class.isAssignableFrom(right.getClass())) {
            return true;
        } else {
            return false;
        }
    }

    private boolean areStrings() {
        if (String.class.equals(left.getClass())
                && String.class.equals(right.getClass())) {
            return true;
        } else {
            return false;
        }
    }

    private boolean areStringAndNumber() {
        if ((left.getClass().equals(String.class) && Number.class
                .isAssignableFrom(right.getClass()))
                || (Number.class.isAssignableFrom(left.getClass()) && right
                        .getClass().equals(String.class))) {
            return true;
        } else {
            return false;
        }
    }

    private boolean hasSameType() {
        return left.getClass().equals(right.getClass());
    }
}
