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

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.lang3.tuple.Pair;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * This delegate creates a list of pairs, that reflects the face order to scan the cube face brick colors.
 */
public class FaceOrderDelegate implements JavaDelegate {
	private static final Logger logger = LoggerFactory.getLogger(FaceOrderDelegate.class);

	
	public void execute(DelegateExecution execution) throws Exception {
		Collection<Pair<String, String>> faceOrderList = new ArrayList<Pair<String, String>>();
		faceOrderList.add(Pair.of("U", "F"));
		faceOrderList.add(Pair.of("F", "D"));
		faceOrderList.add(Pair.of("D", "L"));
		faceOrderList.add(Pair.of("L", "R"));
		faceOrderList.add(Pair.of("R", "B"));
		faceOrderList.add(Pair.of("B", "U"));
		
		execution.setVariable("faceOrderList", faceOrderList);
		logger.info("faceOrderList="+faceOrderList);
	}

}
