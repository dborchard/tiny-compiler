package com.arjunsk.compiler.lisp.core.parser;

import static org.junit.Assert.assertNotNull;

import com.arjunsk.compiler.lisp.core.lexer.Lexer;
import com.arjunsk.compiler.lisp.domain.ast.AstNode;
import com.arjunsk.compiler.lisp.domain.lexer.Token;
import com.arjunsk.compiler.utils.FileReaderUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.junit.Test;

public class ParserTest {

  @Test
  public void test_parse() {

    // 1. Arrange
    String hllCode =
        Objects.requireNonNull(FileReaderUtil.getResourceFileAsString("input/code.lsp"));
    List<Token> tokens = new Lexer().tokenize(hllCode);

    // 2. Act
    AstNode root = new Parser(tokens).parse();

    // 3. Assert
    assertNotNull(root);

    // 4. Log
    System.out.println(printAst(root));
  }

  private String printAst(AstNode root) {

    if (root == null) return "";

    StringBuilder sb = new StringBuilder();
    sb.append(" Type: ")
        .append(root.getType())
        .append(" Value: ")
        .append(root.getValue())
        .append("\n");

    List<String> children = new ArrayList<>();
    root.getParams()
        .forEachRemaining(
            item -> {
              String child = printAst(item);
              if (!child.isEmpty()) children.add(child);
            });
    if (!children.isEmpty()) {
      sb.append("[").append("\n");
      children.forEach(sb::append);
      sb.append("]").append("\n");
    }

    return sb.toString();
  }
}
