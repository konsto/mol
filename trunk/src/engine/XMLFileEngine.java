package engine;

import java.io.FileInputStream;
import xmlparser.Parser;
import ast.GroupNode;
import ast.IVisitor;

public class XMLFileEngine implements IEngine {
    FileInputStream fileStream;
    IVisitor visitor;
    GroupNode root;
    Parser parser;
    Context context;

    public XMLFileEngine(String inputPath) throws Exception {
        fileStream = new FileInputStream(inputPath);
        visitor = new EvaluateVisitor(context);
        root = new GroupNode();
        parser = new Parser();

    }

    @Override
    public void start() throws Exception {
        parser.setUp(fileStream);
        root = parser.parseProgram();
        root.accept(visitor);
        ((EvaluateVisitor) visitor).printContext();

    }

}
