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
package me.jaimegarza.syntax.test.java;

import java.io.File;
import java.io.IOException;

import org.apache.commons.jci.compilers.CompilationResult;
import org.apache.commons.jci.problems.CompilationProblem;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import me.jaimegarza.syntax.AnalysisException;
import me.jaimegarza.syntax.OutputException;
import me.jaimegarza.syntax.ParsingException;
import me.jaimegarza.syntax.language.Language;
import me.jaimegarza.syntax.test.AbstractGenerationBase;

public class TestJavaLexerModes extends AbstractGenerationBase {

  static final String args[] = {
    "-v",
    "--algorithm",
    "l",
    "--language",
    "java",
    "--packing",
    "tabular",
    "--debug",
    "classpath:java-lexermodes.sy",
    "${file.language}" 
};


  @BeforeTest
  public void setUp() throws IOException {
    super.setUp(Language.java, "LexerMode");
  }

  @Override
  @AfterTest
  public void tearDown() {
    super.tearDown();
  }

  @Test
  public void testJavaLexerModes() throws ParsingException, AnalysisException, OutputException {
    generateLanguageFile(args);
    File source = new File(tmpLanguageFile);
    File sourceDir = source.getParentFile();
    CompilationResult result = compileJavaFile(source, sourceDir);

    if (result.getErrors().length > 0) {
      for (CompilationProblem problemo : result.getErrors()) {
        if (problemo.isError()) {
          System.err.println(problemo.toString());
        }
      }
      Assert.fail("Errors during the compilation of the output java file");
    }
  }
}