package com.arjunsk.compiler.svg.core.transforming;

import com.arjunsk.compiler.svg.domain.lexer.ast.AstNode;
import com.arjunsk.compiler.svg.domain.lexer.ast.support.AstNodeClass;
import com.arjunsk.compiler.svg.domain.transformer.ast.SvgAstNode;
import com.arjunsk.compiler.svg.exceptions.TransformerException;
import java.util.Iterator;

/** Transform the AST to SVG AST. */
public class Transformer {

  public SvgAstNode transform(AstNode oldAst) {

    SvgAstNode newAst = new SvgAstNode("svg");
    newAst.appendAttributes("width", 100);
    newAst.appendAttributes("height", 100);
    newAst.appendAttributes("viewBox", "0 0 100 100");
    newAst.appendAttributes("xmlns", "http://www.w3.org/2000/svg");
    newAst.appendAttributes("version", "1.1");

    AstNode currentAstNode;
    String penColor = "89";
    SvgAstNode expression;

    final Iterator<AstNode> children = oldAst.getChildren();
    while (children.hasNext()) {
      currentAstNode = children.next();
      if (currentAstNode.getNodeClass() == AstNodeClass.CALL_EXPRESSION) {
        switch (currentAstNode.getValue()) {
          case "Paper":
            expression = new SvgAstNode("rect");
            expression.appendAttributes("x", 0);
            expression.appendAttributes("y", 0);
            expression.appendAttributes("width", 100);
            expression.appendAttributes("height", 100);
            expression.appendAttributes(
                "fill", makeColor(currentAstNode.getChildren().next().getValue()));
            newAst.appendBody(expression);
            break;
          case "Pen":
            penColor = currentAstNode.getChildren().next().getValue();
            break;
          case "Line":
            expression = new SvgAstNode("line");
            final Iterator<AstNode> lineArgs = currentAstNode.getChildren();
            expression.appendAttributes("x1", Integer.parseInt(lineArgs.next().getValue()));
            expression.appendAttributes("y1", Integer.parseInt(lineArgs.next().getValue()));
            expression.appendAttributes("x2", Integer.parseInt(lineArgs.next().getValue()));
            expression.appendAttributes("y2", Integer.parseInt(lineArgs.next().getValue()));
            expression.appendAttributes("stroke", makeColor(penColor));
            expression.appendAttributes("stroke-linecap", "round");
            newAst.appendBody(expression);
            break;
          default:
            throw new TransformerException("Invalid Token");
        }
      }
    }

    return newAst;
  }

  private String makeColor(String level) {
    int output = 100 - Integer.parseInt(level);
    return "rgb(" + output + "%, " + output + "%, " + output + "%)";
  }
}
