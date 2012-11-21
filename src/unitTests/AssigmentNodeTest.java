package unitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import ast.AssignmentNode;
import ast.CommentNode;
import ast.LiteralNode;

public class AssigmentNodeTest {

    @Test
    public void testGetting() {
        LiteralNode literal = new LiteralNode(1);
        AssignmentNode node = new AssignmentNode("a", literal);
        assertEquals(literal, node.getValue());
        assertEquals("a", node.getIdentifier());
    }

    @Test
    public void testAccept() {
        LiteralNode literal = new LiteralNode(1);
        AssignmentNode node = new AssignmentNode("a", literal);
        TestVisitor visitor = new TestVisitor();
        node.accept(visitor);
        assertEquals("AssigmentNode", visitor.message);
    }

}
