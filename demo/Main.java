import java.util.LinkedHashMap;
import java.util.Map;

import unitTests.Vector2D;
import engine.EvaluateVisitor;

import ast.AssigmentNode;
import ast.BinaryOperatorNode;
import ast.BinaryOperatorType;
import ast.ForNode;
import ast.GroupNode;
import ast.IExpressionNode;
import ast.IfNode;
import ast.ImportNode;
import ast.InvocationNode;
import ast.LiteralNode;
import ast.VariableNode;
import ast.WhileNode;

public class Main {
    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        EvaluateVisitor visitor = new EvaluateVisitor();
        GroupNode root = new GroupNode();
        AssigmentNode assigment = new AssigmentNode("a", new LiteralNode(0));
        BinaryOperatorNode condition = new BinaryOperatorNode(new VariableNode(
                "a"), new LiteralNode(5), BinaryOperatorType.LESS_THAN);
        GroupNode codeBlock = new GroupNode();
        AssigmentNode assigment2 = new AssigmentNode("a", new BinaryOperatorNode(new VariableNode("a"),
                new LiteralNode(1), BinaryOperatorType.ADDITION));
        codeBlock.addChild(assigment2);
        WhileNode whileNode = new WhileNode(condition, codeBlock);

        root.addChild(assigment);
        root.addChild(whileNode);
        root.accept(visitor);
        visitor.printContext();

    }
}
