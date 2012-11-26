
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

        // for (i = 0; i < 5; i++)
        // {
        // a = i
        // }
        GroupNode initializationFor = new GroupNode();
        initializationFor
                .addChild(new AssigmentNode("iter", new LiteralNode(0)));
        BinaryOperatorNode conditionFor = new BinaryOperatorNode(
                new VariableNode("iter"), new LiteralNode(5),
                BinaryOperatorType.LESS_THAN);
        GroupNode codeBlockFor = new GroupNode();
        AssigmentNode assigmentFor = new AssigmentNode("wynikFora",
                new VariableNode("iter"));
        AssigmentNode assigmentFor2 = new AssigmentNode("iter",
                new BinaryOperatorNode(new VariableNode("iter"),
                        new LiteralNode(1), BinaryOperatorType.ADDITION));
        codeBlockFor.addChild(assigmentFor);
        codeBlockFor.addChild(assigmentFor2);
        ForNode forNode = new ForNode(initializationFor, conditionFor,
                codeBlockFor);

        root.addChild(forNode);
        root.accept(visitor);
        visitor.printContext();

    }
}
