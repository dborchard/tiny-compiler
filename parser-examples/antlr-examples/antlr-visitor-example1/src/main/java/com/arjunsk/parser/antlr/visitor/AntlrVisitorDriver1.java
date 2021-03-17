package com.arjunsk.parser.antlr.visitor;

import com.arjunsk.parser.antlr.CalculatorLexer;
import com.arjunsk.parser.antlr.CalculatorParser;
import com.arjunsk.parser.antlr.visitor.support.CalculatorVisitorImpl;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class AntlrVisitorDriver1 {

  public static void main(String[] args) {

    AntlrVisitorDriver1 driver = new AntlrVisitorDriver1();

    System.out.println(driver.compile("2 + 5")); // 7.0
    System.out.println(driver.compile("2 * 5")); // 10.0
    System.out.println(driver.compile("5 - 3")); // 2.0
    System.out.println(driver.compile("5 / 3")); // 1.6666666666666667
  }

  private Double compile(String source) {

    CalculatorLexer lexer = new CalculatorLexer(CharStreams.fromString(source));
    CalculatorParser parser = new CalculatorParser(new CommonTokenStream(lexer));

    ParseTree tree = parser.operation();

    CalculatorVisitorImpl visitor = new CalculatorVisitorImpl();
    return tree.accept(visitor);
  }
}
