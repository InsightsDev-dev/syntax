package me.jaimegarza.syntax.code;

import java.util.ListResourceBundle;

/**
 * Fragments of code to be used for code generation
 * when the language is pascal
 *
 * Please be advised that {1} is interpreted to be the
 * indentation string required.  Always.  This is a
 * contract.
 *
 * @author jaimegarza@gmail.com
 *
 */
public class Fragments_pascal extends ListResourceBundle {

  @Override
  protected Object[][] getContents() {
    return contents;
  }
  
  /**
   * The fragments
   */
  Object[] contents [] = {
      {Fragments.HELLO,"Pello Porld"}, // keep, for unit testing
      {Fragments.STXSTACK, "StxStack[pStxStack{0}]"},
      {Fragments.CURRENT_CHAR, "StxChar"},
      {Fragments.LEXICAL_VALUE, "StxValue"},
      {Fragments.GETC, "StxChar := StxNextChar()"},
  };

}
