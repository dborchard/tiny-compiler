package com.arjunsk.compiler.ck.domain.tree.nodes.grammer.impl;

import com.arjunsk.compiler.ck.domain.tree.nodes.grammer.ParserRuleContext;
import com.arjunsk.compiler.ck.visitor.Visitor;

/**
 * `Statement` Syntax ParseRuleContext.
 *
 * <p>Eg:- LET | SHOW // ie either Let or Show statement would be the value of statement.
 */
public class StatementContext extends ParserRuleContext {

  private final LetContext letContext;

  private final ShowContext showContext;

  public StatementContext(LetContext letContext, ShowContext showContext) {
    this.letContext = letContext;
    this.showContext = showContext;

    // Conditionally add child node
    if (letContext != null) {
      this.addChild(letContext);
    } else {
      this.addChild(showContext);
    }
  }

  public LetContext getLetContext() {
    return letContext;
  }

  public ShowContext getShowContext() {
    return showContext;
  }

  @Override
  public <T> T accept(Visitor<? extends T> visitor) {
    return visitor.visitStatement(this);
  }
}
