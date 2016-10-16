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
		"com/videaps/cube/solving/moving/MoveFace.bpmn",
		"com/videaps/cube/solving/moving/CubeMove.dmn",
		"com/videaps/cube/solving/moving/Direction.dmn",
		"com/videaps/cube/solving/moves/basic/TiltProcess.bpmn",
		"com/videaps/cube/solving/moves/basic/TurnProcess.bpmn",
		"com/videaps/cube/solving/moves/rack/BackToDown.bpmn",
		"com/videaps/cube/solving/moves/rack/DownToBack.bpmn",
		"com/videaps/cube/solving/moves/rack/DownToDown.bpmn",
		"com/videaps/cube/solving/moves/rack/DownToFront.bpmn",
		"com/videaps/cube/solving/moves/rack/DownToLeft.bpmn",
		"com/videaps/cube/solving/moves/rack/DownToRight.bpmn",
		"com/videaps/cube/solving/moves/rack/DownToUpper.bpmn",
		"com/videaps/cube/solving/moves/rack/FrontToDown.bpmn",
		"com/videaps/cube/solving/moves/rack/LeftToDown.bpmn",
		"com/videaps/cube/solving/moves/rack/RightToDown.bpmn",
		"com/videaps/cube/solving/moves/rack/UpperToDown.bpmn"
	} )
public class WalkCubeTest extends BaseTest {

	@Before
	public void setUp() {
		toggle.disable(Features.USE_LEJOS);
	}
	
	
	@Test
	public void testBackToDown() {
		System.out.println(new Exception().getStackTrace()[0].getMethodName());
		
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("move", "BackToDown");
		
		ProcessInstance processInstance = processEngine.getRuntimeService().startProcessInstanceByKey("Process_BackToDown", variables);
		assertTrue(processInstance.isEnded());  
	}

	
	@Test
	public void testDownToBack() {
		System.out.println(new Exception().getStackTrace()[0].getMethodName());
		
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("move", "DownToBack");
		
		ProcessInstance processInstance = processEngine.getRuntimeService().startProcessInstanceByKey("Process_DownToBack", variables);
		assertTrue(processInstance.isEnded());  
	}

	
	@Test
	public void testDownToDown() {
		System.out.println(new Exception().getStackTrace()[0].getMethodName());
		
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("move", "DownToDown");
		
		ProcessInstance processInstance = processEngine.getRuntimeService().startProcessInstanceByKey("Process_DownToDown", variables);
		assertTrue(processInstance.isEnded());  
	}

	
	@Test
	public void testDownToFront() {
		System.out.println(new Exception().getStackTrace()[0].getMethodName());
		
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("move", "DownToFront");
		
		ProcessInstance processInstance = processEngine.getRuntimeService().startProcessInstanceByKey("Process_DownToFront", variables);
		assertTrue(processInstance.isEnded());  
	}

	
	@Test
	public void testDownToLeft() {
		System.out.println(new Exception().getStackTrace()[0].getMethodName());
		
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("move", "DownToLeft");
		
		ProcessInstance processInstance = processEngine.getRuntimeService().startProcessInstanceByKey("Process_DownToLeft_Rack", variables);
		assertTrue(processInstance.isEnded());  
	}

	
	@Test
	public void testDownToRight() {
		System.out.println(new Exception().getStackTrace()[0].getMethodName());
		
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("move", "DownToRight");
		
		ProcessInstance processInstance = processEngine.getRuntimeService().startProcessInstanceByKey("Process_DownToRight", variables);
		assertTrue(processInstance.isEnded());  
	}

	
	@Test
	public void testDownToUpper() {
		System.out.println(new Exception().getStackTrace()[0].getMethodName());
		
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("move", "DownToUpper");
		
		ProcessInstance processInstance = processEngine.getRuntimeService().startProcessInstanceByKey("Process_DownToUpper", variables);
		assertTrue(processInstance.isEnded());  
	}

	
	@Test
	public void testFrontToDown() {
		System.out.println(new Exception().getStackTrace()[0].getMethodName());
		
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("move", "FrontToDown");
		
		ProcessInstance processInstance = processEngine.getRuntimeService().startProcessInstanceByKey("Process_FrontToDown_Rack", variables);
		assertTrue(processInstance.isEnded());  
	}

	
	@Test
	public void testLeftToDown() {
		System.out.println(new Exception().getStackTrace()[0].getMethodName());
		
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("move", "LeftToDown");
		
		ProcessInstance processInstance = processEngine.getRuntimeService().startProcessInstanceByKey("Process_LeftToDown", variables);
		assertTrue(processInstance.isEnded());  
	}

	
	@Test
	public void testRightToDown() {
		System.out.println(new Exception().getStackTrace()[0].getMethodName());
		
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("move", "RightToDown");
		
		ProcessInstance processInstance = processEngine.getRuntimeService().startProcessInstanceByKey("Process_RightToDown", variables);
		assertTrue(processInstance.isEnded());  
	}

	
	@Test
	public void testUpperToDown() {
		System.out.println(new Exception().getStackTrace()[0].getMethodName());
		
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("move", "UpperToDown");
		
		ProcessInstance processInstance = processEngine.getRuntimeService().startProcessInstanceByKey("Process_UpperToDown", variables);
		assertTrue(processInstance.isEnded());  
	}

	
}
