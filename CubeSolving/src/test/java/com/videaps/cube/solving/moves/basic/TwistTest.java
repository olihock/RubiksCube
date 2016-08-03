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
package com.videaps.cube.solving.moves.basic;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.junit.Rule;
import org.junit.Test;
import org.togglz.junit.TogglzRule;

import com.videaps.cube.solving.rubik.Direction;
import com.videaps.cube.solving.toggling.Features;


@Deployment(resources = {
		"com/videaps/cube/solving/moves/TwistProcess.bpmn" } )
public class TwistTest {

	@Rule
	public TogglzRule toggle = TogglzRule.allEnabled(Features.class);

	@Rule
	public ProcessEngineRule processEngine = new ProcessEngineRule();

	
	@Test
	public void test() {
		final int angle = 35;
		
		Map<String, Object> variables = new HashMap<String, Object>();
		
		variables.put("holdCubePort", "B");
		variables.put("holdCubeSpeed", 350);
		variables.put("holdCubeAngle", angle);
		
		variables.put("turnTablePort", "A");
		variables.put("turnTableSpeed", 350);
		variables.put("turnTableDirection", Direction.RIGHT.getValue());
		variables.put("turnTableCount", 2);
		
		variables.put("delayMilliseconds", 2);
		
		variables.put("pullBackPort", "B");
		variables.put("pullBackSpeed", 350);
		variables.put("pullBackAcceleration", 0);
		variables.put("pullBackAngle", -angle);
		variables.put("pullBackImmediateReturn", false);
		
		toggle.enable(Features.USE_LEJOS);
		ProcessInstance processInstance = processEngine.getRuntimeService().startProcessInstanceByKey("Process_Twist", variables);
		assertTrue(processInstance.isEnded());  
	}

}
