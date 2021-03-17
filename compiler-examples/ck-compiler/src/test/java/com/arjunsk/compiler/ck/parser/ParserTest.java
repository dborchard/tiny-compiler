package com.arjunsk.compiler.ck.parser;

import com.arjunsk.compiler.ck.domain.tree.ParseTree;
import com.arjunsk.compiler.ck.lexer.Lexer;
import com.arjunsk.compiler.ck.visitor.impl.SimplerLangCustomVisitor;
import org.junit.Assert;
import org.junit.Test;

public class ParserTest {

  @Test
  public void test_parseProgram() {

    Lexer lexer = new Lexer("a = 10\n" + "show a\n" + "show 23");

    Parser parser = new Parser(lexer);

    ParseTree tree = parser.parseProgram();
    SimplerLangCustomVisitor visitor = new SimplerLangCustomVisitor();
    tree.accept(visitor);

    Assert.assertEquals(3, tree.getChildCount());
    Assert.assertEquals(1, tree.getChild(0).getChildCount()); // Statement
    Assert.assertEquals(2, tree.getChild(0).getChild(0).getChildCount()); // LET a=10

    Assert.assertEquals(1, tree.getChild(1).getChildCount()); // Statement
    Assert.assertEquals(1, tree.getChild(1).getChild(0).getChildCount()); // SHOW show a

    Assert.assertEquals(1, tree.getChild(2).getChildCount()); // Statement
    Assert.assertEquals(1, tree.getChild(2).getChild(0).getChildCount()); // SHOW show 23

    System.out.println(tree.toStringTree());
  }
}
