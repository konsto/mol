
import engine.EvaluateVisitor;

import ast.INode;
import xmlparser.Parser;

public class ParserDemo {

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        EvaluateVisitor visitor = new EvaluateVisitor();
        Parser parser = new Parser();
        parser.setUp("/home/michal/dev/mol-trunk/demo/script6.xml");
//        parser.printTree("");
        INode root = parser.parseProgram();
        root.accept(visitor);
        visitor.printContext();

    }
}
