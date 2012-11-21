package unitTests;

import static org.junit.Assert.*;
import org.junit.Test;
import ast.ImportNode;
import ast.TestVisitor;

public class astImportNodeTest {

    @Test
    public void testGetting() {
        ImportNode node = new ImportNode("test_plugins.TestModule", "a");
        assertEquals("test_plugins.TestModule", node.getPluginName());
        assertEquals("a", node.getAlias());
    }

    @Test
    public void testAccept() {
        ImportNode node = new ImportNode("test_plugins.TestModule", "a");
        TestVisitor visitor = new TestVisitor();
        node.accept(visitor);
        assertTrue(visitor.message.equals("ImportNode"));
    }

}
