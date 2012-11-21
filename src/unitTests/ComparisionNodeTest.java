package unitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import ast.ComparisionNode;
import ast.LiteralNode;

public class ComparisionNodeTest {

    @Test
    public void testGetting() {
        LiteralNode left = new LiteralNode(1);
        LiteralNode right = new LiteralNode(2);
        ComparisionNode node = new ComparisionNode(left, right);
        assertEquals(node.getLeft(), left);
        assertEquals(node.getRight(), right);
    }

    @Test
    public void testAccept() {
        LiteralNode left = new LiteralNode(1);
        LiteralNode right = new LiteralNode(2);
        ComparisionNode node = new ComparisionNode(left, right);
        TestVisitor visitor = new TestVisitor();
        node.accept(visitor);
        assertEquals("ComparisionNode", visitor.message);
    }

}
