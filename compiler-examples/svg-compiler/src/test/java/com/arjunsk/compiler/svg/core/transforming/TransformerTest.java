package com.arjunsk.compiler.svg.core.transforming;

import com.arjunsk.compiler.svg.core.parsing.Lexer;
import com.arjunsk.compiler.svg.core.parsing.Parser;
import com.arjunsk.compiler.svg.domain.lexer.ast.AstNode;
import com.arjunsk.compiler.svg.domain.lexer.token.Token;
import com.arjunsk.compiler.svg.domain.transformer.ast.SvgAstNode;
import com.arjunsk.compiler.utils.FileReaderUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.junit.Assert;
import org.junit.Test;

public class TransformerTest {

  @Test
  public void test_transform() {
    // 1. Arrange
    String hllCode =
        Objects.requireNonNull(FileReaderUtil.getResourceFileAsString("lexer/code.ck"));
    List<Token> tokens = Lexer.tokenize(hllCode);
    AstNode oldAst = new Parser(tokens).parse();

    // 2. Act
    SvgAstNode result = new Transformer().transform(oldAst);

    // 3. Assert
    Assert.assertNotNull(result);

    // 4. Log
    System.out.println(printAst(result));
  }

  private String printAst(SvgAstNode root) {

    if (root == null) return "";

    StringBuilder sb = new StringBuilder();
    sb.append(root.getTag()).append("\n");

    root.getAttributes()
        .forEachRemaining(
            item -> sb.append(item.getKey()).append(" --> ").append(item.getValue()).append("\n"));

    sb.append("\n");

    List<String> children = new ArrayList<>();
    root.getBody()
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
