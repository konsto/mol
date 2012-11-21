//import java.util.LinkedList;
//import java.util.List;
//
//
//import ast.AdditionNode;
//import ast.AssignmentNode;
//import ast.GroupNode;
//import ast.IExpressionNode;
//import ast.ImportNode;
//import ast.InvocationNode;
//import ast.LiteralNode;
//import ast.MultiplicationNode;
//import ast.VariableNode;
//
//public class Main {
//
//    /**
//     * @param args
//     */
//    public static void main(String[] args) {
//        ToyEvaluateVisitor visitor = new ToyEvaluateVisitor();
//        GroupNode root = new GroupNode();
//        root.addChild(new AssignmentNode("a", new LiteralNode(5)));
//        root.addChild(new AssignmentNode("b", new LiteralNode(10)));
//        root.addChild(new AssignmentNode("c", new AdditionNode(
//                new VariableNode("a"), new VariableNode("b"))));
//        root.addChild(new AssignmentNode("d", new MultiplicationNode(
//                new VariableNode("a"), new VariableNode("b"))));
//        root.addChild(new ImportNode("test_plugins.Person", "tp"));
//        root.addChild(new ImportNode("test_plugins.Mathematic", "m"));
//        List<IExpressionNode> callArgs = new LinkedList<IExpressionNode>();
//        callArgs.add(new LiteralNode("ALA"));
//        callArgs.add(new LiteralNode("KOTA"));
//        root.addChild(new AssignmentNode("e", new InvocationNode("tp",
//                "concatenate", callArgs)));
////        List<IExpressionNode> callArgs2 = new LinkedList<IExpressionNode>();
////        callArgs2.add(new VariableNode("b"));
////        root.addChild(new AssignmentNode("pierw", new InvocationNode("m",
////               "testFunction", callArgs2)));  
//        root.accept(visitor);
//        visitor.printContext();
//    }
//}
