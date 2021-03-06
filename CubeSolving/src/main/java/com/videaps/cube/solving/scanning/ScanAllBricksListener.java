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
package com.videaps.cube.solving.scanning;

import java.util.Collection;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 *
 */
public class ScanAllBricksListener implements ExecutionListener {
	private static final Logger logger = LoggerFactory.getLogger(ScanAllBricksListener.class);

	
	public void notify(DelegateExecution execution) throws Exception {
		@SuppressWarnings("unchecked")
		Collection<String> cubeColors = (Collection<String>) execution.getVariable("cubeColors");
		execution.setVariable("cubeColors", cubeColors);

		String[] faceColors = (String[]) execution.getVariable("faceColors");

	    String fromFace = (String) execution.getVariable("fromFace");
	    String faceState = concate(fromFace, faceColors);
	    cubeColors.add(faceState);
	    
	    logger.info("faceState="+faceState);
	}
	
	
	private String concate(String face, String[] brickColors) {
		StringBuffer buf = new StringBuffer(face);
		buf.append(":");
		for(String brickColor : brickColors) {
			buf.append(brickColor);
		}
		return buf.toString();
	}

}
