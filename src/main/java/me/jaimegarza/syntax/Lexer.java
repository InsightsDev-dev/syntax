/*
 ===============================================================================
 Copyright (c) 1985, 2012, Jaime Garza
 All rights reserved.
 
 Redistribution and use in source and binary forms, with or without
 modification, are permitted provided that the following conditions are met:
     * Redistributions of source code must retain the above copyright
       notice, this list of conditions and the following disclaimer.
     * Redistributions in binary form must reproduce the above copyright
       notice, this list of conditions and the following disclaimer in the
       documentation and/or other materials provided with the distribution.
     * Neither the name of Jaime Garza nor the
       names of its contributors may be used to endorse or promote products
       derived from this software without specific prior written permission.
 
 THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
 DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 ===============================================================================
*/
package me.jaimegarza.syntax;

import java.io.IOException;

/**
 * Routines implemented by the lexer.  The lexer is the unit that breaks
 * the input stream as a series of tokens.
 * 
 * @author jgarza
 *
 */
public interface Lexer {
  /**
   * Get one character.  Place it in RuntimeData.currentCharacter
   * @return the character
   * @throws IOException on input error
   */
  char getCharacter() throws IOException;

  /**
   * Reverse one character.  Place it in RuntimeData.currentCharacter
   * @return the character
   * @throws IOException on input error
   */
  void ungetCharacter(char c);
  
  /**
   * Standard tokens.  This routine deals with the parser type of symbols
   * @return next token
   * @throws IOException on input error
   */
  int getNormalSymbol() throws IOException;
  
  /**
   * Regex tokens.  They are surrounded by '/'
   * @return the next regex symbol
   * @throws IOException on input error
   */
  int getRegexSymbol() throws IOException;
}