import java.io.FileInputStream;
import java.io.FileNotFoundException;

import engine.IEngine;
import engine.XMLEngine;
import engine.XMLFileEngine;

public class Mol {
    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        IEngine engine = null;
        if (args.length == 1) {
            try {
                engine = new XMLFileEngine(args[0]);
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
                System.exit(0);
            }
        } else if (args.length > 1) {
            usage();
            System.exit(0);
        } else {
            engine = new XMLEngine();
        }
        engine.start();
    }

    private static void usage() {
        System.out.println("Usage: java mol [filepath]");
        System.out
                .println("If filepath not specified, starting interactive shell");

    }
}
