package unitTests;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import ast.IExpressionNode;
import ast.InvocationNode;
import ast.LiteralNode;
import ast.MultiplicationNode;

public class InvocationNodeTest {

    @Test
    public void testGetting() {
        String pluginAlias = "pluginAlias";
        String method = "method";
        List<IExpressionNode> params = new LinkedList<IExpressionNode>();
        params.add(new LiteralNode(1));
        params.add(new LiteralNode(2));
        InvocationNode node = new InvocationNode(pluginAlias, method, params);
        assertEquals(node.getPluginAlias(), pluginAlias);
        assertEquals(node.getMethod(), method);
        assertEquals(node.getParams(), params);
    }

    @Test
    public void testAccept() {
        String pluginAlias = "pluginAlias";
        String method = "method";
        List<IExpressionNode> params = new LinkedList<IExpressionNode>();
        params.add(new LiteralNode(1));
        params.add(new LiteralNode(2));
        InvocationNode node = new InvocationNode(pluginAlias, method, params);
        TestVisitor visitor = new TestVisitor();
        node.accept(visitor);
        assertEquals("InvocationNode", visitor.message);
    }

}
