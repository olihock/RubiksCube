/*
 Copyright (c) 2016 Videa Project Services GmbH

 Permission is hereby granted, free of charge, to any person obtaining a copy of this software 
 and associated documentation files (the "Software"), to deal in the Software without restriction, 
 including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, 
 and/or sell copies of the Software,and to permit persons to whom the Software is furnished to do so, 
 subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all copies or substantial 
 portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT 
 NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. 
 IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, 
 WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE 
 SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/
package com.videaps.cube.solving.moves;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.camunda.bpm.dmn.engine.DmnDecision;
import org.camunda.bpm.dmn.engine.DmnDecisionTableResult;
import org.camunda.bpm.dmn.engine.DmnEngine;
import org.camunda.bpm.dmn.engine.DmnEngineConfiguration;
import org.camunda.bpm.dmn.engine.test.DmnEngineRule;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.junit.Rule;
import org.junit.Test;


public class FaceSequenceTest {

	@Rule
	public DmnEngineRule dmnEngineRule = new DmnEngineRule();


	@Test
	public void test() throws FileNotFoundException {
		DmnEngine dmnEngine = DmnEngineConfiguration.createDefaultDmnEngineConfiguration().buildEngine();

	    File dmn = new File("C:/Users/Oliver/Development/github/RubiksCube/CubeSolving/src/main/resources/com/videaps/cube/solving/moves/FaceSequenceDecision.dmn");
	    InputStream inputStream = new FileInputStream(dmn);
	    
	    VariableMap variables = Variables.createVariables();
	    variables.put("fromFace", "U");
	    variables.put("toFace", "F");

	    DmnDecision decision = dmnEngine.parseDecision("faceSequence", inputStream);
	    DmnDecisionTableResult result = dmnEngine.evaluateDecisionTable(decision, variables);
	    
	    String sequence = (String) result.getSingleResult().get("moveSequence");
	    assertEquals("tilt", sequence);
	    System.out.println("sequence="+sequence);
	}

}
