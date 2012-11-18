package ast;

public interface INodeIterator {
    public void first();

    public void next();

    public boolean isDone();

    public INode currentItem();

}
