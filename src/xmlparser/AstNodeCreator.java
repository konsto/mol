package xmlparser;

import org.w3c.dom.Node;
import org.w3c.dom.traversal.TreeWalker;

import ast.INode;

public abstract class AstNodeCreator {

    public abstract INode create();

    public static AstNodeCreator getInstance(Node node, TreeWalker walker,
            Parser parser) {
        AstNodeCreator result = null;
        String type = node.getNodeName().toLowerCase();
        switch (type.toLowerCase()) {
        case "import":
            result = new ImportNodeCreator(node, walker, parser);
            break;
        case "int":
        case "double":
        case "string":
            result = new LiteralNodeCreator(node, walker, parser);
            break;
        case "var":
            result = new VariableNodeCreator(node, walker, parser);
            break;
        case "assigment":
            result = new AssigmentNodeCreator(node, walker, parser);
            break;
        case "call":
            result = new InvocationNodeCreator(node, walker, parser);
            break;
        // Binary operators
        case "addition":
        case "subtract":
        case "multiply":
        case "divide":
        case "equalto":
        case "notequalto":
        case "greaterthan":
        case "lessthan":
        case "greaterthanorequalto":
        case "lessthanorequalto":
            result = new BinaryNodeCreator(node, walker, parser);
            break;
        // Unary operators
        case "minus":
        case "negation":
            result = new UnaryNodeCreator(node, walker, parser);
            break;
        case "if":
            result = new IfNodeCreator(node, walker, parser);
            break;
        case "while":
            result = new WhileNodeCreator(node, walker, parser);
            break;
        case "for":
            result = new ForNodeCreator(node, walker, parser);
            break;
        default:
            throw new RuntimeException();
        }
        return result;
    }
}
