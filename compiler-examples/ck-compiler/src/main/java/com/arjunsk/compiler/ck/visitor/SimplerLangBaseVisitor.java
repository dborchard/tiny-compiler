package com.arjunsk.compiler.ck.visitor;

import com.arjunsk.compiler.ck.domain.tree.ParseTree;
import com.arjunsk.compiler.ck.domain.tree.nodes.common.TerminalNode;
import com.arjunsk.compiler.ck.domain.tree.nodes.grammer.impl.LetContext;
import com.arjunsk.compiler.ck.domain.tree.nodes.grammer.impl.ProgramContext;
import com.arjunsk.compiler.ck.domain.tree.nodes.grammer.impl.ShowContext;
import com.arjunsk.compiler.ck.domain.tree.nodes.grammer.impl.StatementContext;

public class SimplerLangBaseVisitor<T> implements Visitor<T> {

  @Override
  public T visitProgram(ProgramContext context) {
    return visitChildren(context);
  }

  @Override
  public T visitStatement(StatementContext context) {
    return visitChildren(context);
  }

  @Override
  public T visitLet(LetContext context) {
    return visitChildren(context);
  }

  @Override
  public T visitShow(ShowContext context) {
    return visitChildren(context);
  }

  /** There is no child to propagate. */
  @Override
  public T visitTerminal(TerminalNode context) {
    return defaultResult();
  }

  /**
   * Propagate visitor to the children.
   *
   * <p>So when you call invoke something like this
   *
   * <pre>
   *     ParseTree tree = parser.parseProgram();
   *     SimplerLangCustomVisitor visitor = new SimplerLangCustomVisitor();
   *     tree.accept(visitor);
   * </pre>
   *
   * The accept(visitor) `visitor` is propagated to the children of that tree node.
   *
   * @param node Visitable implementation
   */
  public T visitChildren(ParseTree node) {
    T result = defaultResult();
    for (int i = 0; i < node.getChildCount(); i++) {
      ParseTree c = node.getChild(i);
      result = c.accept(this);
    }

    return result; // return the last accept result of the children list.
  }

  protected T defaultResult() {
    return null;
  }
}
