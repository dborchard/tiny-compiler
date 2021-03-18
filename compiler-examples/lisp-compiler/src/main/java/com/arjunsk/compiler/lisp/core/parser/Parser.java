package com.arjunsk.compiler.lisp.core.parser;

import com.arjunsk.compiler.lisp.domain.ast.AstNode;
import com.arjunsk.compiler.lisp.domain.lexer.Token;
import com.arjunsk.compiler.lisp.domain.lexer.support.TokenType;
import com.arjunsk.compiler.lisp.exceptions.ParserException;
import java.util.List;

public class Parser {

  private final List<Token> tokens;

  private int current = 0;

  public Parser(List<Token> tokens) {
    this.tokens = tokens;
  }

  public AstNode parse() {
    AstNode root = new AstNode("Program");

    while (current < tokens.size()) {
      root.appendParams(walk());
    }

    return root;
  }

  private AstNode walk() {
    Token token = tokens.get(current);

    if (token.getType() == TokenType.NUMBER) { // 1. Number
      current++;
      return new AstNode("NumberLiteral", token.getValue());
    } else if (token.getType() == TokenType.STRING) { // 2. Variable Name
      current++;
      return new AstNode("StringLiteral", token.getValue());
    } else if (token.getType() == TokenType.PAREN
        && token.getValue().equals("(")) { // 3. Expression

      token = tokens.get(++current);
      final String expressionName = token.getValue();

      // Base Node
      AstNode node = new AstNode("CallExpression", expressionName);

      token = tokens.get(++current);

      while ((token.getType() != TokenType.PAREN)
          || (token.getType() == TokenType.PAREN && !token.getValue().equals(")"))) {

        node.appendParams(walk());
        token = tokens.get(current);
      }
      current++;
      return node;
    } else {
      throw new ParserException();
    }
  }
}
