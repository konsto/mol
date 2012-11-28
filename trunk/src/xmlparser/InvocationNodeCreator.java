package xmlparser;

import java.util.LinkedList;
import java.util.List;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.traversal.TreeWalker;

import ast.IExpressionNode;
import ast.INode;
import ast.InvocationNode;

public class InvocationNodeCreator extends AstNodeCreator {
    Node node;
    TreeWalker walker;
    Parser parser;

    public InvocationNodeCreator(Node node, TreeWalker walker, Parser parser) {
        this.node = node;
        this.walker = walker;
        this.parser = parser;
    }

    @Override
    public INode create() {
        NamedNodeMap attributes = node.getAttributes();
        String method = attributes.getNamedItem("method").getNodeValue();
        IExpressionNode target = (IExpressionNode) parser.parseNode(walker
                .firstChild());
        List<IExpressionNode> params = new LinkedList<IExpressionNode>();
        for (Node n = walker.nextSibling(); n != null; n = walker.nextSibling()) {
            params.add((IExpressionNode) parser.parseNode(n));
        }
        InvocationNode result = new InvocationNode(target, method, params);
        return result;
    }
}
