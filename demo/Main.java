import java.util.LinkedList;
import java.util.List;

import ast.BinaryOperatorNode;
import ast.BinaryOperatorType;
import ast.EvaluateVisitor;
import ast.GroupNode;
import ast.IExpressionNode;
import ast.ImportNode;
import ast.LiteralNode;
import ast.VariableNode;

public class Main {
    /**
     * @param args
     */
    public static void main(String[] args) {
        EvaluateVisitor visitor = new EvaluateVisitor();
        GroupNode root = new GroupNode();
        root.addChild(new BinaryOperatorNode(new LiteralNode("a"),
                new LiteralNode(1), BinaryOperatorType.BASIC_ASSIGMENT));
        BinaryOperatorNode rightOperand = new BinaryOperatorNode(
                new LiteralNode(5), new LiteralNode(2),
                BinaryOperatorType.SUBSTRACTION);
        root.addChild(new BinaryOperatorNode(new LiteralNode("b"),
                rightOperand, BinaryOperatorType.BASIC_ASSIGMENT));

        root.accept(visitor);
        visitor.printContext();

    }
}
