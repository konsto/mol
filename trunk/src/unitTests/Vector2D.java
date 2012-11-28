package unitTests;

import annotations.*;

public class Vector2D {
    private int x;
    private int y;

    public Vector2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Addition
    public void add(int offset) {
        x += offset;
        y += offset;
    }

    @Addition
    public void add(int xoffset, int yoffset) {
        x += xoffset;
        y += yoffset;
    }

    @EqualTo
    public boolean equalTo(Vector2D vector) {
        if (x == vector.x && y == vector.y) {
            return true;
        }
        return false;
    }

    @LessThan
    public boolean lessThan(Vector2D vector) {
        if (x < vector.x && y < vector.y) {
            return true;
        } else {
            return false;
        }
    }

    @Method
    public int getX() {
        return x;
    }

    @Method
    public int getY() {
        return y;
    }

    @Method
    public String print() {
        return "Vector2D: x:" + x + " ==> y: " + y;
    }

    public int bannedMethod() {
        return 1;
    }

    @Method
    public void smallInt(int a) {
    }

    @Method
    public void bigInt(Integer a) {

    }
}
