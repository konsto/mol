package xmlparser;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.traversal.DocumentTraversal;
import org.w3c.dom.traversal.NodeFilter;
import org.w3c.dom.traversal.TreeWalker;

import ast.GroupNode;
import ast.INode;

public class Parser {
    DocumentBuilderFactory builder;
    GroupNode root = null;
    TreeWalker walker;
    Document document;
    DocumentTraversal traversal;
    MyNodeFilter filter;
    AstNodeCreator creator;

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
        for (Node n = walker.firstChild(); n != null; n = walker.nextSibling()) {
            INode child = parseNode(n);
            root.addChild(child);
        }
        return root;
    }

    public INode parseNode(Node node) {
        INode result = null;
        creator = AstNodeCreator.getInstance(node, walker, this);
        result = creator.create();
        walker.setCurrentNode(node);
        return result;
    }
}
