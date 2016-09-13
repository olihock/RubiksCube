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
package com.videaps.cube.solving.algorithm;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.impl.pvm.runtime.ExecutionImpl;
import org.junit.Test;

import com.videaps.cube.solving.algorithm.CalculateDelegate;

public class CalculateTest {

	@Test
	public void test() throws Exception {
		String[] cubeState = { "U:YWBOWYOGG", "F:YYYORRYWW", "D:RGGRYWOYG", "L:OBBGGGWBB", "R:RBWYBWRBO", "B:ROGRORWOB" };
		List<String> cubeStateList = Arrays.asList(cubeState);

		DelegateExecution execution = new ExecutionImpl();
		execution.setVariable("cubeStateList", cubeStateList);

		CalculateDelegate calculateAlgorithmDelegate = new CalculateDelegate();
		calculateAlgorithmDelegate.execute(execution);
		
		@SuppressWarnings("unchecked")
		List<String> notations = (List<String>) execution.getVariable("notations");
		assertNotNull(notations);
	}

}
