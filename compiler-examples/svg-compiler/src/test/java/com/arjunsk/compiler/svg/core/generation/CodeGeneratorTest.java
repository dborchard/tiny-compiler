package com.arjunsk.compiler.svg.core.generation;

import static org.junit.Assert.assertNotNull;

import com.arjunsk.compiler.svg.core.parsing.Lexer;
import com.arjunsk.compiler.svg.core.parsing.Parser;
import com.arjunsk.compiler.svg.core.transforming.Transformer;
import com.arjunsk.compiler.svg.domain.lexer.ast.AstNode;
import com.arjunsk.compiler.svg.domain.lexer.token.Token;
import com.arjunsk.compiler.svg.domain.transformer.ast.SvgAstNode;
import com.arjunsk.compiler.utils.FileReaderUtil;
import java.io.FileWriter;
import java.util.List;
import java.util.Objects;
import org.junit.Test;

public class CodeGeneratorTest {

  @Test
  public void test_generate() {

    // 1. Arrange
    String hllCode =
        Objects.requireNonNull(FileReaderUtil.getResourceFileAsString("lexer/code.ck"));
    List<Token> tokens = Lexer.tokenize(hllCode);
    AstNode oldAst = new Parser(tokens).parse();
    SvgAstNode newAst = new Transformer().transform(oldAst);

    // 2. Act
    String result = new CodeGenerator().generate(newAst);

    // 3. Assert
    assertNotNull(result);

    // 4. Log
    System.out.println(result);
    saveToFile(result, "src/test/resources/output/code.svg");
  }

  private void saveToFile(String data, String filename) {
    try (FileWriter fw = new FileWriter(filename)) {
      fw.write(data);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
