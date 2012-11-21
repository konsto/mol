package unitTests;

import static org.junit.Assert.*;

import org.junit.Test;
import ast.TestVisitor;
import ast.CommentNode;
import ast.LiteralNode;

public class LiteralNodeTest {

    @Test
    public void testGetting() {
        LiteralNode node = new LiteralNode(1);
        assertEquals(1, node.getValue());
    }

    @Test
    public void testAccept() {
        LiteralNode node = new LiteralNode(1);
        TestVisitor visitor = new TestVisitor();
        node.accept(visitor);
        assertEquals("LiteralNode", visitor.message);
    }

}
