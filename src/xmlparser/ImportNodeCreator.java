package xmlparser;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.traversal.TreeWalker;

import ast.INode;
import ast.ImportNode;

public class ImportNodeCreator extends AstNodeCreator {
    Node node;
    TreeWalker walker;
    Parser parser;

    public ImportNodeCreator(Node node, TreeWalker walker, Parser parser) {
        this.node = node;
        this.walker = walker;
        this.parser = parser;
    }

    @Override
    public INode create() {
        NamedNodeMap attributes = node.getAttributes();
        ImportNode result = new ImportNode(attributes.getNamedItem("class")
                .getNodeValue(), attributes.getNamedItem("alias")
                .getNodeValue());
        return result;
    }

}
