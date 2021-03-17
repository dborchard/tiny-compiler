package com.arjunsk.compiler.svg.domain.lexer.ast;

import com.arjunsk.compiler.svg.domain.lexer.ast.support.AstNodeClass;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AstNode {

  private final AstNodeClass nodeClass;

  private final String value;

  private final List<AstNode> children = new ArrayList<>();

  public AstNode(AstNodeClass nodeClass, String value) {
    this.nodeClass = nodeClass;
    this.value = value;
  }

  public AstNode(AstNodeClass nodeClass) {
    this.nodeClass = nodeClass;
    this.value = "";
  }

  public AstNodeClass getNodeClass() {
    return nodeClass;
  }

  public String getValue() {
    return value;
  }

  public Iterator<AstNode> getChildren() {
    return this.children.iterator();
  }

  public void appendNode(AstNode astNode) {
    this.children.add(astNode);
  }
}
