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
package com.videaps.cube.solving;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.junit.Rule;
import org.junit.Test;
import org.togglz.junit.TogglzRule;

import com.videaps.cube.solving.toggling.Features;


@Deployment(resources = {
		"com/videaps/cube/solving/ScanCubeProcess.bpmn",
		"com/videaps/cube/solving/moves/cube/FaceSequence.dmn",
		"com/videaps/cube/solving/moves/basic/TiltProcess.bpmn",
		"com/videaps/cube/solving/moves/cube/UpperToFront.bpmn",
		"com/videaps/cube/solving/moves/cube/FrontToDown.bpmn"
	} )
public class ScanCubeTest extends BaseTest {

	@Test
	public void testUpperToFront() {
		toggle.disable(Features.USE_LEJOS);

		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("fromFace", "U");
		variables.put("toFace", "F");
		
		ProcessInstance processInstance = processEngine.getRuntimeService().startProcessInstanceByKey("Process_ScanCube", variables);
		assertTrue(processInstance.isEnded());  
	}

	
	@Test
	public void testFrontToDown() {
		toggle.disable(Features.USE_LEJOS);

		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("fromFace", "F");
		variables.put("toFace", "D");
		
		ProcessInstance processInstance = processEngine.getRuntimeService().startProcessInstanceByKey("Process_ScanCube", variables);
		assertTrue(processInstance.isEnded());  
	}


	@Test
	public void testDownToLeft() {
		toggle.disable(Features.USE_LEJOS);

		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("fromFace", "D");
		variables.put("toFace", "L");
		
		ProcessInstance processInstance = processEngine.getRuntimeService().startProcessInstanceByKey("Process_ScanCube", variables);
		assertTrue(processInstance.isEnded());  
	}

	
	@Test
	public void testLeftToRight() {
		toggle.disable(Features.USE_LEJOS);

		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("fromFace", "L");
		variables.put("toFace", "R");
		
		ProcessInstance processInstance = processEngine.getRuntimeService().startProcessInstanceByKey("Process_ScanCube", variables);
		assertTrue(processInstance.isEnded());  
	}

	
	@Test
	public void testRightToBack() {
		toggle.disable(Features.USE_LEJOS);

		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("fromFace", "R");
		variables.put("toFace", "B");
		
		ProcessInstance processInstance = processEngine.getRuntimeService().startProcessInstanceByKey("Process_ScanCube", variables);
		assertTrue(processInstance.isEnded());  
	}

	
	@Test
	public void testBackToUpper() {
		toggle.disable(Features.USE_LEJOS);

		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("fromFace", "B");
		variables.put("toFace", "U");
		
		ProcessInstance processInstance = processEngine.getRuntimeService().startProcessInstanceByKey("Process_ScanCube", variables);
		assertTrue(processInstance.isEnded());  
	}

}
