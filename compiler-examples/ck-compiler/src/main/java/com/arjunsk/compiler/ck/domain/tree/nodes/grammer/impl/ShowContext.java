package com.arjunsk.compiler.ck.domain.tree.nodes.grammer.impl;

import com.arjunsk.compiler.ck.domain.tree.nodes.common.TerminalNode;
import com.arjunsk.compiler.ck.domain.tree.nodes.grammer.ParserRuleContext;
import com.arjunsk.compiler.ck.visitor.Visitor;

/**
 * `Show` Syntax ParseRuleContext.
 *
 * <p>Eg:- show INT or show VAR
 */
public class ShowContext extends ParserRuleContext {

  private final TerminalNode integerValue;

  private final TerminalNode variableName;

  public ShowContext(TerminalNode integerValue, TerminalNode variableName) {
    this.integerValue = integerValue;
    this.variableName = variableName;

    // Conditionally add child node
    if (integerValue != null) {
      this.addChild(integerValue);
    } else {
      this.addChild(variableName);
    }
  }

  public TerminalNode getIntegerValue() {
    return integerValue;
  }

  public TerminalNode getVariableName() {
    return variableName;
  }

  @Override
  public <T> T accept(Visitor<? extends T> visitor) {
    return visitor.visitShow(this);
  }
}
