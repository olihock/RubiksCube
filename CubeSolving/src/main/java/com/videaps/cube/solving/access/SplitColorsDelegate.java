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
package com.videaps.cube.solving.access;

import java.util.List;

import org.apache.commons.collections4.ListUtils;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 *
 */
public class SplitColorsDelegate implements JavaDelegate {
	private static final Logger logger = LoggerFactory.getLogger(SplitColorsDelegate.class);

	
	public void execute(DelegateExecution execution) throws Exception {
		logger.info(execution.getCurrentActivityName());

		@SuppressWarnings("unchecked")
		List<String> brickColorsInner = (List<String>) execution.getVariable("brickColorsInner");

		StringBuffer colorBuf = new StringBuffer();
		ColorPicker colorPicker = new ColorPicker();
		
		int partitionSize = brickColorsInner.size() / ColorPicker.NO_OF_BRICKS_PER_FACE;
		logger.info("partitionSize="+partitionSize);
		List<List<String>> partitions = ListUtils.partition(brickColorsInner, partitionSize);
		for(List<String> partition : partitions) {
			String colorStr = String.join("", partition);
			logger.info("colorStr="+colorStr);
			String mostFrequentColor = colorPicker.mostFrequentColor(colorStr);
			logger.info("mostFrequentColor="+mostFrequentColor);
			colorBuf.append(mostFrequentColor);
		}
		
		execution.setVariable("brickColorsSplit", colorBuf.toString());
		logger.info("brickColorsSplit="+colorBuf.toString());
	}


}
