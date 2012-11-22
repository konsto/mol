import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import ast.BinaryOperatorNode;
import ast.BinaryOperatorType;
import ast.EvaluateVisitor;
import ast.GroupNode;
import ast.IExpressionNode;
import ast.IfNode;
import ast.ImportNode;
import ast.InvocationNode;
import ast.LiteralNode;
import ast.UnaryOperatorNode;
import ast.UnaryOperatorType;
import ast.VariableNode;

public class Main {
    /**
     * @param args
     */
    public static void main(String[] args) {
        EvaluateVisitor visitor = new EvaluateVisitor();
        GroupNode root = new GroupNode();

        // root.addChild(new BinaryOperatorNode(new LiteralNode("a"),
        // new LiteralNode(1), BinaryOperatorType.BASIC_ASSIGMENT));
        // BinaryOperatorNode rightOperand = new BinaryOperatorNode(
        // new LiteralNode(5), new LiteralNode(2),
        // BinaryOperatorType.SUBSTRACTION);
        //
        // root.addChild(new BinaryOperatorNode(new LiteralNode("b"),
        // rightOperand, BinaryOperatorType.BASIC_ASSIGMENT));
        // ImportNode importNode = new ImportNode("test_plugins.TestModule",
        // "tm");
        //
        // root.addChild(importNode);
        //
        // InvocationNode invocationNode = new InvocationNode("tm", "foo",
        // new LinkedList<IExpressionNode>());
        //
        // root.addChild(new BinaryOperatorNode(new LiteralNode("c"),
        // invocationNode, BinaryOperatorType.BASIC_ASSIGMENT));
        // List<IExpressionNode> params = new LinkedList<IExpressionNode>();
        // params.add(new LiteralNode(2));
        // invocationNode = new InvocationNode("tm", "bar", params);
        //
        // root.addChild(new BinaryOperatorNode(new LiteralNode("d"),
        // invocationNode, BinaryOperatorType.BASIC_ASSIGMENT));
        //
        // root.addChild(new BinaryOperatorNode(new LiteralNode("e"),
        // new VariableNode("a"), BinaryOperatorType.BASIC_ASSIGMENT));
        //
        // Map<IExpressionNode, GroupNode> ifs = new
        // LinkedHashMap<IExpressionNode, GroupNode>();
        // GroupNode elseExpression = new GroupNode();
        // GroupNode ifCodeBlock = new GroupNode();
        // ifCodeBlock.addChild(new BinaryOperatorNode(new LiteralNode("w"),
        // new LiteralNode(10), BinaryOperatorType.BASIC_ASSIGMENT));
        // ifCodeBlock.addChild(new BinaryOperatorNode(
        // new LiteralNode("wynikIfa"), new BinaryOperatorNode(
        // new VariableNode("w"), new LiteralNode(10),
        // BinaryOperatorType.MULTIPLICATION),
        // BinaryOperatorType.BASIC_ASSIGMENT));
        // elseExpression.addChild(new BinaryOperatorNode(new LiteralNode(
        // "wynikIfa"), new LiteralNode(7),
        // BinaryOperatorType.BASIC_ASSIGMENT));
        // IExpressionNode ifCondition = new BinaryOperatorNode(new
        // VariableNode(
        // "b"), new LiteralNode(1), BinaryOperatorType.EQUAL__TO);
        // ifs.put(ifCondition, ifCodeBlock);
        // IfNode ifnode = new IfNode(ifs, elseExpression);

        BinaryOperatorNode add1 = new BinaryOperatorNode(new LiteralNode(35),
                new LiteralNode(2), BinaryOperatorType.SUBSTRACTION);
        root.addChild(new BinaryOperatorNode(new LiteralNode("test1"), add1,
                BinaryOperatorType.BASIC_ASSIGMENT));

        UnaryOperatorNode test2 = new UnaryOperatorNode(new LiteralNode(5),
                UnaryOperatorType.UNARY_MINUS);
        root.addChild(new BinaryOperatorNode(new LiteralNode("test2"), test2,
                BinaryOperatorType.BASIC_ASSIGMENT));

        // root.addChild(ifnode);
        root.accept(visitor);
        visitor.printContext();
    }
}
