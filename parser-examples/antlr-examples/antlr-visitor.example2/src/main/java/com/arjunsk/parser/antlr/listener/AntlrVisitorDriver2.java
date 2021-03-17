package com.arjunsk.parser.antlr.listener;

import com.arjunsk.parser.antlr.SimplerLangLexer;
import com.arjunsk.parser.antlr.SimplerLangParser;
import com.arjunsk.parser.antlr.listener.support.SimplerLangVisitorImpl;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class AntlrVisitorDriver2 {

  public static void main(String[] args) {
    String code =
        "a = 100\n"
            + "b = 150\n"
            + "show 10\n"
            + "show a\n"
            + "show b\n"
            + "show 121\n"
            + "show 200";

    compile(code);
  }

  private static void compile(String source) {

    SimplerLangLexer lexer = new SimplerLangLexer(CharStreams.fromString(source));
    SimplerLangParser parser = new SimplerLangParser(new CommonTokenStream(lexer));

    ParseTree tree = parser.program();
    SimplerLangVisitorImpl visitor = new SimplerLangVisitorImpl();
    tree.accept(visitor);
  }
}
