package com.arjunsk.parser.javaparser;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.stmt.Statement;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class JavaparserDriver {

  public static void main(String[] args) {
    String code = getResourceFileAsString("input/SimpleProgram.java");
    assert code != null;

    parse(code);
  }

  /**
   * Identify all Statements and see if it is an IfStatement. If yes, print the content of Statement
   * Object.
   */
  private static void parse(String code) {
    CompilationUnit cu = StaticJavaParser.parse(code);

    cu.findAll(Statement.class)
        .forEach(
            statement -> {
              if (statement.isIfStmt()) {
                System.out.println("If statement content\n" + statement);
              }
            });
  }

  /** Read String from File. */
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
