package com.arjunsk.compiler.lisp.core.lexer;

import com.arjunsk.compiler.lisp.domain.lexer.Token;
import com.arjunsk.compiler.lisp.domain.lexer.support.TokenType;
import com.arjunsk.compiler.lisp.exceptions.LexerException;
import java.util.ArrayList;
import java.util.List;

public class Lexer {

  /**
   * Convert code to token stream.
   *
   * @param input High Level Code
   * @return List of tokens
   */
  public List<Token> tokenize(String input) {
    List<Token> result = new ArrayList<>();

    int currentIndex = 0;
    char currentChar;

    while (currentIndex < input.length()) {

      currentChar = input.charAt(currentIndex);

      if (currentChar == '(' || currentChar == ')') {
        result.add(new Token(TokenType.PAREN, currentChar + ""));
        currentIndex++;
      } else if (currentChar == '\n' || currentChar == '\r' || currentChar == ' ') {
        currentIndex++;
      } else if (currentChar == '"') {

        // Iterate till ending double quote.
        currentChar = input.charAt(++currentIndex);
        StringBuilder value = new StringBuilder();
        while (currentChar != '"') {
          value.append(currentChar);
          currentChar = input.charAt(++currentIndex);
        }
        result.add(new Token(TokenType.STRING, value.toString()));
      } else if (Character.isDigit(currentChar)) {

        // Iterate till last digit of the number
        StringBuilder value = new StringBuilder();
        while (Character.isDigit(currentChar)) {
          value.append(currentChar);
          currentChar = input.charAt(++currentIndex);
        }
        result.add(new Token(TokenType.NUMBER, value.toString()));
      } else if (Character.isLetter(currentChar)) {

        // Iterate till last character of the word.
        StringBuilder value = new StringBuilder();
        while (Character.isLetter(currentChar)) {
          value.append(currentChar);
          currentChar = input.charAt(++currentIndex);
        }
        result.add(new Token(TokenType.NAME, value.toString()));
      } else {
        throw new LexerException();
      }
    }

    return result;
  }
}
