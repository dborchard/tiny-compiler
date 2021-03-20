package com.arjunsk.compiler.ck.parser;

import com.arjunsk.compiler.ck.domain.tree.ParseTree;
import com.arjunsk.compiler.ck.lexer.Lexer;
import org.junit.Assert;
import org.junit.Test;

public class ParserTest {

  @Test
  public void test_parser() {

    // 1. Arrange
    String sourceCode = "a = 10\n" + "show a\n" + "show 20";
    Lexer lexer = new Lexer(sourceCode);

    // 2. Act
    Parser parser = new Parser(lexer);
    ParseTree tree = parser.parseProgram();

    // 3. Assert
    Assert.assertEquals(3, tree.getChildCount());
    Assert.assertEquals(1, tree.getChild(0).getChildCount()); // Statement
    Assert.assertEquals(2, tree.getChild(0).getChild(0).getChildCount()); // LET a=10

    Assert.assertEquals(1, tree.getChild(1).getChildCount()); // Statement
    Assert.assertEquals(1, tree.getChild(1).getChild(0).getChildCount()); // SHOW show a

    Assert.assertEquals(1, tree.getChild(2).getChildCount()); // Statement
    Assert.assertEquals(1, tree.getChild(2).getChild(0).getChildCount()); // SHOW show 23

    Assert.assertEquals(
        "( ProgramContext( ( StatementContext( ( LetContext( a  10 ) ) ) )  ( StatementContext( ( ShowContext( a ) ) ) )  ( StatementContext( ( ShowContext( 20 ) ) ) ) ) )",
        tree.toStringTree());
  }
}
