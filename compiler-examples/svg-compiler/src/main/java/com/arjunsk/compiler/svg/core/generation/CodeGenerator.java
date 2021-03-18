package com.arjunsk.compiler.svg.core.generation;

import com.arjunsk.compiler.svg.domain.transformer.ast.SvgAstNode;

/** Convert to SVG based on Transformed AST. */
public class CodeGenerator {

  public String generate(SvgAstNode node) {
    return printAst(node);
  }

  /** using recursion to process transformed AST. */
  private String printAst(SvgAstNode root) {

    if (root == null) return "";

    StringBuilder sb = new StringBuilder();

    sb.append("<").append(root.getTag());

    root.getAttributes()
        .forEachRemaining(
            item ->
                sb.append(" ")
                    .append(item.getKey())
                    .append("=")
                    .append("\"")
                    .append(item.getValue())
                    .append("\""));

    sb.append(">");

    root.getBody().forEachRemaining(item -> sb.append("\n").append(printAst(item)));

    sb.append("\n").append("</").append(root.getTag()).append(">");

    return sb.toString();
  }
}
