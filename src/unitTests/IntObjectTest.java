package unitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import engine.IntObject;

public class IntObjectTest {

    @Test
    public void testGetValue() {
        IntObject obj = new IntObject(2);
        assertEquals(new Integer(2), obj.getValue());
    }
    
    @Test
    public void testAddInt() {
        IntObject obj1 = new IntObject(2);
        assertEquals(new Integer(5), obj1.add(3));
    }
    
    @Test
    public void testAddDouble() {
        IntObject obj = new IntObject(2);
        assertEquals(5.3, obj.add(3.3), 10E-6);
    }
    
    @Test
    public void testEqualTo() {
        IntObject obj1 = new IntObject(2);
        assertEquals(true, obj1.equalTo(2));
        assertEquals(false, obj1.equalTo(3));
        
    }
}
