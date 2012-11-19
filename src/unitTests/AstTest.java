package unitTests;

import java.util.List;
import java.util.LinkedList;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ast.AssignmentNode;
import ast.CommentNode;
import ast.GroupNode;
import ast.IExpressionNode;
import ast.INode;
import ast.INodeIterator;
import ast.ImportNode;
import ast.InvocationNode;
import ast.LiteralNode;
import ast.GroupNodeForwardIterator;
import ast.PrintVisitor;
import ast.VariableNode;

public class AstTest {
    GroupNode groupNode = new GroupNode();

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testAst() {
        groupNode.addChild(new ImportNode("my_plugins.Math", "m"));
        groupNode.addChild(new CommentNode("calc sqrt of 5"));
        groupNode.addChild(new AssignmentNode("a", new LiteralNode(5)));
        List<IExpressionNode> params = new LinkedList<IExpressionNode>();
        params.add(new VariableNode("a"));
        groupNode.addChild(new AssignmentNode("b", new InvocationNode("m",
                "sqrt", params)));
        params.clear();
        params.add(new VariableNode("b"));
        params.add(new VariableNode("d"));
        groupNode.addChild(new InvocationNode("c", "print", params));
        PrintVisitor visitor = new PrintVisitor();
        visitor.visit(groupNode);
    }
}
