package ast;

public class PrintVisitor implements IVisitor {

    @Override
    public void visit(AssignmentNode node) {
        System.out.format("Assigment node (%s)%n", node.getIdentifier());
    }

    @Override
    public void visit(CommentNode node) {
        System.out.format("Comment node (%s)%n", node.getContent());
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
        System.out.format("Import node (%s, %s)%n", node.getPluginName(),
                node.getAlias());
    }

    @Override
    public void visit(InvocationNode node) {
        System.out.format("Invocation node (%s, %s)%n", node.getMethod(),
                node.getPluginAlias());
    }

    @Override
    public void visit(LiteralNode node) {
        System.out.format("Literal node (%s)%n", node.getValue().toString());
    }

    @Override
    public void visit(VariableNode node) {
        System.out.format("Variable node (%s)%n", node.getIdentifier());
    }
}
