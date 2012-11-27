package xmlparser;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.traversal.TreeWalker;

import engine.IObject;

import ast.AssigmentNode;
import ast.GroupNode;
import ast.IExpressionNode;
import ast.INode;
import ast.ImportNode;
import ast.LiteralNode;

public class NodeFactory {
    TreeWalker walker;
    GroupNode root;
    INode temp;
    int i = 0;

    public NodeFactory(TreeWalker walker) {
        this.walker = walker;
        root = new GroupNode();
    }

    public void printTree(String indent) {
        Node parent = walker.getCurrentNode();
        System.out.println("[" + i + "]" + indent + "- "
                + ((Element) parent).getTagName());
        i++;
        for (Node n = walker.firstChild(); n != null; n = walker.nextSibling()) {
            printTree(indent + "    ");
        }
        walker.setCurrentNode(parent);
    }

    public void evaluate() {
        Node parent = walker.getCurrentNode();
        for (Node n = walker.firstChild(); n != null; n = walker.nextSibling()) {
            evaluate();
        }
    }

    public INode create(Node treeNode) {
        String type = treeNode.getNodeName();
        switch (type) {
        case "ImportNode":
            root.addChild(new ImportNode(((Element) treeNode)
                    .getAttribute("moduleName"), ((Element) treeNode)
                    .getAttribute("alias")));
            break;
        }
        return null;
    }

    public GroupNode getRoot() {
        return this.root;
    }

    public INode processNode(Node treeNode) {
        String type = treeNode.getNodeName();
        if (type.equals("AssigmentNode")) {
            AssigmentNode node = new AssigmentNode(
                    ((Element) treeNode).getAttribute("identifier"),
                    (IExpressionNode) processNode(treeNode.getFirstChild()));
            root.addChild(node);
        } else if (type.equals("LiteralNode")) {
            LiteralNode node = new LiteralNode(
                    ((Element) treeNode).getAttribute("value"));
        }
        return null;
    }
}
