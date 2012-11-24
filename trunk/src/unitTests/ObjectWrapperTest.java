package unitTests;

import static org.junit.Assert.*;

import java.lang.reflect.Method;

import org.junit.Test;

import engine.DoubleObject;
import engine.IntObject;
import engine.ObjectWrapper;
import engine.StringObject;

public class ObjectWrapperTest {

    @Test
    public void testConstructFromDouble() throws Exception {
        ObjectWrapper obj = new ObjectWrapper(2.0);
        Object value = obj.getValue();
        assertEquals(Double.class, value.getClass());
        assertEquals(2.0, (Double) obj.getValue(), 10E-6);

        obj = new ObjectWrapper(new Double(2.0));
        value = obj.getValue();
        assertEquals(Double.class, value.getClass());
        assertEquals(2.0, (Double) obj.getValue(), 10E-6);
    }

    @Test
    public void testConstructFromFloat() throws Exception {
        ObjectWrapper obj = new ObjectWrapper(new Float(2.0));
        Object value = obj.getValue();
        assertEquals(Double.class, value.getClass());
        assertEquals(2.0, (Double) obj.getValue(), 10E-6);
    }

    @Test
    public void testConstructFromInteger() throws Exception {
        ObjectWrapper obj = new ObjectWrapper(1);
        Object value = obj.getValue();
        assertEquals(Integer.class, value.getClass());
        assertEquals(new Integer(1), obj.getValue());

        obj = new ObjectWrapper(new Integer(1));
        value = obj.getValue();
        assertEquals(Integer.class, value.getClass());
        assertEquals(new Integer(1), obj.getValue());
    }

    @Test
    public void testConstructFromString() throws Exception {
        ObjectWrapper obj = new ObjectWrapper("test");
        Object value = obj.getValue();
        assertEquals(String.class, value.getClass());
        assertEquals("test", obj.getValue());
    }

    @Test
    public void testConstructFromObject() throws Exception {
        Vector2D vector = new Vector2D(2, 2);
        ObjectWrapper obj = new ObjectWrapper(vector);
        Object value = obj.getValue();
        assertEquals(Vector2D.class, value.getClass());
        assertEquals(vector, value);
    }

    @Test
    public void testGetType() throws Exception {
        ObjectWrapper obj = new ObjectWrapper(2);
        assertEquals(Integer.class, obj.getType());

        obj = new ObjectWrapper(new Vector2D(2, 2));
        assertEquals(Vector2D.class, obj.getType());
    }

    @Test
    public void testGetMethod1() throws NoSuchMethodException,
            SecurityException {
        Class<?> c = Vector2D.class;
        Method method = c.getMethod("smallInt", new Class[] { int.class });
    }

    @Test(expected = Exception.class)
    public void testGetMethod2() throws NoSuchMethodException,
            SecurityException {
        Class<?> c = Vector2D.class;
        Method method = c.getMethod("smallInt", new Class[] { Integer.class });
    }

    @Test(expected = Exception.class)
    public void testGetMethod3() throws NoSuchMethodException,
            SecurityException {
        Class<?> c = Vector2D.class;
        Method method = c.getMethod("bigInt", new Class[] { int.class });
    }

    @Test
    public void testGetMethod4() throws NoSuchMethodException,
            SecurityException {
        Class<?> c = Vector2D.class;
        Method method = c.getMethod("bigInt", new Class[] { Integer.class });
    }

    @Test
    public void testInvokeMethod1() throws Exception {
        Class<?> c = Vector2D.class;
        Method method = c.getMethod("smallInt", new Class[] { int.class });
        method.invoke(new Vector2D(2, 2), new Object[] { new Integer(1) });
    }

    @Test
    public void testInvokeMethod2() throws Exception {
        Class<?> c = Vector2D.class;
        Method method = c.getMethod("bigInt", new Class[] { Integer.class });
        method.invoke(new Vector2D(2, 2), new Object[] { 1 });
    }

    //
    // @Test
    // public void testIsAssignableFrom() {
    // assertTrue(Integer.class.isAssignableFrom(int.class));
    // }

    @Test
    public void testAddPrimitive() throws Exception {
        ObjectWrapper obj = new ObjectWrapper(2);
        // assertEquals(4, obj.add(new ObjectWrapper(2)));
        obj.add(new ObjectWrapper(2));
    }

}
