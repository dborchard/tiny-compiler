package com.arjunsk.parser.jdt.ast;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;

public class JdtAstParser {

  public void parse(String javaCode) {
    ASTParser parser = ASTParser.newParser(AST.JLS15);
    parser.setSource(javaCode.toCharArray());
    parser.setKind(ASTParser.K_COMPILATION_UNIT);
    final CompilationUnit cu = (CompilationUnit) parser.createAST(null);

    cu.accept(new JdtAstVisitor(cu));
  }
}
