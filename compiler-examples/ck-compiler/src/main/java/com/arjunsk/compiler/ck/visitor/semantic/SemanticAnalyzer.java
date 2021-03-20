package com.arjunsk.compiler.ck.visitor.semantic;

import com.arjunsk.compiler.ck.domain.tree.nodes.common.TerminalNode;
import com.arjunsk.compiler.ck.domain.tree.nodes.grammer.impl.LetContext;
import com.arjunsk.compiler.ck.domain.tree.nodes.grammer.impl.ShowContext;
import com.arjunsk.compiler.ck.domain.tree.nodes.grammer.impl.StatementContext;
import com.arjunsk.compiler.ck.exceptions.SemanticException;
import com.arjunsk.compiler.ck.visitor.SimplerLangBaseVisitor;
import java.util.HashMap;
import java.util.Map;

public class SemanticAnalyzer extends SimplerLangBaseVisitor<Void> {

  private final Map<String, String> variableMap;

  public SemanticAnalyzer() {
    super();
    this.variableMap = new HashMap<>();
  }

  /** Validate Statement Semantics. */
  @Override
  public Void visitStatement(StatementContext context) {
    if (context.getLetContext() == null && context.getShowContext() == null) {
      throw new SemanticException("Statement should of type LET or SHOW.");
    } else if (context.getLetContext() != null && context.getShowContext() != null) {
      throw new SemanticException("Statement should be either of type LET or SHOW & not both.");
    }

    return super.visitStatement(context);
  }

  /** Validate LET Semantics. */
  @Override
  public Void visitLet(LetContext context) {

    String variableName = context.getVariableName().getText();
    String variableValue = context.getVariableValue().getText();

    if (variableName == null || variableName.isEmpty()) {
      throw new SemanticException("Variable name cannot be empty.");
    } else if (variableValue == null || variableValue.isEmpty()) {
      throw new SemanticException("Variable value cannot be empty.");
    }

    // Check if variable value is Integer. In our case, this will be already handled in the
    // Tokenizer.
    try {
      Integer.parseInt(variableValue);
    } catch (NumberFormatException | NullPointerException ex) {
      throw new SemanticException("Variable value should be integer.", ex);
    }

    // This will be used to check whether variable is declared using LET before invoking SHOW for
    // the variable.
    variableMap.put(variableName, variableValue);

    return super.visitLet(context);
  }

  /**
   * Validate SHOW Semantics.
   *
   * <p>NOTE: We validate if the variable is previously declared using LET.
   */
  @Override
  public Void visitShow(ShowContext context) {

    TerminalNode variableNameTN = context.getVariableName();
    TerminalNode integerValueTN = context.getIntegerValue();

    /* 1. Checking whether either of VAR | INT is present.*/
    boolean isVarPresent = variableNameTN != null && !variableNameTN.getText().isEmpty();
    boolean isIntPresent = integerValueTN != null && !integerValueTN.getText().isEmpty();

    if (!isVarPresent && !isIntPresent) {
      throw new SemanticException("SHOW should have integer or variable as argument");
    } else if (isVarPresent && isIntPresent) {
      throw new SemanticException("SHOW should have either integer or variable as argument");
    }

    /* 2. If SHOW Argument is Number, check if it is an integer. In our case, this will be
    already handled in the Tokenizer.*/
    if (integerValueTN != null) {
      try {
        Integer.parseInt(integerValueTN.getText());
      } catch (NumberFormatException | NullPointerException ex) {
        throw new SemanticException("SHOW argument is not a valid integer.", ex);
      }
    }

    /* 3. if SHOW Argument is Variable, check if the variable is declared previously.*/
    if (variableNameTN != null && !variableMap.containsKey(variableNameTN.getText())) {
      throw new SemanticException("SHOW argument variable is not declared.");
    }

    return super.visitShow(context);
  }
}
