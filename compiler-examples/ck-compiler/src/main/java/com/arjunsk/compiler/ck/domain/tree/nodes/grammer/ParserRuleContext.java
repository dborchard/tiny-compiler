package com.arjunsk.compiler.ck.domain.tree.nodes.grammer;

import com.arjunsk.compiler.ck.domain.tree.ParseTree;
import com.arjunsk.compiler.ck.visitor.Visitor;
import java.util.ArrayList;
import java.util.List;

/** Base implementation for our Grammar Elements. */
public class ParserRuleContext implements ParseTree {

  public ParseTree parent;

  public List<ParseTree> children;

  /** {@inheritDoc} */
  @Override
  public ParseTree getParent() {
    return this.parent;
  }

  /** {@inheritDoc} */
  @Override
  public void setParent(ParseTree parent) {
    this.parent = parent;
  }

  /** {@inheritDoc} */
  @Override
  public String getText() {
    if (getChildCount() == 0) {
      return "";
    }

    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < getChildCount(); i++) {
      builder.append(getChild(i).getText());
    }

    return builder.toString();
  }

  /** {@inheritDoc} */
  @Override
  public Object getPayload() {
    return this;
  }

  /** {@inheritDoc} */
  public void addChild(ParseTree child) {
    child.setParent(this);
    if (children == null) children = new ArrayList<>();
    children.add(child);
  }

  /** {@inheritDoc} */
  @Override
  public ParseTree getChild(int i) {
    return this.children.get(i);
  }

  /** {@inheritDoc} */
  @Override
  public int getChildCount() {
    return children != null ? children.size() : 0;
  }

  /** {@inheritDoc} */
  @Override
  public String toStringTree() {
    if (getChildCount() == 0) {
      return "";
    }

    StringBuilder sb = new StringBuilder();

    sb.append("( ");
    sb.append(this.getClass().getSimpleName());
    sb.append("(");
    for (int i = 0; i < getChildCount(); i++) {
      sb.append(" ").append(getChild(i).toStringTree()).append(" ");
    }
    sb.append(")");
    sb.append(" )");

    return sb.toString();
  }

  /** To be overridden in child implementations. */
  @Override
  public <T> T accept(Visitor<? extends T> visitor) {
    return null;
  }
}
