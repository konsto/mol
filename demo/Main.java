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
        Vector2D v1 = new Vector2D(1, 1);
        Vector2D v2 = new Vector2D(1, 1);
        System.out.println(v1.equalTo(v2));

        EvaluateVisitor visitor = new EvaluateVisitor();
        GroupNode root = new GroupNode();
        Map<IExpressionNode, GroupNode> ifs = new LinkedHashMap<IExpressionNode, GroupNode>();
        GroupNode elseExpression = new GroupNode();
        IExpressionNode condition = new BinaryOperatorNode(new LiteralNode(2),
                new LiteralNode(2), BinaryOperatorType.EQUAL__TO);
        GroupNode ifsCodeBlock = new GroupNode();
        ifsCodeBlock.addChild(new AssigmentNode("result", new LiteralNode(
                "EQUAL")));
        ifs.put(condition, ifsCodeBlock);
        elseExpression.addChild(new AssigmentNode("result", new LiteralNode(
                "NOT EQUAL")));

        IfNode ifNode = new IfNode(ifs, elseExpression);
        root.addChild(ifNode);
        root.accept(visitor);
        visitor.printContext();

    }
}
