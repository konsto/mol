package unitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import ast.AdditionNode;
import ast.LiteralNode;
import ast.MultiplicationNode;

public class MultiplicationNodeTest {

    @Test
    public void testGetting() {
        LiteralNode left = new LiteralNode(1);
        LiteralNode right = new LiteralNode(2);
        MultiplicationNode node = new MultiplicationNode(left, right);
        assertEquals(left, node.getLeft());
        assertEquals(right, node.getRight());
    }

    @Test
    public void testAccept() {
        LiteralNode left = new LiteralNode(1);
        LiteralNode right = new LiteralNode(2);
        MultiplicationNode node = new MultiplicationNode(left, right);
        TestVisitor visitor = new TestVisitor();
        node.accept(visitor);
        assertEquals("MultiplicationNode", visitor.message);
    }
}
