package ast;

public enum UnaryOperatorType {
    MINUS, INCREMENT, DECREMENT, NEGATION;

    public static UnaryOperatorType getEnum(String type) {
        UnaryOperatorType result = null;
        switch (type.toLowerCase()) {
        case "minus":
            result = UnaryOperatorType.MINUS;
            break;

        case "increment":
            result = UnaryOperatorType.INCREMENT;
            break;

        case "decrement":
            result = UnaryOperatorType.DECREMENT;
            break;

        case "negation":
            result = UnaryOperatorType.NEGATION;
            break;
        }
        return result;
    }

}
