package de.wwu.muli;

import org.extendj.JavaCompiler;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class BackendMain extends JavaCompiler {
    public BackendMain() {
        super("muli-lang");
    }

    public static void main(String args[]) {
        String subClassPath;
        try {
            // Export library (mulicp) to disk
            subClassPath = exportSubClassPath();
        } catch (IOException e) {
            throw new RuntimeException("Error: Could not export the required class path files to the filesystem. Aborting.", e);
        }

        // Add Muli classpath to compiler classpath
        final String augmentedArgs[] = new String[args.length+2];
        System.arraycopy(args, 0, augmentedArgs, 0, args.length);
        augmentedArgs[args.length] = "-classpath";
        augmentedArgs[args.length+1] = subClassPath;

        // TODO actually patch args: look for existing -classpath and add new path together with File.pathSeparator.

        // Begin backend execution
        int exitCode = new BackendMain().run(augmentedArgs);
        if (exitCode != 0) {
            System.exit(exitCode);
        }
    }

    private static String exportSubClassPath() throws IOException {
        File dest = File.createTempFile("mulicp", ".jar");
        dest.deleteOnExit();
        exportResource("/mulicp.jar", dest);
        return dest.toString();
    }

    /**
     * Export a resource embedded into the Jar file to the local file path.
     * Adapted from http://stackoverflow.com/a/13379744/96203
     * (original author: Ordiel@Stackoverflow)
     *
     * @param fromResourceName e.g. "/SmartLibrary.dll"
     * @param to Target File for output, created e.g. by File.createTempFile()
     * @throws IOException if anything goes wrong
     */
    static private void exportResource(String fromResourceName, File to) throws IOException {
        InputStream stream = null;
        OutputStream resStreamOut = null;
        try {
            stream = BackendMain.class.getResourceAsStream(fromResourceName);
            if(stream == null) {
                throw new IOException("Cannot get resource \"" + fromResourceName + "\" from Jar file.");
            }

            int readBytes;
            byte[] buffer = new byte[4096];
            resStreamOut = new FileOutputStream(to);
            while ((readBytes = stream.read(buffer)) > 0) {
                resStreamOut.write(buffer, 0, readBytes);
            }
        } catch (IOException ex) {
            throw ex;
        } finally {
            if (stream != null) {
                stream.close();
            }
            if (resStreamOut != null) {
                resStreamOut.close();
            }
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
