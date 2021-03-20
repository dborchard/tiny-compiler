package com.arjunsk.compiler.ck.domain.tree.nodes.grammer.impl;

import com.arjunsk.compiler.ck.domain.tree.nodes.grammer.ParserRuleContext;
import com.arjunsk.compiler.ck.visitor.Visitor;
import java.util.List;

/**
 * `Program` Semantics ParseRuleContext.
 *
 * <p>Eg:- Statement+ // ie program consists of multiple statements.
 *
 * <p>NOTE: This is a simple compiler. Ideally we should be having the `package name` and `Class
 * Name` as part of ProgramContext. This `package name` and `Class Name` could be later used in
 * {@link
 * com.arjunsk.compiler.ck.visitor.codegenerator.CodeGeneratorVisitor#visitProgram(ProgramContext)}
 * to write the corresponding `.class` in the correct `target package` with the correct `class
 * name`.
 */
public class ProgramContext extends ParserRuleContext {

  private final List<StatementContext> statements;

  public ProgramContext(List<StatementContext> statements) {
    this.statements = statements;

    // Add the statements as  children to this node.
    for (StatementContext statement : statements) {
      this.addChild(statement);
    }
  }

  public List<StatementContext> getStatements() {
    return statements;
  }

  public StatementContext getStatements(int i) {
    return statements.get(i);
  }

  @Override
  public <T> T accept(Visitor<? extends T> visitor) {
    return visitor.visitProgram(this);
  }
}
