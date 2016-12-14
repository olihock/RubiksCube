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
package com.videaps.cube.solving.access.sensor;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.junit.Before;
import org.junit.Test;

import com.videaps.cube.solving.BaseTest;
import com.videaps.cube.solving.toggling.Features;


@Deployment(resources = {
		"com/videaps/cube/solving/access/sensor/CalibrateTest.bpmn"
	} )
public class CalibrateTest extends BaseTest {

	@Before
	public void setUp() {
		toggle.disable(Features.USE_LEJOS);
	}

	
	@Test
	public void test() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(CalibrateDelegate.SENSOR_PORT_KEY, "S2");
		
		ProcessInstance processInstance = processEngine.getRuntimeService()
				.startProcessInstanceByKey("Process_CalibrateTest", params);
		
		int returnCode = (Integer) params.get(CalibrateDelegate.RETURN_CODE_KEY);
		System.out.println("returnCode="+returnCode);
		assertEquals(CalibrateDelegate.RETURN_CODE_NEUTRAL, returnCode);
		
		assertTrue(processInstance.isEnded());  
	}

}
