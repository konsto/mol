package ast;

import java.util.ArrayList;
import java.util.List;

public class GroupNode implements INode {

    private List<INode> children;

    public GroupNode() {
        this.children = new ArrayList<INode>();
    }

    public void addChild(INode node) {
        children.add(node);
    }

    public void accept(IVisitor visitor) {
//        for (INode child : children) {
//            child.accept(visitor);
//        }
//        visitor.visit(this);
    }
    
    public INodeIterator getChildrenIterator()
    {
        return new GroupNodeForwardIterator(children);
    }
}
