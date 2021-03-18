package com.arjunsk.compiler.svg.core.parsing;

import com.arjunsk.compiler.svg.domain.lexer.token.Token;
import com.arjunsk.compiler.svg.domain.lexer.token.support.TokenType;
import com.arjunsk.compiler.svg.exceptions.LexerException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*

Input:
 Paper 100

Output:
  WORD Paper
  NUMBER 100

*/

/** Convert Input Code to Token's. */
public final class Lexer {

  private Lexer() {}

  public static List<Token> tokenize(String hllCode) {

    return Arrays.stream(hllCode.split("\\s+"))
        .filter(element -> !element.trim().isEmpty())
        .map(
            element -> {
              char firstChar = element.charAt(0);
              if (Character.isLetter(firstChar)) {
                return new Token(TokenType.WORD, element);
              } else if (isNumeric(element)) {
                return new Token(TokenType.NUMBER, element);
              } else {
                throw new LexerException();
              }
            })
        .collect(Collectors.toList());
  }

  private static boolean isNumeric(String num) {
    return num.chars().allMatch(Character::isDigit);
  }
}
