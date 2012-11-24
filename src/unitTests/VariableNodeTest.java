package unitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import engine.TestVisitor;
import ast.LiteralNode;
import ast.VariableNode;

public class VariableNodeTest {

    @Test
    public void testGetting() {
        String ident = "a";
        VariableNode node = new VariableNode(ident);
        assertEquals(node.getIdentifier(), ident);
    }

    @Test
    public void testAccept() {
        VariableNode node = new VariableNode("a");
        TestVisitor visitor = new TestVisitor();
        node.accept(visitor);
        assertEquals("VariableNode", visitor.message);
    }

}
