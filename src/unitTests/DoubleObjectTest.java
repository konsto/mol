package unitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import engine.DoubleObject;

public class DoubleObjectTest {

    @Test
    public void testGetValue() {
        DoubleObject obj = new DoubleObject(1.0);
        assertEquals(1.0, obj.getValue(), 10E-6);

        obj = new DoubleObject(new Double(1));
        assertEquals(1.0, obj.getValue(), 10E-6);

        obj = new DoubleObject(new Double(1.0));
        assertEquals(1.0, obj.getValue(), 10E-6);

        obj = new DoubleObject(new Integer(1));
        assertEquals(1.0, obj.getValue(), 10E-6);
        
        obj = new DoubleObject(new Float(2.3));
        assertEquals(2.3, obj.getValue(), 10E-6);
    }

    @Test
    public void addDouble() {
        DoubleObject obj = new DoubleObject(2.0);
        assertEquals(5.2, obj.add(3.2), 10E-6);
    }

    @Test
    public void addInt() {
        DoubleObject obj = new DoubleObject(1.0);
        assertEquals(2.0, obj.add(1), 10E-6);
    }

    @Test
    public void testEqualTo() {
        DoubleObject obj = new DoubleObject(2.0);
        assertEquals(true, obj.equalTo(2.0));
        assertEquals(false, obj.equalTo(2.2));
    }

}
