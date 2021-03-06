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
		"com/videaps/cube/solving/moving/WalkCube.bpmn",
		"com/videaps/cube/solving/moving/CubeMove.dmn",
		"com/videaps/cube/solving/moving/Direction.dmn",
		"com/videaps/cube/solving/moves/basic/Twist.bpmn",
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
public class MoveFaceTest extends BaseTest {

	@Before
	public void setUp() {
		toggle.disable(Features.USE_LEJOS);
	}
	
	
	@Test
	public void test() {
		System.out.println(new Exception().getStackTrace()[0].getMethodName());
		
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("notation", "F");
		
		ProcessInstance processInstance = processEngine.getRuntimeService().startProcessInstanceByKey("Process_MoveFace", variables);
		assertTrue(processInstance.isEnded());  
	}

}
