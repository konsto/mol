package ast;

public class ImportNode implements INode {

    private String moduleName;
    private String alias;

    public ImportNode(String moduleName, String alias) {
        this.moduleName = moduleName;
        this.alias = alias;
    }

    public String getModuleName() {
        return this.moduleName;
    }

    public String getAlias() {
        return this.alias;
    }

    @Override
    public void accept(IVisitor visitor) throws Exception {
        visitor.visit(this);

    }
}
