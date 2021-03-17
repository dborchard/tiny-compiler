package com.arjunsk.compiler.lisp.core.lexer;

import static org.junit.Assert.assertEquals;

import com.arjunsk.compiler.lisp.domain.lexer.Token;
import com.arjunsk.compiler.utils.FileReaderUtil;
import java.util.List;
import java.util.Objects;
import org.junit.Test;

public class LexerTest {

  @Test
  public void test_tokenize() {

    // 1. Arrange
    String hllCode =
        Objects.requireNonNull(FileReaderUtil.getResourceFileAsString("input/code.lsp"));

    // 2. Act
    List<Token> tokens = new Lexer().tokenize(hllCode);

    // 3. Assert
    assertEquals(9, tokens.size());

    // 4. Log
    tokens.forEach(System.out::println);
  }
}
