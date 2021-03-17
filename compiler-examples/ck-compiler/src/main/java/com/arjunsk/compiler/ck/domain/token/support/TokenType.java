package com.arjunsk.compiler.ck.domain.token.support;

/**
 * Our code will contain only SHOW, VARIABLE, EQUALS_OPERATOR or NUMBER
 *
 * <pre>
 *   a = 10
 *   show a
 *   show 20
 *
 * </pre>
 */
public enum TokenType {
  SHOW, // Key word

  EQUALS_OPERATOR, // The only operator

  // Terminal Nodes
  NUMBER,
  VARIABLE,
}
