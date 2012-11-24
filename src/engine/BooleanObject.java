package engine;

import annotations.EqualTo;
import annotations.Negation;
import annotations.Type;
import annotations.Value;

@Type(type = Boolean.class)
public class BooleanObject {
    Boolean value;

    public BooleanObject(boolean value) {
        this.value = value;
    }

    @Value
    public Boolean getValue() {
        return value;
    }

    @EqualTo
    public Boolean equalTo(boolean object) {
        return value == object;
    }
    
    @Negation
    public Boolean negation() {
        return !value;
    }
}
