package com.arjunsk.compiler.svg.domain.transformer.ast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class SvgAstNode {

  private final String tag;
  private final Map<String, Object> attributes;
  private final List<SvgAstNode> body;

  public SvgAstNode(String tag) {
    this.tag = tag;
    this.attributes = new HashMap<>();
    this.body = new ArrayList<>();
  }

  public void appendBody(SvgAstNode node) {
    this.body.add(node);
  }

  public void appendAttributes(String key, Object value) {
    attributes.put(key, value);
  }

  public String getTag() {
    return tag;
  }

  public Iterator<Entry<String, Object>> getAttributes() {
    return attributes.entrySet().iterator();
  }

  public Iterator<SvgAstNode> getBody() {
    return body.iterator();
  }
}
