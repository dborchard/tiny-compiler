package com.arjunsk.compiler.ck.domain.tree.nodes.common;

import com.arjunsk.compiler.ck.domain.token.Token;
import com.arjunsk.compiler.ck.domain.tree.ParseTree;
import com.arjunsk.compiler.ck.visitor.Visitor;

/**
 * Terminal node or Leaf node.
 *
 * <p>Eg:- VAR or INT
 */
public class TerminalNode implements ParseTree {

  public ParseTree parent;

  public Token symbol;

  @Override
  public ParseTree getParent() {
    return this.parent;
  }

  @Override
  public void setParent(ParseTree parent) {
    this.parent = parent;
  }

  /**
   * Terminal nodes will have Token as the payload. (ie VAR or INT)
   *
   * @param symbol Token value for the terminal node
   */
  public void setSymbol(Token symbol) {
    this.symbol = symbol;
  }

  /**
   * Terminal node will have Token as the payload. (ie VAR or INT). We have it as Object return type
   * because for ContextNodes, it can be that node itself.
   *
   * @return Token value.
   */
  @Override
  public Object getPayload() {
    return this.symbol;
  }

  /**
   * Terminal node will have the Text as Token value.
   *
   * @return Token Value
   */
  @Override
  public String getText() {
    return this.symbol.getValue();
  }

  /** {@inheritDoc} */
  @Override
  public void addChild(ParseTree child) {}

  /** {@inheritDoc} */
  @Override
  public ParseTree getChild(int i) {
    return null;
  }

  /** {@inheritDoc} */
  @Override
  public int getChildCount() {
    return 0;
  }

  /**
   * Implementation of toStringTree for terminal node return only the Token Value.
   *
   * @return Token Value
   */
  @Override
  public String toStringTree() {
    return getText();
  }

  /**
   * {@inheritDoc}
   *
   * <p>Invoke visitTerminal function in the Visitor Implementation.
   */
  @Override
  public <T> T accept(Visitor<? extends T> visitor) {
    return visitor.visitTerminal(this);
  }
}
