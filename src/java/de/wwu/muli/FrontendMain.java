package de.wwu.muli;

import org.extendj.JavaChecker;
import org.extendj.ast.CompilationUnit;

public class FrontendMain extends JavaChecker {

  public static void main(String args[]) {
    int exitCode = new FrontendMain().run(args);
    if (exitCode != 0) {
      System.exit(exitCode);
    }
  }

  @Override
  protected int processCompilationUnit(CompilationUnit unit) throws Error {
    // Replace the following super call to skip semantic error checking in unit.
    return super.processCompilationUnit(unit);
  }

  /** Called by processCompilationUnit when there are no errors in the argument unit.  */
  @Override
  protected void processNoErrors(CompilationUnit unit) {
    unit.process();
  }
}
