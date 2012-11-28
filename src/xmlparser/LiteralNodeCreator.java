package xmlparser;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.traversal.TreeWalker;

import ast.INode;
import ast.LiteralNode;

public class LiteralNodeCreator extends AstNodeCreator {
    Node node;
    TreeWalker walker;
    Parser parser;

    public LiteralNodeCreator(Node node, TreeWalker walker, Parser parser) {
        this.node = node;
        this.walker = walker;
        this.parser = parser;
    }

    @Override
    public INode create() {
        NamedNodeMap attributes = node.getAttributes();
        Object arg = null;
        String type = node.getNodeName().toLowerCase();
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

}
