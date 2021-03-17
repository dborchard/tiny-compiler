package com.arjunsk.compiler.ck.lexer;

import com.arjunsk.compiler.ck.domain.token.support.TokenType;
import org.junit.Assert;
import org.junit.Test;

public class LexerTest {

  @Test
  public void test_nextToken() {
    Lexer lexer = new Lexer("a = 10\n" + "show a");

    Assert.assertTrue(lexer.nextToken());
    Assert.assertEquals(TokenType.VARIABLE, lexer.getCurrentToken().getType());
    Assert.assertEquals("a", lexer.getCurrentToken().getValue());
    Assert.assertNull(lexer.getPreviousToken());

    Assert.assertTrue(lexer.nextToken());
    Assert.assertEquals(TokenType.EQUALS_OPERATOR, lexer.getCurrentToken().getType());
    Assert.assertEquals(TokenType.VARIABLE, lexer.getPreviousToken().getType());
    Assert.assertEquals("a", lexer.getPreviousToken().getValue());

    Assert.assertTrue(lexer.nextToken());
    Assert.assertEquals(TokenType.NUMBER, lexer.getCurrentToken().getType());
    Assert.assertEquals("10", lexer.getCurrentToken().getValue());
    Assert.assertEquals(TokenType.EQUALS_OPERATOR, lexer.getPreviousToken().getType());

    Assert.assertTrue(lexer.nextToken());
    Assert.assertEquals(TokenType.SHOW, lexer.getCurrentToken().getType());
    Assert.assertEquals(TokenType.NUMBER, lexer.getPreviousToken().getType());
    Assert.assertEquals("10", lexer.getPreviousToken().getValue());

    Assert.assertTrue(lexer.nextToken());
    Assert.assertEquals(TokenType.VARIABLE, lexer.getCurrentToken().getType());
    Assert.assertEquals("a", lexer.getCurrentToken().getValue());
    Assert.assertEquals(TokenType.SHOW, lexer.getPreviousToken().getType());
  }
}
