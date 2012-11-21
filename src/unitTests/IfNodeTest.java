package unitTests;

import static org.junit.Assert.*;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;

import ast.AssignmentNode;
import ast.ComparisionNode;
import ast.GroupNode;
import ast.IExpressionNode;
import ast.IfNode;
import ast.LiteralNode;

public class IfNodeTest {

    @Test
    public void testGetting() {
        Map<IExpressionNode, GroupNode> ifs = new LinkedHashMap<IExpressionNode, GroupNode>();
        GroupNode codeBlock = new GroupNode();
        codeBlock.addChild(new AssignmentNode("a", new LiteralNode(1)));
        IExpressionNode ifCondition = new ComparisionNode(new LiteralNode(1),
                new LiteralNode(1));
        ifs.put(ifCondition, codeBlock);
        GroupNode elseExpression = new GroupNode();
        elseExpression.addChild(new AssignmentNode("a", new LiteralNode(2)));
        IfNode node = new IfNode(ifs, elseExpression);
        assertEquals(node.getIfs(), ifs);
        assertEquals(elseExpression, node.getElseExpression());
    }

    @Test
    public void testAccept() {
        Map<IExpressionNode, GroupNode> ifs = new LinkedHashMap<IExpressionNode, GroupNode>();
        GroupNode codeBlock = new GroupNode();
        codeBlock.addChild(new AssignmentNode("a", new LiteralNode(1)));
        IExpressionNode ifCondition = new ComparisionNode(new LiteralNode(1),
                new LiteralNode(1));
        ifs.put(ifCondition, codeBlock);
        GroupNode elseExpression = new GroupNode();
        elseExpression.addChild(new AssignmentNode("a", new LiteralNode(2)));
        IfNode node = new IfNode(ifs, elseExpression);
        TestVisitor visitor = new TestVisitor();
        node.accept(visitor);
        assertEquals("IfNode", visitor.message);
    }

}
