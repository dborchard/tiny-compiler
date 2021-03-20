package com.arjunsk.compiler.ck.parser;

import com.arjunsk.compiler.ck.domain.token.Token;
import com.arjunsk.compiler.ck.domain.token.support.TokenType;
import com.arjunsk.compiler.ck.domain.tree.nodes.common.TerminalNode;
import com.arjunsk.compiler.ck.domain.tree.nodes.grammer.impl.LetContext;
import com.arjunsk.compiler.ck.domain.tree.nodes.grammer.impl.ProgramContext;
import com.arjunsk.compiler.ck.domain.tree.nodes.grammer.impl.ShowContext;
import com.arjunsk.compiler.ck.domain.tree.nodes.grammer.impl.StatementContext;
import com.arjunsk.compiler.ck.exceptions.ParserException;
import com.arjunsk.compiler.ck.lexer.Lexer;
import java.util.ArrayList;
import java.util.List;

public class Parser {

  private final Lexer lexer;

  public Parser(Lexer lexer) {
    this.lexer = lexer;
  }

  /**
   * Parse Logic for Program.
   *
   * <p>NOTE: We will use if-else to create a Deterministic Finite Automata (DFA).
   */
  public ProgramContext parseProgram() {
    List<StatementContext> statements = new ArrayList<>();
    do {
      statements.add(parseStatement());
    } while (lexer.nextToken());
    return new ProgramContext(statements);
  }

  /** Parse Logic for Statement. Creates a LET or SHOW statement based on the Tokens passed. */
  public StatementContext parseStatement() {

    if (lexer.getCurrentToken() == null) {
      lexer.nextToken(); // Current Token =  LET | SHOW
    }

    Token token = lexer.getCurrentToken(); // LET | SHOW

    if (token.getType() == TokenType.VARIABLE) { // LET
      return new StatementContext(parseLet(), null);
    } else if (token.getType() == TokenType.SHOW) { // SHOW
      return new StatementContext(null, parseShow());
    } else {
      throw new ParserException("Not of type LET or SHOW.");
    }
  }

  /** Parse Logic for Let. */
  public LetContext parseLet() {
    if (lexer.getCurrentToken() == null) {
      lexer.nextToken(); // Current Token =  VAR
    }
    TerminalNode variableNameToken = parseTerminalNode(); // VAR

    lexer.nextToken(); // move to : =
    lexer.nextToken(); // move to : INT

    TerminalNode valueToken = parseTerminalNode(); // INT

    return new LetContext(variableNameToken, valueToken);
  }

  /** Parse Logic for Show. */
  public ShowContext parseShow() {

    if (lexer.getCurrentToken() == null) {
      lexer.nextToken(); // Current Token =  SHOW
    }

    lexer.nextToken(); // Current Token = VAR | INT

    TerminalNode terminal = parseTerminalNode(); // VAR | INT
    final Token token = (Token) terminal.getPayload();

    if (token.getType() == TokenType.NUMBER) {
      return new ShowContext(terminal, null);
    } else if (token.getType() == TokenType.VARIABLE) {
      return new ShowContext(null, terminal);
    } else {
      throw new ParserException("Show not preceded with var or int");
    }
  }

  /** Parse Logic for Terminal Node. */
  public TerminalNode parseTerminalNode() {

    if (lexer.getCurrentToken() == null) {
      lexer.nextToken(); // Current Token =  VAR | INT
    }

    TerminalNode token = new TerminalNode();
    token.setSymbol(lexer.getCurrentToken());
    return token;
  }
}
