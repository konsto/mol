package xmlparser;

import org.w3c.dom.Node;
import org.w3c.dom.traversal.TreeWalker;

import ast.ForNode;
import ast.GroupNode;
import ast.IExpressionNode;
import ast.INode;

public class ForNodeCreator extends AstNodeCreator {
    Node node;
    TreeWalker walker;
    Parser parser;

    public ForNodeCreator(Node node, TreeWalker walker, Parser parser) {
        this.node = node;
        this.walker = walker;
        this.parser = parser;
    }

    @Override
    public INode create() {
        GroupNode initialization = new GroupNode();
        IExpressionNode condition = null;
        GroupNode codeBlock = new GroupNode();
        GroupNode afterBlock = new GroupNode();
        for (Node n = walker.firstChild(); n != null; n = walker.nextSibling()) {
            if (n.getNodeName().equals("initialization")) {
                for (Node m = walker.firstChild(); m != null; m = walker
                        .nextSibling()) {
                    initialization.addChild(parser.parseNode(m));
                    walker.setCurrentNode(m);
                }
                walker.setCurrentNode(n);
            } else if (n.getNodeName().equals("condition")) {
                condition = (IExpressionNode) parser.parseNode(walker
                        .firstChild());
                walker.setCurrentNode(n);
            } else if (n.getNodeName().equals("after")) {
                for (Node m = walker.firstChild(); m != null; m = walker
                        .nextSibling()) {
                    afterBlock.addChild(parser.parseNode(m));
                    walker.setCurrentNode(m);
                }
                walker.setCurrentNode(n);
            } else if (n.getNodeName().equals("action")) {
                for (Node m = walker.firstChild(); m != null; m = walker
                        .nextSibling()) {
                    codeBlock.addChild(parser.parseNode(m));
                    walker.setCurrentNode(m);
                }
                walker.setCurrentNode(n);
            } else {
                throw new RuntimeException();
            }
        }
        ForNode result = new ForNode(initialization, condition, codeBlock,
                afterBlock);
        return result;
    }

}
