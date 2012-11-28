package xmlparser;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.traversal.DocumentTraversal;
import org.w3c.dom.traversal.NodeFilter;
import org.w3c.dom.traversal.TreeWalker;

import ast.AssigmentNode;
import ast.BinaryOperatorNode;
import ast.BinaryOperatorType;
import ast.ForNode;
import ast.GroupNode;
import ast.IExpressionNode;
import ast.INode;
import ast.IfNode;
import ast.ImportNode;
import ast.InvocationNode;
import ast.LiteralNode;
import ast.UnaryOperatorNode;
import ast.UnaryOperatorType;
import ast.VariableNode;
import ast.WhileNode;

public class Parser {
    DocumentBuilderFactory builder;
    GroupNode root = null;
    TreeWalker walker;
    Document document;
    DocumentTraversal traversal;
    MyNodeFilter filter;

    public Parser() throws ParserConfigurationException {
        builder = DocumentBuilderFactory.newInstance();
    }

    public void setUp(String filepath) throws Exception {
        document = builder.newDocumentBuilder().parse(filepath);
        traversal = (DocumentTraversal) document;
        filter = new MyNodeFilter();
        walker = traversal.createTreeWalker(document.getDocumentElement(),
                NodeFilter.SHOW_ELEMENT, filter, false);
    }

    public void printTree(String indent) {
        Node parent = walker.getCurrentNode();
        System.out.println(indent + "- " + parent.getNodeName());
        for (Node n = walker.firstChild(); n != null; n = walker.nextSibling()) {
            printTree(indent + "    ");
        }
        walker.setCurrentNode(parent);
    }

    public GroupNode getRoot() {
        return root;
    }

    public INode parseProgram() {
        root = new GroupNode();

        Node programNode = walker.getCurrentNode();
        for (Node n = walker.firstChild(); n != null; n = walker.nextSibling()) {
            INode child = parseNode(n);
            root.addChild(child);
        }
        return root;
    }

    public INode parseNode(Node node) {
        INode result = null;
        String type = node.getNodeName();
        switch (type) {
        case "import":
            result = createImportNode(node);
            break;
        case "int":
        case "double":
        case "string":
            result = createLiteralNode(node, type);
            break;
        case "var":
            result = createVariableNode(node);
            break;
        case "assigment":
            result = createAssigmentNode(node);
            break;
        case "call":
            result = createInvocationNode(node);
            break;
        // Binary operators
        case "addition":
        case "subtract":
        case "multiply":
        case "divide":
        case "equalTo":
        case "notEqualTo":
        case "greaterThan":
        case "lessThan":
        case "greaterThanOrEqualTo":
        case "lessThanOrEqualTo":
            result = createBinaryNode(node, type);
            break;
        // Unary operators
        case "minus":
        case "negation":
            result = createUnaryNode(node, type);
            break;
        case "if":
            result = createIfNode(node);
            break;
        case "while":
            result = createWhileNode(node);
            break;
        case "for":
            result = createForNode(node);
            break;
        default:
            throw new RuntimeException();
        }
        walker.setCurrentNode(node);
        return result;
    }

    private ForNode createForNode(Node node) {
        GroupNode initialization = new GroupNode();
        IExpressionNode condition = null;
        GroupNode codeBlock = new GroupNode();
        GroupNode afterBlock = new GroupNode();
        for (Node n = walker.firstChild(); n != null; n = walker.nextSibling()) {
            if (n.getNodeName().equals("initialization")) {
                for (Node m = walker.firstChild(); m != null; m = walker
                        .nextSibling()) {
                    initialization.addChild(parseNode(m));
                    walker.setCurrentNode(m);
                }
                walker.setCurrentNode(n);
            } else if (n.getNodeName().equals("condition")) {
                condition = (IExpressionNode) parseNode(walker.firstChild());
                walker.setCurrentNode(n);
            } else if (n.getNodeName().equals("after")) {
                for (Node m = walker.firstChild(); m != null; m = walker
                        .nextSibling()) {
                    afterBlock.addChild(parseNode(m));
                    walker.setCurrentNode(m);
                }
                walker.setCurrentNode(n);
            } else if (n.getNodeName().equals("action")) {
                for (Node m = walker.firstChild(); m != null; m = walker
                        .nextSibling()) {
                    codeBlock.addChild(parseNode(m));
                    walker.setCurrentNode(m);
                }
                walker.setCurrentNode(n);
            } else {
                throw new RuntimeException();
            }
        }
        ForNode result = new ForNode(initialization, condition, codeBlock,
                afterBlock);
        return result;
    }

    private WhileNode createWhileNode(Node node) {
        IExpressionNode condition = null;
        GroupNode codeBlock = new GroupNode();
        for (Node n = walker.firstChild(); n != null; n = walker.nextSibling()) {
            if (n.getNodeName().equals("condition")) {
                condition = (IExpressionNode) parseNode(walker.firstChild());
                walker.setCurrentNode(n);
            } else if (n.getNodeName().equals("action")) {
                for (Node m = walker.firstChild(); m != null; m = walker
                        .nextSibling()) {
                    codeBlock.addChild(parseNode(m));
                    walker.setCurrentNode(m);
                }
            } else {
                throw new RuntimeException();
            }
        }
        WhileNode whileNode = new WhileNode(condition, codeBlock);
        return whileNode;
    }

    private ImportNode createImportNode(Node node) {
        NamedNodeMap attributes = node.getAttributes();
        ImportNode result = new ImportNode(attributes.getNamedItem("class")
                .getNodeValue(), attributes.getNamedItem("alias")
                .getNodeValue());
        return result;
    }

    private VariableNode createVariableNode(Node node) {
        NamedNodeMap attributes = node.getAttributes();
        VariableNode result = new VariableNode(attributes.getNamedItem(
                "identifier").getNodeValue());
        return result;
    }

    private LiteralNode createLiteralNode(Node node, String type) {
        NamedNodeMap attributes = node.getAttributes();
        Object arg = null;
        switch (type.toLowerCase()) {
        case "double":
            arg = Double.parseDouble(attributes.getNamedItem("value")
                    .getNodeValue());
            break;
        case "int":
            arg = Integer.parseInt(attributes.getNamedItem("value")
                    .getNodeValue());
            break;
        case "string":
            arg = attributes.getNamedItem("value").getNodeValue();
            break;
        default:
            throw new RuntimeException();
        }
        LiteralNode result = new LiteralNode(arg);
        return result;
    }

    private InvocationNode createInvocationNode(Node node) {
        NamedNodeMap attributes = node.getAttributes();
        String method = attributes.getNamedItem("method").getNodeValue();
        IExpressionNode target = (IExpressionNode) parseNode(walker
                .firstChild());
        List<IExpressionNode> params = new LinkedList<IExpressionNode>();
        for (Node n = walker.nextSibling(); n != null; n = walker.nextSibling()) {
            params.add((IExpressionNode) parseNode(n));
        }
        InvocationNode result = new InvocationNode(target, method, params);
        return result;
    }

    private AssigmentNode createAssigmentNode(Node node) {
        NamedNodeMap attributes = node.getAttributes();
        AssigmentNode result = new AssigmentNode(attributes.getNamedItem(
                "identifier").getNodeValue(),
                (IExpressionNode) parseNode(walker.firstChild()));
        return result;
    }

    private IExpressionNode createBinaryNode(Node n, String type) {
        walker.setCurrentNode(n);
        BinaryOperatorType operator = BinaryOperatorType.getEnum(type);
        IExpressionNode left = (IExpressionNode) parseNode(walker.firstChild());
        IExpressionNode right = (IExpressionNode) parseNode(walker
                .nextSibling());
        BinaryOperatorNode binaryNode = new BinaryOperatorNode(left, right,
                operator);
        walker.setCurrentNode(n);
        return binaryNode;
    }

    private IExpressionNode createUnaryNode(Node n, String type) {
        walker.setCurrentNode(n);
        UnaryOperatorType operator = UnaryOperatorType.getEnum(type);
        IExpressionNode operand = (IExpressionNode) parseNode(walker
                .firstChild());
        UnaryOperatorNode unaryNode = new UnaryOperatorNode(operand, operator);
        walker.setCurrentNode(n);
        return unaryNode;
    }

    private void createIfBranch(Node branch,
            Map<IExpressionNode, GroupNode> branches) {
        IExpressionNode condition = null;
        GroupNode branchCodeBlock = new GroupNode();
        for (Node n = walker.firstChild(); n != null; n = walker.nextSibling()) {
            if (n.getNodeName().equals("condition")) {
                condition = (IExpressionNode) parseNode(walker.firstChild());
                walker.setCurrentNode(n);
            } else if (n.getNodeName().equals("action")) {
                for (Node m = walker.firstChild(); m != null; m = walker
                        .nextSibling()) {
                    branchCodeBlock.addChild(parseNode(m));
                }
            }
        }
        branches.put(condition, branchCodeBlock);
        walker.setCurrentNode(branch);
    }

    private void createElseBranch(Node elseNode, GroupNode elseExpression) {
        // walker.setCurrentNode(elseNode);
        for (Node n = walker.firstChild(); n != null; n = walker.nextSibling()) {
            elseExpression.addChild(parseNode(n));
        }

        walker.setCurrentNode(elseNode);
    }

    private IExpressionNode createIfNode(Node n) {
        Map<IExpressionNode, GroupNode> ifs = new LinkedHashMap<IExpressionNode, GroupNode>();
        GroupNode elseExpression = new GroupNode();
        for (Node m = walker.firstChild(); m != null; m = walker.nextSibling()) {
            if (m.getNodeName().equals("branch")) {
                createIfBranch(m, ifs);
            } else if (m.getNodeName().equals("else")) {
                createElseBranch(m, elseExpression);
            } else {
                throw new RuntimeException();
            }
        }
        IfNode ifNode = new IfNode(ifs, elseExpression);
        return ifNode;
    }
}
