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

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.videaps.cube.solving.rubik.Computer;
import com.videaps.cube.solving.toggling.Features;


/**
 *
 */
public class CalculateDelegate implements JavaDelegate {
	private static final Logger logger = LoggerFactory.getLogger(CalculateDelegate.class);
	

	public void execute(DelegateExecution execution) throws Exception {
		@SuppressWarnings("unchecked")
		List<String> cubeStateList = (List<String>) execution.getVariable("cubeStateList");
		
		List<String> notations = new ArrayList<String>();
		if(Features.USE_LEJOS.isActive()) {
			String algorithm = new Computer().solveCube(cubeStateList);
			algorithm = tidyUpAlgorithm(algorithm);
			notations = splitAlgorithm(algorithm);
		}
		
		execution.setVariable("notations", notations);
		logger.info("notations="+notations);
	}

	
	private String tidyUpAlgorithm(String algorithm) {
		algorithm = algorithm.replace("·", "");
		algorithm = algorithm.replace("  ", " ");
		return algorithm;
	}
	
	private List<String> splitAlgorithm(String algorithm) {
		StringTokenizer tokenizer = new StringTokenizer(algorithm);
		List<String> notations = new ArrayList<String>();
		while(tokenizer.hasMoreTokens()) {
			notations.add(tokenizer.nextToken());
		}
		return notations;
	}

}
