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
        TreeWalker outerWalker = traversal.createTreeWalker(
                document.getDocumentElement(), NodeFilter.SHOW_ELEMENT, filter,
                false);
        outerWalker.setCurrentNode(branch);
        IExpressionNode condition = null;
        GroupNode branchCodeBlock = new GroupNode();
        for (Node n = outerWalker.firstChild(); n != null; n = outerWalker
                .nextSibling()) {
            if (n.getNodeName().equals("condition")) {
                condition = (IExpressionNode) parseNode(outerWalker
                        .firstChild());
                 outerWalker.setCurrentNode(n);
            } else if (n.getNodeName().equals("action")) {
                TreeWalker innerWalker = traversal.createTreeWalker(
                        document.getDocumentElement(), NodeFilter.SHOW_ELEMENT,
                        filter, false);
                innerWalker.setCurrentNode(outerWalker.getCurrentNode());
                for (Node m = innerWalker.firstChild(); m != null; m = innerWalker
                        .nextSibling()) {
                    branchCodeBlock.addChild(parseNode(m));
                    System.out.println("A");
                }
            }
        }
        branches.put(condition, branchCodeBlock);
        // walker.setCurrentNode(branch);
    }

    private void createElseBranch(Node elseNode, GroupNode elseExpression) {
//        walker.setCurrentNode(elseNode);
        for (Node n = walker.firstChild(); n != null; n = walker.nextSibling()) {
            elseExpression.addChild(parseNode(n));
        }

        walker.setCurrentNode(elseNode);
    }

    private IExpressionNode createIfNode(Node n) {
//        walker.setCurrentNode(n);
        Map<IExpressionNode, GroupNode> ifs = new LinkedHashMap<IExpressionNode, GroupNode>();
        GroupNode elseExpression = new GroupNode();
        for (Node m = walker.firstChild(); m != null; m = walker.nextSibling()) {
            if (m.getNodeName().equals("branch")) {
                createIfBranch(m, ifs);
            } else if (m.getNodeName().equals("else")) {
                createElseBranch(m, elseExpression);
            }
        }
        walker.setCurrentNode(n);
        IfNode ifNode = new IfNode(ifs, elseExpression);
        return ifNode;
    }

    public INode parseNode(Node node) {
        INode result = null;
        NamedNodeMap attributes = node.getAttributes();
        String type = node.getNodeName();
        switch (type) {
        case "import":
            // System.out.println(attributes.getNamedItem("class").getNodeValue());
            result = new ImportNode(attributes.getNamedItem("class")
                    .getNodeValue(), attributes.getNamedItem("alias")
                    .getNodeValue());
            break;
        case "int":
            result = new LiteralNode(Integer.parseInt(attributes.getNamedItem(
                    "value").getNodeValue()));
            break;
        case "double":
            result = new LiteralNode(Double.parseDouble(attributes
                    .getNamedItem("value").getNodeValue()));
            break;
        case "string":
            result = new LiteralNode(attributes.getNamedItem("value")
                    .getNodeValue());
            break;
        case "var":
            result = new VariableNode(attributes.getNamedItem("identifier")
                    .getNodeValue());
            break;
        case "assigment":
            Node nn = walker.firstChild();
            // IExpressionNode temp = (IExpressionNode)
            // parseNode(walker.firstChild());
            result = new AssigmentNode(attributes.getNamedItem("identifier")
                    .getNodeValue(),
                    (IExpressionNode) parseNode(walker.firstChild()));
            break;
        case "call":
            String method = attributes.getNamedItem("method").getNodeValue();
            IExpressionNode target = (IExpressionNode) parseNode(walker
                    .firstChild());
            List<IExpressionNode> params = new LinkedList<IExpressionNode>();
            for (Node n = walker.nextSibling(); n != null; n = walker
                    .nextSibling()) {
                params.add((IExpressionNode) parseNode(n));
            }
            result = new InvocationNode(target, method, params);
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
            result = createUnaryNode(node, type);
            break;
        case "negation":
            result = createUnaryNode(node, type);
            break;
        case "if":
            result = createIfNode(node);
            break;
        }
        walker.setCurrentNode(node);
        return result;
    }
}
