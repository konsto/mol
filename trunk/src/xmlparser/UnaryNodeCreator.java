package xmlparser;

import org.w3c.dom.Node;
import org.w3c.dom.traversal.TreeWalker;

import ast.IExpressionNode;
import ast.INode;
import ast.UnaryOperatorNode;
import ast.UnaryOperatorType;

public class UnaryNodeCreator extends AstNodeCreator {
    Node node;
    TreeWalker walker;
    Parser parser;

    public UnaryNodeCreator(Node node, TreeWalker walker, Parser parser) {
        this.node = node;
        this.walker = walker;
        this.parser = parser;
    }

    @Override
    public INode create() {
        walker.setCurrentNode(node);
        String type = node.getNodeName();
        UnaryOperatorType operator = UnaryOperatorType.getEnum(type);
        IExpressionNode operand = (IExpressionNode) parser.parseNode(walker
                .firstChild());
        UnaryOperatorNode unaryNode = new UnaryOperatorNode(operand, operator);
        walker.setCurrentNode(node);
        return unaryNode;
    }

}
