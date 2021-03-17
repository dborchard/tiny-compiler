package com.arjunsk.compiler.ck.visitor;

import com.arjunsk.compiler.ck.domain.tree.nodes.common.TerminalNode;
import com.arjunsk.compiler.ck.domain.tree.nodes.grammer.impl.LetContext;
import com.arjunsk.compiler.ck.domain.tree.nodes.grammer.impl.ProgramContext;
import com.arjunsk.compiler.ck.domain.tree.nodes.grammer.impl.ShowContext;
import com.arjunsk.compiler.ck.domain.tree.nodes.grammer.impl.StatementContext;

/**
 * Visitor Pattern used for separating Business logic from domain class code.
 *
 * @param <T> Return type of visitXXX. Could be Void, Boolean etc.
 */
public interface Visitor<T> {

  T visitProgram(ProgramContext context);

  T visitStatement(StatementContext context);

  T visitLet(LetContext context);

  T visitShow(ShowContext context);

  T visitTerminal(TerminalNode context);
}
