package com.arjunsk.compiler.svg.core.parsing;

import com.arjunsk.compiler.svg.domain.lexer.token.Token;
import com.arjunsk.compiler.utils.FileReaderUtil;
import java.util.List;
import java.util.Objects;
import org.junit.Assert;
import org.junit.Test;

public class LexerTest {

  @Test
  public void test_tokenize() {
    // 1. Arrange
    String hllCode =
        Objects.requireNonNull(FileReaderUtil.getResourceFileAsString("lexer/code.ck"));

    // 2. Act
    List<Token> resultList = Lexer.tokenize(hllCode);

    // 3. Assert
    Assert.assertEquals(19, resultList.size());

    // (Optional) 4. Log
    resultList.forEach(token -> System.out.println(token.getType() + " " + token.getValue()));
  }
}
