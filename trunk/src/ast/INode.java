package ast;

public interface INode {

    public void accept(IVisitor visitor) throws Exception;
}
