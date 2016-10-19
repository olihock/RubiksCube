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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.junit.Before;
import org.junit.Test;

import com.videaps.cube.solving.toggling.Features;


@Deployment(resources = {
		"com/videaps/cube/solving/SolveCube.bpmn",

		"com/videaps/cube/solving/core/Twist.bpmn",
		"com/videaps/cube/solving/core/Tilt.bpmn",
		"com/videaps/cube/solving/core/Turn.bpmn",

		"com/videaps/cube/solving/walking/MoveFace.bpmn",
		"com/videaps/cube/solving/walking/WalkCube.bpmn",
		"com/videaps/cube/solving/walking/CubeMove.dmn",
		"com/videaps/cube/solving/walking/Direction.dmn",
		"com/videaps/cube/solving/walking/moves/BackToDown.bpmn",
		"com/videaps/cube/solving/walking/moves/DownToBack.bpmn",
		"com/videaps/cube/solving/walking/moves/DownToDown.bpmn",
		"com/videaps/cube/solving/walking/moves/DownToFront.bpmn",
		"com/videaps/cube/solving/walking/moves/DownToLeft.bpmn",
		"com/videaps/cube/solving/walking/moves/DownToRight.bpmn",
		"com/videaps/cube/solving/walking/moves/DownToUpper.bpmn",
		"com/videaps/cube/solving/walking/moves/FrontToDown.bpmn",
		"com/videaps/cube/solving/walking/moves/LeftToDown.bpmn",
		"com/videaps/cube/solving/walking/moves/RightToDown.bpmn",
		"com/videaps/cube/solving/walking/moves/UpperToDown.bpmn",
	} )
public class SolveCubeTest extends BaseTest {

	@Before
	public void setUp() throws Exception {
		toggle.disable(Features.USE_LEJOS);
	}

	
	@Test
	public void test() {
		System.out.println(new Exception().getStackTrace()[0].getMethodName());

		Map<String, Object> variables = new HashMap<String, Object>();
		
		List<String> notations = new ArrayList<String>();
		notations.add("R");
		
		variables.put("notations", notations);
		
		ProcessInstance processInstance = processEngine.getRuntimeService()
				.startProcessInstanceByKey("Process_SolveCube", variables);
		assertTrue(processInstance.isEnded());  
	}
}
