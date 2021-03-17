package com.arjunsk.compiler.svg.core.parsing;

import com.arjunsk.compiler.svg.domain.lexer.ast.AstNode;
import com.arjunsk.compiler.svg.domain.lexer.token.Token;
import com.arjunsk.compiler.utils.FileReaderUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.junit.Assert;
import org.junit.Test;

public class ParserTest {

  @Test
  public void test_parse() {

    // 1. Arrange
    String hllCode = Objects.requireNonNull(FileReaderUtil.getResourceFileAsString("lexer/code.ck"));
    List<Token> tokens = Lexer.tokenize(hllCode);

    // 2. Act
    AstNode result = new Parser(tokens).parse();

    // 3. Assert
    Assert.assertNotNull(result);

    // 4. Log
    System.out.println(printAst(result));
  }

  private String printAst(AstNode root) {

    if (root == null) return "";

    StringBuilder sb = new StringBuilder();
    sb.append(" Type: ")
        .append(root.getNodeClass())
        .append(" Value: ")
        .append(root.getValue())
        .append("\n");

    List<String> children = new ArrayList<>();
    root.getChildren()
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
