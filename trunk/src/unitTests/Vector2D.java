package unitTests;

import annotations.*;

@Type (type = Vector2D.class)
public class Vector2D {
    private int x;
    private int y;

    public Vector2D(int x, int y) {
        this.x = x;
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

    @Method
    public int getX() {
        return x;
    }

    @Method
    public int getY() {
        return y;
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
