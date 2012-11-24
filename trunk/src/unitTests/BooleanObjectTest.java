package unitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import engine.BooleanObject;

public class BooleanObjectTest {

    @Test
    public void testGetValue() {
        BooleanObject obj = new BooleanObject(true);
        assertEquals(true, obj.getValue());
    }
    
    @Test
    public void testEqualTo() {
        BooleanObject obj = new BooleanObject(true);
        assertEquals(true, obj.equalTo(true));
        assertEquals(false, obj.equalTo(false));
    }

}
