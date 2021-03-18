package com.arjunsk.parser.antlr.listener.support;

import com.arjunsk.parser.antlr.SimplerLangBaseVisitor;
import com.arjunsk.parser.antlr.SimplerLangParser.LetContext;
import com.arjunsk.parser.antlr.SimplerLangParser.ShowContext;
import java.util.HashMap;
import java.util.Map;

/** Logic for handling LET and SHOW. */
public class SimplerLangVisitorImpl extends SimplerLangBaseVisitor<Void> {

  private final Map<String, Integer> variableMap;

  public SimplerLangVisitorImpl() {
    super();
    variableMap = new HashMap<>();
  }

  @Override
  public Void visitLet(LetContext ctx) {
    this.variableMap.put(ctx.VAR().getText(), Integer.parseInt(ctx.INT().getText()));
    return super.visitLet(ctx);
  }

  @Override
  public Void visitShow(ShowContext ctx) {
    if (ctx.INT() != null) {
      System.out.println(ctx.INT().getText());
    } else if (ctx.VAR() != null) {
      System.out.println(this.variableMap.get(ctx.VAR().getText()));
    }

    return super.visitShow(ctx);
  }
}
