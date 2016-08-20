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

import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.junit.Before;
import org.junit.Test;

import com.videaps.cube.solving.BaseTest;
import com.videaps.cube.solving.toggling.Features;


@Deployment(resources = {
		"com/videaps/cube/solving/moves/ScanFace.bpmn",
		"com/videaps/cube/solving/moves/basic/TiltProcess.bpmn",
		"com/videaps/cube/solving/moves/basic/TurnProcess.bpmn",
		"com/videaps/cube/solving/moves/cube/FaceSequence.dmn",
		"com/videaps/cube/solving/moves/cube/UpperToFront.bpmn",
		"com/videaps/cube/solving/moves/cube/FrontToDown.bpmn",
		"com/videaps/cube/solving/moves/cube/DownToLeft.bpmn",
		"com/videaps/cube/solving/moves/cube/LeftToRight.bpmn",
		"com/videaps/cube/solving/moves/cube/RightToBack.bpmn",
		"com/videaps/cube/solving/moves/cube/BackToUpper.bpmn",
	} )
public class ScanFaceTest extends BaseTest {

	@Before
	public void setUp() {
		toggle.disable(Features.USE_LEJOS);
	}
	
	
	@Test
	public void testUpperToFront() {
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("fromFace", "U");
		variables.put("toFace", "F");
		
		ProcessInstance processInstance = processEngine.getRuntimeService().startProcessInstanceByKey("Process_ScanFace", variables);
		assertTrue(processInstance.isEnded());  
	}

	
	@Test
	public void testFrontToDown() {
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("fromFace", "F");
		variables.put("toFace", "D");
		
		ProcessInstance processInstance = processEngine.getRuntimeService().startProcessInstanceByKey("Process_ScanFace", variables);
		assertTrue(processInstance.isEnded());  
	}


	@Test
	public void testDownToLeft() {
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("fromFace", "D");
		variables.put("toFace", "L");
		
		ProcessInstance processInstance = processEngine.getRuntimeService().startProcessInstanceByKey("Process_ScanFace", variables);
		assertTrue(processInstance.isEnded());  
	}

	
	@Test
	public void testLeftToRight() {
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("fromFace", "L");
		variables.put("toFace", "R");
		
		ProcessInstance processInstance = processEngine.getRuntimeService().startProcessInstanceByKey("Process_ScanFace", variables);
		assertTrue(processInstance.isEnded());  
	}

	
	@Test
	public void testRightToBack() {
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("fromFace", "R");
		variables.put("toFace", "B");
		
		ProcessInstance processInstance = processEngine.getRuntimeService().startProcessInstanceByKey("Process_ScanFace", variables);
		assertTrue(processInstance.isEnded());  
	}

	
	@Test
	public void testBackToUpper() {
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("fromFace", "B");
		variables.put("toFace", "U");
		
		ProcessInstance processInstance = processEngine.getRuntimeService().startProcessInstanceByKey("Process_ScanFace", variables);
		assertTrue(processInstance.isEnded());  
	}

}
