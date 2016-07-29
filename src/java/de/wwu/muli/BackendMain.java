package de.wwu.muli;

import org.extendj.JavaCompiler;

public class BackendMain extends JavaCompiler {
    // Options:
    // -d XXXXX           Output directory XXXXX for resulting compilates.
    // -XprettyPrint      Pretty-prints the input source.
    // -XstructuredPrint  Similar to the above, but puts brackets around every expression (e.g. y - x - 1 => ((y - x) - 1).
    // -XdumpTree         Dumps the AST deducted from the input source.

    public BackendMain() {
        super("muli-lang");
    }

    public static void main(String args[]) {
        int exitCode = new BackendMain().run(args);
        if (exitCode != 0) {
            System.exit(exitCode);
        }
    }
}
