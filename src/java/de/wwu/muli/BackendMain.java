package de.wwu.muli;

import org.extendj.JavaCompiler;

public class BackendMain extends JavaCompiler {
    public BackendMain() {
        super("muli-lang");
    }

    public static void main(String args[]) {
        int exitCode = new BackendMain().run(args);
        if (exitCode != 0) {
            System.exit(exitCode);
        }
    }

    @Override
    protected void printUsage() {
        super.printUsage();
        System.out.println(
                    "  -XprettyPrint             Mode: Pretty-print the input file\n"
                  + "  -XstructuredPrint         Mode: Pretty-print the input file, augmented with structural information\n"
                  + "  -XdumpTree                Mode: Output the generated AST");

    }
}
