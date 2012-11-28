package ast;

public enum BinaryOperatorType {
    ASSIGMENT, ADDITION, SUBSTRACTION, MULTIPLICATION,
    DIVISION, EQUAL__TO, NOT_EQUAL_TO, GREATER_THAN, LESS_THAN,
    GREATER_THAN_OR_EQUAL_TO, LESS_THAN_OR_EQUAL_TO;
    
    public static BinaryOperatorType getEnum(String operator)   {
        BinaryOperatorType result = null;
        switch(operator.toLowerCase()) {
        case "addition":
            result = BinaryOperatorType.ADDITION;
            break;
        case "subtract":
            result = BinaryOperatorType.SUBSTRACTION;
            break;
        case "multiply":
            result = BinaryOperatorType.MULTIPLICATION;
            break;
        case "divide":
            result = BinaryOperatorType.DIVISION;
            break;
        case "equalto":
            result = BinaryOperatorType.EQUAL__TO;
            break;
        case "notequalto":
            result = BinaryOperatorType.NOT_EQUAL_TO;
            break;
        case "greaterthan":
            result = BinaryOperatorType.GREATER_THAN;
            break;
        case "lessthan":
            result = BinaryOperatorType.LESS_THAN;
            break;
        case "greaterthanorequalto":
            result = BinaryOperatorType.GREATER_THAN_OR_EQUAL_TO;
            break;
        case "lessthanorequalto":
            result = BinaryOperatorType.LESS_THAN_OR_EQUAL_TO;
            break;
        }
        return result;
    }
}
