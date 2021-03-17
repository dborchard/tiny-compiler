package com.arjunsk.compiler.lisp.core.lexer;

import com.arjunsk.compiler.lisp.domain.lexer.Token;
import com.arjunsk.compiler.lisp.domain.lexer.support.TokenType;
import com.arjunsk.compiler.lisp.exceptions.LexerException;
import java.util.ArrayList;
import java.util.List;

public class Lexer {

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
        currentChar = input.charAt(++currentIndex);
        StringBuilder value = new StringBuilder();
        while (currentChar != '"') {
          value.append(currentChar);
          currentChar = input.charAt(++currentIndex);
        }
        result.add(new Token(TokenType.STRING, value.toString()));
      } else if (Character.isDigit(currentChar)) {
        StringBuilder value = new StringBuilder();
        while (Character.isDigit(currentChar)) {
          value.append(currentChar);
          currentChar = input.charAt(++currentIndex);
        }
        result.add(new Token(TokenType.NUMBER, value.toString()));
      } else if (Character.isLetter(currentChar)) {
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
