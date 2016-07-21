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
import java.util.List;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 *
 */
public class BrickColorListener implements ExecutionListener {
	private static final Logger logger = LoggerFactory.getLogger(BrickColorListener.class);

	
	public void notify(DelegateExecution execution) throws Exception {
		
		@SuppressWarnings("unchecked")
		List<Number> brickColors = (List<Number>) execution.getVariable("brickColors");
		if(brickColors == null) {
			brickColors = new ArrayList<Number>();
			execution.setVariable("brickColors", brickColors);
		}
		
		Number color = (Number) execution.getVariable("getColorColor");
		logger.info("color="+color);
		
		brickColors.add(color);
		logger.info("brickColors="+brickColors);
		logger.info("size="+brickColors.size());
	}

}
