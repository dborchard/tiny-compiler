package com.arjunsk.compiler.ck.domain.tree;

import com.arjunsk.compiler.ck.visitor.Visitor;

/**
 * Base for implementing a parse tree.
 *
 * <p>Since this is the domain object which will have custom business logic in future, we use {@link
 * Visitable#accept(Visitor)} to implement the same.
 *
 * <p>You might be wondering, in the {@link
 * com.arjunsk.compiler.ck.domain.tree.nodes.grammer.ParserRuleContext} we already have the
 * parent-child relationship. Then how is this class helpful. We use this class for generic
 * traversal of parent-child paths. Mainly used to propagate accept() and generate toString() of the
 * tree.
 */
public interface ParseTree extends Visitable {

  ParseTree getParent();

  void setParent(ParseTree parent);

  /**
   * @return concatenation of all the children.getText() or return the Token value for Terminal
   *     nodes.
   */
  String getText();

  /** @return this Object or Token entry. */
  Object getPayload();

  /** Add child to the tree. */
  void addChild(ParseTree child);

  /** Get child at the given index. */
  ParseTree getChild(int i);

  /** @return count of children */
  int getChildCount();

  /** @return bracket matched flattened string of the tree. */
  String toStringTree();
}
