package ast;

public class PrintVisitor implements IVisitor {

    @Override
    public void visit(AssignmentNode node) {
        System.out.format("Assigment node (%s)", node.getIdentifier());
    }

    @Override
    public void visit(CommentNode node) {
        System.out.format("Comment node (%s)", node.getContent());
    }

    @Override
    public void visit(GroupNode node) {
        INodeIterator iter = node.getChildrenIterator();
        while (!iter.isDone()) {
            INode childNode = iter.currentItem();
            childNode.accept(this);
            iter.next();
        }
    }

    @Override
    public void visit(ImportNode node) {
        System.out.format("Import node (%s, %s)", node.getPluginName(),
                node.getAlias());
    }

    @Override
    public void visit(InvocationNode node) {
        System.out.format("Invocation node (%s, %s", node.getMethod(),
                node.getPluginAlias());
    }

    @Override
    public void visit(LiteralNode node) {
        System.out.format("Literal node (%s)", node.getValue().toString());
    }

    @Override
    public void visit(VariableNode node) {
        System.out.format("Variable node (%s)", node.getIdentifier());
    }
}
