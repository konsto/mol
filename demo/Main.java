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

        GroupNode root = new GroupNode();
        EvaluateVisitor visitor = new EvaluateVisitor();

        GroupNode initialization = new GroupNode();
        IExpressionNode condition = null;
        GroupNode codeBlock = new GroupNode();
        GroupNode afterBlock = new GroupNode();
       

        initialization.addChild(new AssigmentNode("i", new LiteralNode(0)));
        condition = new BinaryOperatorNode(new VariableNode("i"),
                new LiteralNode(5), BinaryOperatorType.LESS_THAN);
        afterBlock.addChild(new AssigmentNode("i", new BinaryOperatorNode(
                new VariableNode("i"), new LiteralNode(1),
                BinaryOperatorType.ADDITION)));
        codeBlock.addChild(new AssigmentNode("wynikFora", new VariableNode("i")));

        ForNode forNode = new ForNode(initialization, condition, codeBlock,
                afterBlock);
        
        root.addChild(forNode);
        root.accept(visitor);
        visitor.printContext();

    }
}
