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

import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.junit.Before;
import org.junit.Test;

import com.videaps.cube.solving.toggling.Features;


@Deployment(resources = {
		"com/videaps/cube/solving/ScanCube.bpmn",

		"com/videaps/cube/solving/core/Tilt.bpmn",
		"com/videaps/cube/solving/core/Turn.bpmn",

		"com/videaps/cube/solving/scanning/InitialiseFace.bpmn",
		"com/videaps/cube/solving/scanning/ScanFace.bpmn",
		"com/videaps/cube/solving/scanning/FaceSequence.dmn",
		"com/videaps/cube/solving/scanning/ScanAllBricks.bpmn",
		"com/videaps/cube/solving/scanning/ScanSingleBrick.bpmn",
		"com/videaps/cube/solving/scanning/moves/FrontFaceUp.bpmn",
		"com/videaps/cube/solving/scanning/moves/DownFaceUp.bpmn",
		"com/videaps/cube/solving/scanning/moves/LeftFaceUp.bpmn",
		"com/videaps/cube/solving/scanning/moves/RightFaceUp.bpmn",
		"com/videaps/cube/solving/scanning/moves/BackFaceUp.bpmn",
		"com/videaps/cube/solving/scanning/moves/UpperFaceUp.bpmn",
	} )
public class ScanCubeTest extends BaseTest {

	@Before
	public void setUp() throws Exception {
		toggle.enable(Features.USE_LEJOS);
	}

	
	@Test
	public void test() {
		ProcessInstance processInstance = processEngine.getRuntimeService().startProcessInstanceByKey("Process_ScanCube");
		assertTrue(processInstance.isEnded());  
	}

}
