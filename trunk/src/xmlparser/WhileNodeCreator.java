package xmlparser;

import org.w3c.dom.Node;
import org.w3c.dom.traversal.TreeWalker;

import ast.GroupNode;
import ast.IExpressionNode;
import ast.INode;
import ast.WhileNode;

public class WhileNodeCreator extends AstNodeCreator {
    Node node;
    TreeWalker walker;
    Parser parser;

    public WhileNodeCreator(Node node, TreeWalker walker, Parser parser) {
        this.node = node;
        this.walker = walker;
        this.parser = parser;
    }

    @Override
    public INode create() {
        IExpressionNode condition = null;
        GroupNode codeBlock = new GroupNode();
        for (Node n = walker.firstChild(); n != null; n = walker.nextSibling()) {
            if (n.getNodeName().equals("condition")) {
                condition = (IExpressionNode) parser.parseNode(walker
                        .firstChild());
                walker.setCurrentNode(n);
            } else if (n.getNodeName().equals("action")) {
                for (Node m = walker.firstChild(); m != null; m = walker
                        .nextSibling()) {
                    codeBlock.addChild(parser.parseNode(m));
                    walker.setCurrentNode(m);
                }
            } else {
                throw new RuntimeException();
            }
        }
        WhileNode whileNode = new WhileNode(condition, codeBlock);
        return whileNode;
    }
}
