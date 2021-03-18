package com.arjunsk.parser.antlr.listener.support;

import com.arjunsk.parser.antlr.SimplerLangBaseListener;
import com.arjunsk.parser.antlr.SimplerLangParser.LetContext;
import com.arjunsk.parser.antlr.SimplerLangParser.ShowContext;
import java.util.HashMap;
import java.util.Map;

/** Logic for handling LET and SHOW. */
public class SimplerLangListenerImpl extends SimplerLangBaseListener {

  private final Map<String, Integer> variableMap;

  public SimplerLangListenerImpl() {
    super();
    this.variableMap = new HashMap<>();
  }

  @Override
  public void exitLet(LetContext ctx) {
    this.variableMap.put(ctx.VAR().getText(), Integer.parseInt(ctx.INT().getText()));
    super.exitLet(ctx);
  }

  @Override
  public void exitShow(ShowContext ctx) {
    if (ctx.INT() != null) {
      System.out.println(ctx.INT().getText());
    } else if (ctx.VAR() != null) {
      System.out.println(this.variableMap.get(ctx.VAR().getText()));
    }
    super.exitShow(ctx);
  }
}
