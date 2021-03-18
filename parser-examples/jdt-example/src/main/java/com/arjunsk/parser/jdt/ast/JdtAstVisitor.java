package com.arjunsk.parser.jdt.ast;

import java.util.HashSet;
import java.util.Set;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.ForStatement;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

/** Visitor for all Java Grammar Semantics. */
public class JdtAstVisitor extends ASTVisitor {

  private final Set<String> names;

  private final CompilationUnit cu;

  public JdtAstVisitor(CompilationUnit cu) {
    this.cu = cu;
    names = new HashSet<>();
  }

  public boolean visit(VariableDeclarationFragment node) {
    SimpleName name = node.getName();
    this.names.add(name.getIdentifier());
    System.out.println(
        "Declared '" + name + "' at line " + cu.getLineNumber(name.getStartPosition()));
    return false;
  }

  public boolean visit(SimpleName node) {
    if (this.names.contains(node.getIdentifier())) {
      System.out.println(
          "Used '" + node + "' at line " + cu.getLineNumber(node.getStartPosition()));
    }
    return false;
  }

  public boolean visit(ForStatement node) {
    System.out.println("ForStatement -- content:\n" + node.toString());
    return false; // if true, will iterate Visitor to it child node as well.
  }

  public boolean visit(IfStatement node) {
    System.out.println("IFStatement -- content:\n" + node.toString());
    return false;
  }
}
