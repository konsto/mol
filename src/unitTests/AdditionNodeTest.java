package unitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import ast.AdditionNode;
import ast.AssignmentNode;
import ast.CommentNode;
import ast.LiteralNode;

public class AdditionNodeTest {

    @Test
    public void testGetting() {
        LiteralNode left = new LiteralNode(1);
        LiteralNode right = new LiteralNode(2);
        AdditionNode node = new AdditionNode(left, right);
        assertEquals(left, node.getLeft());
        assertEquals(right, node.getRight());
    }

    @Test
    public void testAccept() {
        LiteralNode left = new LiteralNode(1);
        LiteralNode right = new LiteralNode(2);
        AdditionNode node = new AdditionNode(left, right);
        TestVisitor visitor = new TestVisitor();
        node.accept(visitor);
        assertEquals("AdditionNode", visitor.message);
    }
}
