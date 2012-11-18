package ast;

import java.util.List;

public class GroupNodeForwardIterator implements INodeIterator {

    private List<INode> nodeList;
    private int current;

    public GroupNodeForwardIterator(List<INode> nodeList) {
        this.nodeList = nodeList;
        this.current = 0;
    }

    @Override
    public void first() {
        this.current = 0;
    }

    @Override
    public void next() {
        this.current++;
    }

    @Override
    public boolean isDone() {
        return this.current >= nodeList.size();
    }

    @Override
    public INode currentItem() throws IndexOutOfBoundsException {
        if (isDone()) {
            throw new IndexOutOfBoundsException();
        }
        return this.nodeList.get(current);
    }

}
