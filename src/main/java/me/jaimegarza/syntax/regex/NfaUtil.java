/*
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
package me.jaimegarza.syntax.regex;

import me.jaimegarza.syntax.model.nfa.AnyCharacterTransition;
import me.jaimegarza.syntax.model.nfa.CharacterRangeTransition;
import me.jaimegarza.syntax.model.nfa.CharacterTransition;
import me.jaimegarza.syntax.model.nfa.Construct;
import me.jaimegarza.syntax.model.nfa.DirectedGraph;
import me.jaimegarza.syntax.model.nfa.EpsilonTransition;
import me.jaimegarza.syntax.model.nfa.Node;

public class NfaUtil {

  public static Construct character(DirectedGraph graph, char c) {
    Node start = graph.newNode();
    Node end = graph.newNode();
    new CharacterTransition(start, end, c);
    return new Construct(start, end);
  }

  public static Construct characterRange(DirectedGraph graph, char from, char to) {
    Node start = graph.newNode();
    Node end = graph.newNode();
    new CharacterRangeTransition(start, end, from, to);
    return new Construct(start, end);
  }

  public static Construct any(DirectedGraph graph) {
    Node start = graph.newNode();
    Node end = graph.newNode();
    new AnyCharacterTransition(start, end);
    return new Construct(start, end);
  }
  
  public static Construct concat(DirectedGraph graph, Construct from, Construct to) {
    new EpsilonTransition(from.getEnd(), to.getStart());
    return new Construct(from.getStart(), to.getEnd());
  }
  
  public static Construct alternate(DirectedGraph graph, Construct a, Construct b) {
    Node start = graph.newNode();
    Node end = graph.newNode();
    new EpsilonTransition(start, a.getStart());
    new EpsilonTransition(start, b.getStart());
    new EpsilonTransition(a.getEnd(), end);
    new EpsilonTransition(b.getEnd(), end);
    return new Construct(start, end);
  }

  public static Construct zeroOrMany(DirectedGraph graph, Construct a) {
    Node start = graph.newNode();
    Node end = graph.newNode();
    new EpsilonTransition(start, a.getStart());
    new EpsilonTransition(a.getEnd(), end);
    new EpsilonTransition(start, end);
    new EpsilonTransition(a.getEnd(), a.getStart());
    return new Construct(start, end);
  }

  public static Construct oneOrMany(DirectedGraph graph, Construct a) {
    Node start = graph.newNode();
    Node end = graph.newNode();
    new EpsilonTransition(start, a.getStart());
    new EpsilonTransition(a.getEnd(), end);
    new EpsilonTransition(a.getEnd(), a.getStart());
    return new Construct(start, end);
  }

  public static Construct optional(DirectedGraph graph, Construct a) {
    new EpsilonTransition(a.getStart(), a.getEnd());
    return a;
  }
  
}