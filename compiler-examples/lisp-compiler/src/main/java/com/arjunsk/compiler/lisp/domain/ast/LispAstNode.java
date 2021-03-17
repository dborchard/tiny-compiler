package com.arjunsk.compiler.lisp.domain.ast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LispAstNode {

  private final String type;

  private final String value;

  private final List<LispAstNode> arguments;

  public LispAstNode(String type) {
    this.type = type;
    this.value = "";
    this.arguments = new ArrayList<>();
  }

  public LispAstNode(String type, String value) {
    this.type = type;
    this.value = value;
    this.arguments = new ArrayList<>();
  }

  public void appendParams(LispAstNode node) {
    this.arguments.add(node);
  }

  public String getType() {
    return type;
  }

  public String getValue() {
    return value;
  }

  public Iterator<LispAstNode> getArguments() {
    return this.arguments.iterator();
  }
}
