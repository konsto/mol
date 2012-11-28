package xmlparser;

import java.util.LinkedHashMap;
import java.util.Map;

import org.w3c.dom.Node;
import org.w3c.dom.traversal.TreeWalker;

import ast.GroupNode;
import ast.IExpressionNode;
import ast.INode;
import ast.IfNode;

public class IfNodeCreator extends AstNodeCreator {
    Node node;
    TreeWalker walker;
    Parser parser;

    public IfNodeCreator(Node node, TreeWalker walker, Parser parser) {
        this.node = node;
        this.walker = walker;
        this.parser = parser;
    }

    @Override
    public INode create() {
        Map<IExpressionNode, GroupNode> ifs = new LinkedHashMap<IExpressionNode, GroupNode>();
        GroupNode elseExpression = new GroupNode();
        for (Node m = walker.firstChild(); m != null; m = walker.nextSibling()) {
            if (m.getNodeName().equals("branch")) {
                createIfBranch(m, ifs);
            } else if (m.getNodeName().equals("else")) {
                createElseBranch(m, elseExpression);
            } else {
                throw new RuntimeException();
            }
        }
        IfNode ifNode = new IfNode(ifs, elseExpression);
        return ifNode;
    }

    private void createIfBranch(Node branch,
            Map<IExpressionNode, GroupNode> branches) {
        IExpressionNode condition = null;
        GroupNode branchCodeBlock = new GroupNode();
        for (Node n = walker.firstChild(); n != null; n = walker.nextSibling()) {
            if (n.getNodeName().equals("condition")) {
                condition = (IExpressionNode) parser.parseNode(walker
                        .firstChild());
                walker.setCurrentNode(n);
            } else if (n.getNodeName().equals("action")) {
                for (Node m = walker.firstChild(); m != null; m = walker
                        .nextSibling()) {
                    branchCodeBlock.addChild(parser.parseNode(m));
                }
            }
        }
        branches.put(condition, branchCodeBlock);
        walker.setCurrentNode(branch);
    }

    private void createElseBranch(Node elseNode, GroupNode elseExpression) {
        // walker.setCurrentNode(elseNode);
        for (Node n = walker.firstChild(); n != null; n = walker.nextSibling()) {
            elseExpression.addChild(parser.parseNode(n));
        }
        walker.setCurrentNode(elseNode);
    }
}
