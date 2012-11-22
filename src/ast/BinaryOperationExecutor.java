package ast;

import exceptions.BadOperandsForOperationException;
import exceptions.DivideByZeroException;
import exceptions.NoSuchOperatorException;

public class BinaryOperationExecutor implements OperationExecutor {

    private Object left;
    private Object right;
    private BinaryOperatorType operator;

    public void set(Object left, Object right, BinaryOperatorType operator) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    @Override
    public Object executeOperation() throws Exception {
        // TODO: Dodac obsluge typow zdefiniowanych przez klienta
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
        case MULTIPLICATION:
            if (areNumbers()) {
                double temp1 = Double.parseDouble(left.toString());
                double temp2 = Double.parseDouble(right.toString());
                return temp1 * temp2;
            } else {
                throw new BadOperandsForOperationException();
            }
            //TODO Przy dzieleniu przez 0 leci wyjatek, ale jesli
            //wynik dzielenia jest przypisywany do zmiennej,
            //to zmienna ta wpisuje sie do kontekstu z niezidentyfikowana
            //wartoscia
        case DIVISION:
            if (areNumbers()) {
                double temp1 = Double.parseDouble(left.toString());
                double temp2 = Double.parseDouble(right.toString());
                if (temp2 == 0) {
                    throw new DivideByZeroException();
                }
                return temp1 / temp2;
            } else {
                throw new BadOperandsForOperationException();
            }
        case EQUAL__TO:
            if (areNumbers()) {
                double temp1 = Double.parseDouble(left.toString());
                double temp2 = Double.parseDouble(right.toString());
                return temp1 == temp2;
            } else if (areStrings()) {
                System.out.println(left.toString());
                return left.toString().equals(right.toString());
            } else {
                throw new BadOperandsForOperationException();
            }
        case GREATER_THAN:
            if (areNumbers()) {
                double temp1 = Double.parseDouble(left.toString());
                double temp2 = Double.parseDouble(right.toString());
                return temp1 > temp2;
            } else {
                throw new BadOperandsForOperationException();
            }
        case GREATER_THAN_OR_EQUAL_TO:
            if (areNumbers()) {
                double temp1 = Double.parseDouble(left.toString());
                double temp2 = Double.parseDouble(right.toString());
                return temp1 >= temp2;
            } else {
                throw new BadOperandsForOperationException();
            }
        case LESS_THAN:
            if (areNumbers()) {
                double temp1 = Double.parseDouble(left.toString());
                double temp2 = Double.parseDouble(right.toString());
                return temp1 < temp2;
            } else {
                throw new BadOperandsForOperationException();
            }
        case LESS_THAN_OR_EQUAL_TO:
            if (areNumbers()) {
                double temp1 = Double.parseDouble(left.toString());
                double temp2 = Double.parseDouble(right.toString());
                return temp1 <= temp2;
            } else {
                throw new BadOperandsForOperationException();
            }
        case NOT_EQUAL_TO:
            if (areNumbers()) {
                double temp1 = Double.parseDouble(left.toString());
                double temp2 = Double.parseDouble(right.toString());
                return temp1 != temp2;
            } else if (areStrings()) {
                return left.toString() != right.toString();
            } else {
                throw new BadOperandsForOperationException();
            }
        default:
            throw new NoSuchOperatorException();
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
