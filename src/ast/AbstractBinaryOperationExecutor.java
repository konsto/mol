package ast;

public abstract class AbstractBinaryOperationExecutor {

    abstract public Object execute(Object left, Object right) throws Exception;

    protected boolean areNumbers(Object left, Object right) {
        if (Number.class.isAssignableFrom(left.getClass())
                && Number.class.isAssignableFrom(right.getClass())) {
            return true;
        } else {
            return false;
        }
    }

    protected boolean areStrings(Object left, Object right) {
        if (String.class.equals(left.getClass())
                && String.class.equals(right.getClass())) {
            return true;
        } else {
            return false;
        }
    }

    protected boolean areStringAndNumber(Object left, Object right) {
        if ((left.getClass().equals(String.class) && Number.class
                .isAssignableFrom(right.getClass()))
                || (Number.class.isAssignableFrom(left.getClass()) && right
                        .getClass().equals(String.class))) {
            return true;
        } else {
            return false;
        }
    }

    protected boolean hasSameType(Object left, Object right) {
        return left.getClass().equals(right.getClass());
    }
}
