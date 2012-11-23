import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
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
import ast.UserObjectMethodInvocationNode;
import ast.VariableNode;

import test_plugins.*;

public class Main {
    /**
     * @param args
     * @throws SecurityException
     * @throws NoSuchMethodException
     * @throws InstantiationException
     * @throws InvocationTargetException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public static void main(String[] args) throws NoSuchMethodException,
            SecurityException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException,
            InstantiationException {
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
        // BinaryOperatorNode add1 = new BinaryOperatorNode(new LiteralNode(35),
        // new LiteralNode(2), BinaryOperatorType.DIVISION);
        // root.addChild(new BinaryOperatorNode(new LiteralNode("test1"), add1,
        // BinaryOperatorType.BASIC_ASSIGMENT));
        //
        // UnaryOperatorNode test2 = new UnaryOperatorNode(new LiteralNode(5),
        // UnaryOperatorType.UNARY_MINUS);
        // root.addChild(new BinaryOperatorNode(new LiteralNode("test2"), test2,
        // BinaryOperatorType.BASIC_ASSIGMENT));
        // root.addChild(ifnode);

        ImportNode importNode = new ImportNode("test_plugins.DogFactory", "df");

        List<IExpressionNode> params = new LinkedList<IExpressionNode>();
        // params.add(new LiteralNode("Misiek"));

        InvocationNode invocationNode = new InvocationNode("df", "getDog",
                params);

        BinaryOperatorNode assigmentNode = new BinaryOperatorNode(
                new LiteralNode("dog"), invocationNode,
                BinaryOperatorType.BASIC_ASSIGMENT);

        BinaryOperatorNode assigmentNode2 = new BinaryOperatorNode(
                new LiteralNode("a"), new LiteralNode(true),
                BinaryOperatorType.BASIC_ASSIGMENT);

        List<IExpressionNode> params2 = new LinkedList<IExpressionNode>();
        params2.add(new LiteralNode(3.3));
        
        UserObjectMethodInvocationNode test = new UserObjectMethodInvocationNode(
                "dog", "setNumber", params2);
        
        List<IExpressionNode> params3 = new LinkedList<IExpressionNode>();
        UserObjectMethodInvocationNode test1 = new UserObjectMethodInvocationNode(
                "dog", "present", params3);

        BinaryOperatorNode assigment3 = new BinaryOperatorNode(new LiteralNode(
                "wynikFunkcji"), test1, BinaryOperatorType.BASIC_ASSIGMENT);

        BinaryOperatorNode aa = new BinaryOperatorNode(new LiteralNode("q"),
                new VariableNode("dog"), BinaryOperatorType.BASIC_ASSIGMENT);

        root.addChild(importNode);
        root.addChild(assigmentNode);
        root.addChild(assigmentNode2);
        root.addChild(aa);
        root.addChild(test);
        root.addChild(assigment3);
        root.accept(visitor);
        visitor.printContext();
        // Map<String, Object> context = visitor.getContext();
        // Object dog = context.get("dog");
        // Method method = dog.getClass().getMethod("bark", null);
        // method.invoke(dog, null);
        // Method setNumber = dog.getClass().getMethod("setNumber",
        // Integer.class);
        // setNumber.invoke(dog, new Integer(3));
        // Method getNumber = dog.getClass().getMethod("getNumber", null);
        // Integer number = (Integer) getNumber.invoke(dog, null);
        // System.out.println(number);
        // Class type = context.get("a").getClass();
        // System.out.println(type);
        // if ((Number.class.isAssignableFrom(type))
        // || (type.equals(String.class)) || (type.equals(Boolean.class))) {
        // System.out.println("Jest typem wbudowanym");
        // } else {
        // System.out.println("Jest typem złożonym");
        // }
        // for (String a : context.keySet()) {
        // System.out.println(context.get(a).getClass());
        // }

    }
}
