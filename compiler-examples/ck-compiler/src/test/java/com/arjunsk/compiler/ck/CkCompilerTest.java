package com.arjunsk.compiler.ck;

import com.arjunsk.compiler.ck.domain.tree.ParseTree;
import com.arjunsk.compiler.ck.lexer.Lexer;
import com.arjunsk.compiler.ck.parser.Parser;
import com.arjunsk.compiler.ck.utils.FileReaderUtil;
import com.arjunsk.compiler.ck.visitor.codegenerator.CodeGeneratorVisitor;
import com.arjunsk.compiler.ck.visitor.consoleprinter.ConsolePrinterVisitor;
import org.junit.Assert;
import org.junit.Test;

public class CkCompilerTest {

  @Test
  public void compile() {
    // 1. Input Code
    final String sourceCode = FileReaderUtil.getResourceFileAsString("input/CgSample.ck");

    // 2. Lexer
    assert sourceCode != null;
    Lexer lexer = new Lexer(sourceCode);

    // 3. Parser
    Parser parser = new Parser(lexer);
    ParseTree tree = parser.parseProgram();

    Assert.assertNotNull(tree);

    // 4.1 Console Print Visitor
    tree.accept(new ConsolePrinterVisitor());

    // 4.2 Compiler Visitor
    tree.accept(new CodeGeneratorVisitor());
  }
}
