package com.arjunsk.compiler.ck.domain.tree;

import com.arjunsk.compiler.ck.visitor.Visitor;

/** visitor pattern, 'Visitable' allows business logic to be separated from domain entities. */
public interface Visitable {

  /**
   * @param visitor Base Visitor or Custom Visitor Implementation.
   * @param <T> Output Type Generic present in the Visitor Implementation.
   * @return the output based on the datatype passed in Visitor implementation
   *     <p>Eg:- public class SimplerLangCustomVisitor extends SimplerLangBaseVisitor<Void>
   *     <p>Here T = Void, visitor is of type SimplerLangBaseVisitor<Void>
   */
  <T> T accept(Visitor<? extends T> visitor);
}
