package ast;

public class CommentNode implements INode {

    private String content;

    public CommentNode(String content) {
        this.content = content;
    }

    public String getContent() {
        return this.content;
    }
}
