package unitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import engine.StringObject;

public class StringObjectTest {

    @Test
    public void testGetValue() {
        StringObject obj = new StringObject("test");
        assertEquals("test", obj.getValue());
    }
    
    @Test
    public void testAdd() {
        StringObject obj = new StringObject("test");
        assertEquals("testAdd", obj.add("Add"));
    }
    
    @Test
    public void testEqualTo() {
        StringObject obj = new StringObject("test");
        assertEquals(true, obj.equalTo("test"));
        assertEquals(false, obj.equalTo("testAdd"));
    }

}
