package com.arjunsk.parser.jdt;

import com.arjunsk.parser.jdt.ast.JdtAstVisitor;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;

public class JdtDriver {

  public static void main(String[] args) {
    String javaCode = getResourceFileAsString("input/SimpleProgram.java");
    assert javaCode != null;

    ASTParser parser = ASTParser.newParser(AST.JLS15);
    parser.setSource(javaCode.toCharArray()); // setting source code.
    parser.setKind(ASTParser.K_COMPILATION_UNIT);
    final CompilationUnit cu = (CompilationUnit) parser.createAST(null); // Root of tree

    cu.accept(new JdtAstVisitor(cu));
  }

  /** Read File as String. */
  public static String getResourceFileAsString(String fileName) {
    ClassLoader classLoader = ClassLoader.getSystemClassLoader();
    try (InputStream is = classLoader.getResourceAsStream(fileName)) {
      if (is == null) return null;
      try (InputStreamReader isr = new InputStreamReader(is);
          BufferedReader reader = new BufferedReader(isr)) {
        return reader.lines().collect(Collectors.joining(System.lineSeparator()));
      }
    } catch (Exception ex) {
      throw new RuntimeException("Error reading file", ex);
    }
  }
}
