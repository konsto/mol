package xmlparser;

import org.w3c.dom.Node;
import org.w3c.dom.traversal.TreeWalker;

import ast.BinaryOperatorNode;
import ast.BinaryOperatorType;
import ast.IExpressionNode;
import ast.INode;

public class BinaryNodeCreator extends AstNodeCreator {
    Node node;
    TreeWalker walker;
    Parser parser;

    public BinaryNodeCreator(Node node, TreeWalker walker, Parser parser) {
        this.node = node;
        this.walker = walker;
        this.parser = parser;
    }

    @Override
    public INode create() {
        String type = node.getNodeName();
        BinaryOperatorType operator = BinaryOperatorType.getEnum(type);
        IExpressionNode left = (IExpressionNode) parser.parseNode(walker
                .firstChild());
        IExpressionNode right = (IExpressionNode) parser.parseNode(walker
                .nextSibling());
        BinaryOperatorNode binaryNode = new BinaryOperatorNode(left, right,
                operator);
        walker.setCurrentNode(node);
        return binaryNode;
    }
}
