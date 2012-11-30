package engine;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import xmlparser.Parser;

import ast.GroupNode;
import ast.IVisitor;

public class XMLEngine implements IEngine {
    Scanner scanner;
    EvaluateVisitor visitor;
    GroupNode root;
    Parser parser;
    ByteArrayInputStream input;
    Context context;

    public XMLEngine(InputStream input) {
        this.scanner = new Scanner(input);
        root = new GroupNode();
        context = new Context();
    }

    public XMLEngine() {
        this(System.in);
    }

    @Override
    public void start() throws Exception {
        parser = new Parser();
        StringBuilder script = new StringBuilder();
        String line = "<program>";
        do {
            script.append(line);
            System.out.print(">> ");
            line = scanner.nextLine();
            if (line.equals("evaluate")) {
                evaluate(script);
                script = new StringBuilder();
                line = "<program>";
            } else if (line.startsWith("print")) {
                printElement(line);
                line = "";
            } else if (line.startsWith("script")) {
                // loadScript(line);
            }
        } while (!line.equals("exit"));
        scanner.close();
        reset();
    }

    private void evaluate(StringBuilder script) throws Exception {
        script.append("</program>");
        System.out.println(script.toString());
        input = new ByteArrayInputStream(script.toString().getBytes());
        parser.setUp(input);
        root = parser.parseProgram();
        visitor = new EvaluateVisitor(context);
        root.accept(visitor);
        printContext();
    }

    private void printElement(String line) throws Exception {
        String patternString1 = "(\\(.*\\))";
        Pattern pattern = Pattern.compile(patternString1);
        Matcher matcher = pattern.matcher(line);
        String arg = "";
        while (matcher.find()) {
            arg = matcher.group(1);
        }
        arg = arg.replaceAll("\\(", " ");
        arg = arg.replaceAll("\\)", " ");
        arg = arg.trim();
        System.out.println(getElement(arg).getValue());
    }

    public void reset() {
        context = new Context();
    }
    
    private void printContext() throws Exception {
        context.print();
    }
    
    private IObject getElement(String key) {
        return this.context.getEntry(key).getObject();
    }

}
