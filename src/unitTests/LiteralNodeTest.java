package unitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import engine.TestVisitor;
import ast.LiteralNode;

public class LiteralNodeTest {

    @Test
    public void testGettingInt() {
        LiteralNode node = new LiteralNode(1);
        assertEquals(1, node.getValue());
    }
    
    @Test
    public void testGettingChar() {
        LiteralNode node = new LiteralNode('a');
        assertEquals('a', node.getValue());
    }
    
    @Test
    public void testGettingString() {
        LiteralNode node = new LiteralNode("test");
        assertEquals("test", node.getValue());
    }
    
    @Test
    public void testGettingDouble() {
        LiteralNode node = new LiteralNode(1.0);
        assertEquals(1.0, node.getValue());
    }
    
    @Test (expected = Exception.class)
    public void testGettingNull() {
        LiteralNode node = new LiteralNode(null);
        assertEquals(null, node.getValue());
    }

    @Test
    public void testAccept() {
        LiteralNode node = new LiteralNode(1);
        TestVisitor visitor = new TestVisitor();
        node.accept(visitor);
        assertEquals("LiteralNode", visitor.message);
    }

}
