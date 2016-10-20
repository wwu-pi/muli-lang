package de.wwu.muli;

import org.extendj.JavaCompiler;

import java.util.Arrays;

public class BackendMain extends JavaCompiler {
    public BackendMain() {
        super("muli-lang");
    }

    public static void main(String args[]) {
        // Add Muli classpath to compiler classpath
        final String augmentedArgs[] = new String[args.length+2];
        System.arraycopy(args, 0, augmentedArgs, 0, args.length);
        augmentedArgs[args.length] = "-classpath";
        augmentedArgs[args.length+1] = "../muli-cp/build/classes/main/"; // TODO add a reliable (absolute?) path here

        // TODO actually patch args: look for existing -classpath and add new path together with File.pathSeparator.

        // Begin backend execution
        int exitCode = new BackendMain().run(augmentedArgs);
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
