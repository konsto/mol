package engine;

import ast.IVisitor;

public class Engine {
    private Context context;
    private IVisitor visitor;
    
    public Engine() {
        this.context = new Context();
    }

}
