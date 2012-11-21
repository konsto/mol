package unitTests;

import static org.junit.Assert.*;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

import org.junit.Test;

import ast.AdditionNode;
import ast.AssignmentNode;
import ast.ComparisionNode;
import ast.GroupNode;
import ast.IExpressionNode;
import ast.IfNode;
import ast.ImportNode;
import ast.InvocationNode;
import ast.LiteralNode;
import ast.MultiplicationNode;
import ast.VariableNode;

public class AstTest {
    GroupNode root = new GroupNode();
    ToyEvaluateVisitor visitor = new ToyEvaluateVisitor();

    @Test
    public void testImportNode() throws ClassNotFoundException {
        root.addChild(new ImportNode("test_plugins.TestModule", "tm"));
        root.accept(visitor);
        assertTrue((Boolean) visitor.getValue());
    }

    @Test
    public void testLiteralNode() {
        LiteralNode ln = new LiteralNode(5);
        assertEquals(5, ln.getValue());
    }

    @Test
    public void testCreatingInvcationNode() {
        InvocationNode in = new InvocationNode("tm", "foo",
                new LinkedList<IExpressionNode>());
        assertNotNull(in);
    }

    @Test
    public void testAssigmentNode() {
        root.addChild(new AssignmentNode("a", new LiteralNode(5)));
        root.accept(visitor);
        Map<String, Object> currentContext = visitor.getContext();
        assertEquals(5, currentContext.get("a"));
    }

    @Test(expected = Exception.class)
    public void testBadAssigmentNode() {
        root.addChild(new AssignmentNode("a", new VariableNode("b")));
        root.accept(visitor);

        Map<String, Object> currentContext = visitor.getContext();
        assertEquals(5, currentContext.get("a"));
    }

    @Test
    public void testAssigningInvocationNode() {
        root.addChild(new ImportNode("test_plugins.TestModule", "tm"));
        root.addChild(new AssignmentNode("funkcja", new InvocationNode("tm",
                "foo", new LinkedList<IExpressionNode>())));
        root.accept(visitor);
        assertEquals("foo", visitor.getContextElement("funkcja"));
    }

    @Test
    public void testAssigningvariableNode() {
        root.addChild(new AssignmentNode("a", new LiteralNode(5)));
        root.addChild(new AssignmentNode("b", new VariableNode("a")));
        root.accept(visitor);
        assertTrue(visitor.getContextElement("a").equals(
                visitor.getContextElement("b")));
    }

    @Test
    public void testAdditionNode() {
        root.addChild(new AssignmentNode("a", new LiteralNode(5)));
        root.addChild(new AssignmentNode("addition", new AdditionNode(
                new LiteralNode(5), new VariableNode("a"))));
        root.accept(visitor);
        assertEquals(10, visitor.getContextElement("addition"));
    }

    @Test(expected = Exception.class)
    public void testBadAdditionNode() {
        root.addChild(new AssignmentNode("a", new LiteralNode("ala")));
        root.addChild(new AssignmentNode("addition", new AdditionNode(
                new LiteralNode(5), new VariableNode("a"))));
        root.accept(visitor);
        assertEquals(10, visitor.getContextElement("addition"));
    }

    @Test
    public void testMultiplicationNode() {
        root.addChild(new AssignmentNode("a", new LiteralNode(5)));
        root.addChild(new AssignmentNode("multiplication",
                new MultiplicationNode(new LiteralNode(5),
                        new VariableNode("a"))));
        root.accept(visitor);
        assertEquals(25, visitor.getContextElement("multiplication"));
    }

    @Test
    public void testTrueComparisionNode() {
        root.addChild(new ComparisionNode(new LiteralNode(5),
                new LiteralNode(5)));
        root.accept(visitor);
        visitor.printContext();
        assertEquals(true, visitor.getValue());
    }

    @Test
    public void testFalseComparisionNode() {
        root.addChild(new ComparisionNode(new LiteralNode(6),
                new LiteralNode(5)));
        root.accept(visitor);
        visitor.printContext();
        assertEquals(false, visitor.getValue());
    }

    @Test    
    public void testIfNode() {
        Map<IExpressionNode, GroupNode> ifs = new LinkedHashMap<IExpressionNode, 
                GroupNode>();
        GroupNode codeBlock =new GroupNode();
        codeBlock.addChild(new AssignmentNode("a", 
                new AdditionNode(new LiteralNode(1), new LiteralNode(1))));
        codeBlock.addChild(new AssignmentNode("b", 
                new AdditionNode(new VariableNode("a"), new LiteralNode(1))));
        ifs.put(new ComparisionNode(new LiteralNode(5), new LiteralNode(1)), codeBlock);
        GroupNode elseCodeBlock = new GroupNode();
        elseCodeBlock.addChild(new AssignmentNode("b", new LiteralNode(11)));
        root.addChild(new IfNode(ifs, elseCodeBlock));
        root.accept(visitor);
        System.out.println(visitor.getValue());
    }
}
