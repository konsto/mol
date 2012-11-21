package unitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import ast.CommentNode;

public class CommentNodeTest {

    @Test
    public void testGetting() {
        CommentNode node = new CommentNode("Comment");
        assertEquals("Comment", node.getContent());
    }

    @Test
    public void testAccept() {
        CommentNode node = new CommentNode("Comment");
        TestVisitor visitor = new TestVisitor();
        node.accept(visitor);
        assertEquals("CommentNode", visitor.message);
    }

}