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
     * Neither the name of the <organization> nor the
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
package me.jaimegarza.syntax.definition;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class State {
  int id;
  boolean review;
  int from;
  Symbol symbol;
  List<Dot> markers = new LinkedList<Dot>();
  int defaultValue;
  int howMany;
  List<Action> actions;
  int position;
  int actionSize = 0;
  int message;
  private int mark = 0;
  private List<Dot> cut;
  private int row[];

  public State(int id, int from, Symbol symbol) {
    this();
    this.id = id;
    this.review = true;
    this.from = from;
    this.symbol = symbol;
    this.message = -1;
  }

  public State() {
  }

  public boolean isReview() {
    return review;
  }

  public void setReview(boolean review) {
    this.review = review;
  }

  public int getFrom() {
    return from;
  }

  public void setFrom(int from) {
    this.from = from;
  }

  public Symbol getSymbol() {
    return symbol;
  }

  public void setSymbol(Symbol symbol) {
    this.symbol = symbol;
  }

  public int getSymbolId() {
    return symbol == null ? -1 : symbol.getId();
  }

  public List<Dot> getMarkers() {
    return markers;
  }

  public List<Dot> getOriginalMarkers() {
    if (mark == markers.size()) {
      return markers;
    } else {
      return markers.subList(0, mark);
    }
  }

  public Dot getMarker(int i) {
    Dot marker = null;
    if (markers != null && i < markers.size()) {
      marker = markers.get(i);
    }
    return marker;
  }

  public void addMarker(Dot dot) {
    if (!markers.contains(dot)) {
      markers.add(dot);
    }
  }

  public void addAllMarkers(List<Dot> dots) {
    for (Dot dot : dots) {
      addMarker(dot);
    }
  }

  public int getDefaultValue() {
    return defaultValue;
  }

  public void setDefaultValue(int defaultValue) {
    this.defaultValue = defaultValue;
  }

  public List<Action> getActions() {
    return actions;
  }

  public void setActions(List<Action> actions) {
    this.actions = actions;
  }

  public int getPosition() {
    return position;
  }

  public void setPosition(int position) {
    this.position = position;
  }

  public int getMessage() {
    return message;
  }

  public void setMessage(int message) {
    this.message = message;
  }

  public int getActionSize() {
    return actionSize;
  }

  public void setActionSize(int actionSize) {
    this.actionSize = actionSize;
  }

  public int getHowMany() {
    return howMany;
  }

  public void setHowMany(int howMany) {
    this.howMany = howMany;
  }

  public void mark() {
    mark = markers.size();
  }

  public void cutToMark() {
    if (mark == markers.size()) {
      cut = null;
    } else {
      cut = markers.subList(mark, markers.size());
      markers = markers.subList(0, mark);
    }
  }

  public void restore() {
    if (cut != null) {
      List<Dot> currentMarkers = markers;
      markers = new LinkedList<Dot>();
      markers.addAll(currentMarkers);
      markers.addAll(cut);
      cut = null;
    }
  }

  public int[] getRow() {
    return row;
  }

  public void setRow(int[] row) {
    this.row = Arrays.copyOf(row, row.length);
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }

    try {
      State s = (State) obj;
      // skipping review on purpose, since review is really volatile
      // skipping defaultValue, actions since they may be transitional (i.e.
      // about to be calculated and this is why I am comparing in the first
      // place)
      return from == s.from && symbol.equals(s.symbol) && markers.equals(s.markers) && // maybe
                                                                                       // not
               position == s.position;

    } catch (NullPointerException unused) {
      return false;
    } catch (ClassCastException unused) {
      return false;
    }
  }

  @Override
  public String toString() {
    String sym = symbol != null ? symbol.toString() : "(no symbol)";
    String s = "" + id + ". " + (from == -1 ? "starting state" : ("from " + from + " with " + sym)) + "\n";
    for (Dot dot : markers) {
      s = s + dot.getRule().getLeftHand() + " -> " + dot.toString();
    }
    return s;
  }

}
