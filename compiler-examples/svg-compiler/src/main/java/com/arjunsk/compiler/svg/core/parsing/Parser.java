package com.arjunsk.compiler.svg.core.parsing;

import com.arjunsk.compiler.svg.domain.lexer.ast.AstNode;
import com.arjunsk.compiler.svg.domain.lexer.ast.support.AstNodeClass;
import com.arjunsk.compiler.svg.domain.lexer.token.Token;
import com.arjunsk.compiler.svg.domain.lexer.token.support.TokenType;
import com.arjunsk.compiler.svg.exceptions.ParserException;
import java.util.ArrayList;
import java.util.List;

public class Parser {

  private final List<Token> tokens;

  private int currentTokenIndex;

  public Parser(List<Token> tokens) {
    this.tokens = tokens;
    this.currentTokenIndex = 0;
  }

  public AstNode parse() {

    AstNode ast = new AstNode(AstNodeClass.BLOCK);

    boolean isPaperDefined = false;
    boolean isPenDefined = false;

    while (currentTokenIndex < tokens.size()) {
      Token currentToken = tokens.get(currentTokenIndex);

      if (currentToken.getType() == TokenType.WORD) {
        AstNode expression;

        switch (currentToken.getValue()) {
          case "Paper":
            if (isPaperDefined) throw new ParserException("Paper already defined");

            expression = new AstNode(AstNodeClass.CALL_EXPRESSION, "Paper");
            findNumericalArguments(1).forEach(expression::appendNode);
            ast.appendNode(expression);
            isPaperDefined = true;
            break;
          case "Pen":
            if (isPenDefined) throw new ParserException("Pen already defined");

            expression = new AstNode(AstNodeClass.CALL_EXPRESSION, "Pen");
            findNumericalArguments(1).forEach(expression::appendNode);
            ast.appendNode(expression);
            isPenDefined = true;
            break;
          case "Line":
            if (!(isPaperDefined && isPenDefined))
              throw new ParserException("No Paper & Pen defined");

            expression = new AstNode(AstNodeClass.CALL_EXPRESSION, "Line");
            findNumericalArguments(4).forEach(expression::appendNode);
            ast.appendNode(expression);
            break;
          default:
            throw new ParserException("Invalid Token");
        }
      } else {
        throw new ParserException("Unexpected Token Type" + currentToken.getType());
      }
      currentTokenIndex = currentTokenIndex + 1;
    }
    return ast;
  }

  private List<AstNode> findNumericalArguments(int argCount) {
    List<AstNode> result = new ArrayList<>();
    while (argCount-- > 0) {
      result.add(new AstNode(AstNodeClass.NUMBER, tokens.get(++currentTokenIndex).getValue()));
    }
    return result;
  }
}
