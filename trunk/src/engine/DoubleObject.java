package engine;

import annotations.Addition;
import annotations.EqualTo;
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

    @EqualTo
    public Boolean equalTo(double object) {
        return value == object;
    }
}
