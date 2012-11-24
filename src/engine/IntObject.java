package engine;

import annotations.Addition;
import annotations.EqualTo;
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

    @EqualTo
    public Boolean equalTo(int object) {
        return value == object;
    }
}
