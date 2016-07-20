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
		"com/videaps/cube/solving/behaviours/InitialiseCubeProcess.bpmn" } )
public class InitialiseCubeTest {

	@Rule
	public ProcessEngineRule pe = new ProcessEngineRule();

	@Rule
	public TogglzRule togglzRule = TogglzRule.allEnabled(Features.class);
	
	
	@Test
	public void test() {
		Map<String, Object> variables = new HashMap<String, Object>();

		variables.put("initialiseCubeMotorPort", "C");
		variables.put("initialiseCubeSpeed", 75);
		variables.put("initialiseCubeImmediateReturn", true);
		variables.put("initialiseCubeAngle", 110);
		
		variables.put("moveTurnTableMotorPort", "A");
		variables.put("moveTurnTableSpeed", 75);
		variables.put("moveTurnTableImmediateReturn", true);
		variables.put("moveTurnTableAngle", -30);
		
		ProcessInstance pi = pe.getRuntimeService().startProcessInstanceByKey("Process_InitialiseCube", variables);
		assertTrue(pi.isEnded());  
		
	}

}
