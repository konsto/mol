package engine;

import annotations.Addition;
import annotations.Division;
import annotations.EqualTo;
import annotations.GreaterThan;
import annotations.GreaterThanOrEqualTo;
import annotations.LessThan;
import annotations.LessThanOrEqualTo;
import annotations.Minus;
import annotations.Multiplication;
import annotations.NotEqualTo;
import annotations.Subtraction;
import annotations.Type;
import annotations.Value;

@Type(type = Double.class)
public class DoubleObject {
    Double value;

    public DoubleObject(double value) {
        this.value = value;
    }

    @Value
    public Double getValue() {
        return value;
    }

    @Addition
    public Double add(double object) {
        return value + object;
    }

    @Addition
    public Double add(int object) {
        return value + object;
    }

    @Subtraction
    public Double subtract(double object) {
        return value - object;
    }

    @Subtraction
    public Double subtract(int object) {
        return value - object;
    }

    @EqualTo
    public Boolean equalTo(double object) {
        return value == object;
    }

    @GreaterThan
    public Boolean greaterThan(double object) {
        return value > object;
    }

    @GreaterThanOrEqualTo
    public Boolean greaterThanOrEqual(double object) {
        return value >= object;
    }

    @LessThan
    public Boolean lessThan(double object) {
        return value < object;
    }

    @LessThanOrEqualTo
    public Boolean lessThanOrEqualTo(double object) {
        return value <= object;
    }

    @NotEqualTo
    public Boolean notEqualTo(double object) {
        return value != object;
    }
    
    @Multiplication
    public Double multiply(double object) {
        return  value * object;
    }
    
    @Division
    public Double divide(double object) {
        return value / object;
    }
    
    @Minus
    public Double minus() {
        return -value;
    }
}
