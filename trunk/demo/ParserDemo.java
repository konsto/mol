import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.traversal.DocumentTraversal;
import org.w3c.dom.traversal.NodeFilter;
import org.w3c.dom.traversal.NodeIterator;
import org.w3c.dom.traversal.TreeWalker;
import org.xml.sax.SAXException;

import engine.EvaluateVisitor;

import ast.AssigmentNode;
import ast.GroupNode;
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
        parser.setUp("/home/michal/dev/mol-trunk/demo/script4.xml");
//        parser.printTree("");
        INode root = parser.parseProgram();
        root.accept(visitor);
        visitor.printContext();

    }
}
