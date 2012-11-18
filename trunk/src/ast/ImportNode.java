package ast;

public class ImportNode implements INode {

    private String pluginName;
    private String alias;

    public ImportNode(String pluginName, String alias) {
        this.pluginName = pluginName;
        this.alias = alias;
    }

    public String getPluginName() {
        return this.pluginName;
    }

    public String getAlias() {
        return this.alias;
    }
}
