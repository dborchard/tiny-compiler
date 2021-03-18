package com.arjunsk.compiler.lisp.domain.ast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/** Input AST. */
public class AstNode {

  private final String type; // CallExpression or NumberLiteral or StringLiteral

  private final String value; // Node Value

  private final List<AstNode> params;

  public AstNode(String type) {
    this.type = type;
    this.value = "";
    this.params = new ArrayList<>();
  }

  public AstNode(String type, String value) {
    this.type = type;
    this.value = value;
    this.params = new ArrayList<>();
  }

  public void appendParams(AstNode node) {
    this.params.add(node);
  }

  public String getType() {
    return type;
  }

  public String getValue() {
    return value;
  }

  public Iterator<AstNode> getParams() {
    return this.params.iterator();
  }
}
