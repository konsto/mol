package xmlparser;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.traversal.TreeWalker;

import ast.AssigmentNode;
import ast.IExpressionNode;
import ast.INode;

public class AssigmentNodeCreator extends AstNodeCreator {
    Node node;
    TreeWalker walker;
    Parser parser;

    public AssigmentNodeCreator(Node node, TreeWalker walker, Parser parser) {
        this.node = node;
        this.walker = walker;
        this.parser = parser;
    }

    @Override
    public INode create() {
        NamedNodeMap attributes = node.getAttributes();
        AssigmentNode result = new AssigmentNode(attributes.getNamedItem(
                "identifier").getNodeValue(),
                (IExpressionNode) parser.parseNode(walker.firstChild()));
        return result;
    }
}
