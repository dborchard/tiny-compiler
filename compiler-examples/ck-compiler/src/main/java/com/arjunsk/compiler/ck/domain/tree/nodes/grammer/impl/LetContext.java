package com.arjunsk.compiler.ck.domain.tree.nodes.grammer.impl;

import com.arjunsk.compiler.ck.domain.tree.nodes.common.TerminalNode;
import com.arjunsk.compiler.ck.domain.tree.nodes.grammer.ParserRuleContext;
import com.arjunsk.compiler.ck.visitor.Visitor;

/**
 * `Let` Semantics ParseRuleContext.
 *
 * <p>Eg:- a = 10 ( VAR = INT)
 */
public class LetContext extends ParserRuleContext {

  private final TerminalNode variableName;

  private final TerminalNode variableValue;

  public LetContext(TerminalNode variableName, TerminalNode variableValue) {
    this.variableName = variableName;
    this.variableValue = variableValue;

    // Add the arguments as  children to this node.
    this.addChild(variableName);
    this.addChild(variableValue);
  }

  public TerminalNode getVariableName() {
    return variableName;
  }

  public TerminalNode getVariableValue() {
    return variableValue;
  }

  @Override
  public <T> T accept(Visitor<? extends T> visitor) {
    return visitor.visitLet(this);
  }
}
