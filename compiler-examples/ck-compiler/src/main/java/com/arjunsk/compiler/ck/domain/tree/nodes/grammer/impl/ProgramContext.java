package com.arjunsk.compiler.ck.domain.tree.nodes.grammer.impl;

import com.arjunsk.compiler.ck.domain.tree.nodes.grammer.ParserRuleContext;
import com.arjunsk.compiler.ck.visitor.Visitor;
import java.util.List;

/**
 * `Program` Semantics ParseRuleContext.
 *
 * <p>Eg:- Statement+ // ie program consists of multiple statements.
 */
public class ProgramContext extends ParserRuleContext {

  private final List<StatementContext> statements;

  public ProgramContext(List<StatementContext> statements) {
    this.statements = statements;

    // Add the statements as  children to this node.
    for (StatementContext statement : statements) {
      this.addChild(statement);
    }
  }

  public List<StatementContext> getStatements() {
    return statements;
  }

  public StatementContext getStatements(int i) {
    return statements.get(i);
  }

  @Override
  public <T> T accept(Visitor<? extends T> visitor) {
    return visitor.visitProgram(this);
  }
}
