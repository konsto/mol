package test_plugins;

import annotations.Method;

public class Console {

    @Method
    public void print(Object a) {
        System.out.println(a.toString());
    }
}
