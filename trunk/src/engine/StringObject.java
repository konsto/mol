package engine;

import annotations.Addition;
import annotations.EqualTo;
import annotations.Type;
import annotations.Value;

@Type(type = String.class)
public class StringObject {
    private String value;

    public StringObject(String value) {
        this.value = value;
    }

    @Value
    public String getValue() {
        return value;
    }

    @Addition
    public String add(String object) {
        return this.getValue() + object;
    }

    @EqualTo
    public boolean equalTo(String object) {
        return this.value.equals(object);
    }
}
