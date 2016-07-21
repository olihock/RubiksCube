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
package com.videaps.cube.solving.behaviour;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.junit.Rule;
import org.junit.Test;
import org.togglz.junit.TogglzRule;

import com.videaps.cube.solving.toggling.Features;


@Deployment(resources = {
		"com/videaps/cube/solving/behaviours/InitialiseCubeProcess.bpmn",
		"com/videaps/cube/solving/behaviours/ScanAndTurnProcess.bpmn" 
	} )
public class ScanAndTurnTest {

	@Rule
	public ProcessEngineRule pe = new ProcessEngineRule();

	@Rule
	public TogglzRule togglzRule = TogglzRule.allEnabled(Features.class);
	
	
	@Test
	public void test() throws InterruptedException {
		ProcessInstance pi = pe.getRuntimeService().startProcessInstanceByKey(
				"Process_InitialiseCube", InitialiseCubeTest.variables());
		assertTrue(pi.isEnded());  

		Thread.sleep(5000);
		
		pi = pe.getRuntimeService().startProcessInstanceByKey("Process_ScanAndTurn", variables());
		assertTrue(pi.isEnded());  
		
	}


	public static Map<String, Object> variables() {
		Map<String, Object> variables = new HashMap<String, Object>();
		
		variables.put("rotateMotorMotorPort", "A");
		variables.put("rotateMotorSpeed", 15);
		variables.put("rotateMotorAngle", -230);
		variables.put("rotateMotorImmediateReturn", true);

		variables.put("getColorSensorPort", "S1");

		variables.put("isMotorMovingMotorPort", "A");
		return variables;
	}

}
