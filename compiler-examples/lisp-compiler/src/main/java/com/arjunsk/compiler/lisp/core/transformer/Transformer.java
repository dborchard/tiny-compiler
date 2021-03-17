package com.arjunsk.compiler.lisp.core.transformer;

import com.arjunsk.compiler.lisp.domain.ast.AstNode;
import com.arjunsk.compiler.lisp.domain.ast.LispAstNode;

// TODO: Continue from here
public class Transformer {

  public LispAstNode transform(AstNode root) {
    LispAstNode newAst = new LispAstNode("Program");
    return newAst;
  }
}
