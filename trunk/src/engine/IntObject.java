package engine;

import annotations.Addition;
import annotations.Division;
import annotations.EqualTo;
import annotations.GreaterThan;
import annotations.GreaterThanOrEqualTo;
import annotations.LessThan;
import annotations.LessThanOrEqualTo;
import annotations.Minus;
import annotations.Modulo;
import annotations.Multiplication;
import annotations.NotEqualTo;
import annotations.Subtraction;
import annotations.Type;
import annotations.Value;

@Type(type = Integer.class)
public class IntObject {
    Integer value;

    public IntObject(int value) {
        this.value = value;
    }

    @Value
    public Integer getValue() {
        return value;
    }

    @Addition
    public Integer add(int object) {
        return value + object;
    }

    @Addition
    public Double add(double object) {
        return value + object;
    }

    @Subtraction
    public Integer subtract(int object) {
        return value - object;
    }

    @Subtraction
    public Double subtract(double object) {
        return value - object;
    }

    @Division
    public Integer divide(int object) {
        return value / object;
    }

    @Multiplication
    public Integer multiply(int object) {
        return value * object;
    }

    @GreaterThan
    public Boolean greaterThan(int object) {
        return value > object;
    }

    @GreaterThanOrEqualTo
    public Boolean greaterThanOrEqual(int object) {
        return value >= object;
    }

    @LessThan
    public Boolean lessThan(int object) {
        return value < object;
    }

    @LessThanOrEqualTo
    public Boolean lessThanOrEqualTo(int object) {
        return value <= object;
    }

    @EqualTo
    public Boolean equalTo(int object) {
        return value == object;
    }

    @NotEqualTo
    public Boolean notEqualTo(int object) {
        return value != object;
    }

    @Minus
    public Integer minus() {
        return -value;
    }

    @Modulo
    public Integer modulo(int object) {
        return value % object;
    }
}
