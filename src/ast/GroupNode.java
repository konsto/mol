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
}
