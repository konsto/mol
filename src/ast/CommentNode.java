package ast;

public class CommentNode implements INode {

    private String content;

    public CommentNode(String content) {
        this.content = content;
    }

    public String getContent() {
        return this.content;
    }
    
    @Override
    public void accept(IVisitor visitor) throws Exception {
        visitor.visit(this);
        
    }
}
