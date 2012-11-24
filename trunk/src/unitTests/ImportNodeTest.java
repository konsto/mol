package unitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import engine.TestVisitor;
import ast.GroupNode;
import ast.INode;
import ast.ImportNode;

public class ImportNodeTest {
    GroupNode groupNode = new GroupNode();

    @Test
    public void testCreatingImportNode() {
        INode in = new ImportNode("test_plugins.TestModule", "tm");
        assertNotNull(in);
    }

    @Test
    public void VisitingImportNodeToTree() {
        INode in = new ImportNode("test_plugins.TestModule", "tm");
        assertNotNull(in);
        groupNode.addChild(in);
        TestVisitor visitor = new TestVisitor();
        in.accept(visitor);
        assertEquals("ImportNode", visitor.message);
    }

}
