package com.arjunsk.compiler.ck.visitor.semantic;

import static org.hamcrest.CoreMatchers.startsWith;

import com.arjunsk.compiler.ck.domain.tree.ParseTree;
import com.arjunsk.compiler.ck.exceptions.SemanticException;
import com.arjunsk.compiler.ck.lexer.Lexer;
import com.arjunsk.compiler.ck.parser.Parser;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class SemanticAnalyzerTest {

  @Rule public final ExpectedException exceptionCatcher = ExpectedException.none();

  @Test
  public void test_variable_not_declared() {
    // 1. Arrange
    String sourceCode = "a = 10\n" + "show b";
    Lexer lexer = new Lexer(sourceCode);

    Parser parser = new Parser(lexer);
    ParseTree tree = parser.parseProgram();

    // 3. Assert
    exceptionCatcher.expect(SemanticException.class);
    exceptionCatcher.expectMessage(startsWith("SHOW argument variable is not declared."));

    // 2. Act
    tree.accept(new SemanticAnalyzer());
  }
}
