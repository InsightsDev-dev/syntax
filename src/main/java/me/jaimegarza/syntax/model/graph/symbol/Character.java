/*
Syntax is distributed under the Revised, or 3-clause BSD license
===============================================================================
Copyright (c) 1985, 2012, 2016, Jaime Garza
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:
    * Redistributions of source code must retain the above copyright
      notice, this list of conditions and the following disclaimer.
    * Redistributions in binary form must reproduce the above copyright
      notice, this list of conditions and the following disclaimer in the
      documentation and/or other materials provided with the distribution.
    * Neither the name of the copyright holder nor the
      names of its contributors may be used to endorse or promote products
      derived from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER BE LIABLE FOR ANY
DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
===============================================================================
*/
package me.jaimegarza.syntax.model.graph.symbol;

/**
 * Symbol that matches one specific character
 * @author jgarza
 *
 */
public class Character extends RegexSymbol {
  /** the character */
  private char character;
  
  /**
   * Default constructor
   * @param c the character
   */
  public Character(char c) {
    this.character = c;
  }

  @Override
  public boolean isEpsilon() {
    return false;
  }

  @Override
  public String canonical() {
    return "'" + character + "'";
  }

  @Override
  public boolean matches(char c) {
    return character == c;
  }
  
  @Override
  public int code() {
    return CHAR_CODE;
  }
  
  @Override
  public int sizeof() {
    return 3; // the code + the character twice
  }

  @Override
  public int[] getCodeArray() {
    int[] rc = new int[2];
    rc[0] = character;
    rc[1] = character;
    return rc;
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    try {
      Character c = (Character) o;
      return character == c.character;
    } catch (NullPointerException unused) {
      return false;
    } catch (ClassCastException unused) {
      return false;
    }
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int hash = prime + character;
    return hash;
  }
  
  @Override
  public String toString() {
    return canonical();
  }

  @Override
  public String toHtmlString() {
    return ""+character;
  }
}
