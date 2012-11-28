package xmlparser;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.traversal.TreeWalker;

import ast.INode;
import ast.VariableNode;

public class VariableNodeCreator extends AstNodeCreator {
    Node node;
    TreeWalker walker;
    Parser parser;

    public VariableNodeCreator(Node node, TreeWalker walker, Parser parser) {
        this.node = node;
        this.walker = walker;
        this.parser = parser;
    }

    @Override
    public INode create() {
        NamedNodeMap attributes = node.getAttributes();
        VariableNode result = new VariableNode(attributes.getNamedItem(
                "identifier").getNodeValue());
        return result;
    }
}
