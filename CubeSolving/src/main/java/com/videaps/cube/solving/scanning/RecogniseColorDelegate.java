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

import java.util.List;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.videaps.cube.solving.access.ColorPicker;


/**
 *
 */
public class RecogniseColorDelegate implements JavaDelegate {
	private static final Logger logger = LoggerFactory.getLogger(RecogniseColorDelegate.class);

	public void execute(DelegateExecution execution) throws Exception {
		logger.info(execution.getCurrentActivityName());

		@SuppressWarnings("unchecked")
		List<String> colors = (List<String>) execution.getVariable("brickColors");
		logger.info("colors="+colors);

		ColorPicker colorPicker = new ColorPicker();
		String colorStr = String.join("", colors);
		logger.info("colorStr="+colorStr);
		String mostFrequentColor = colorPicker.mostFrequentColor(colorStr);
		logger.info("mostFrequentColor="+mostFrequentColor);

		execution.setVariable("color", mostFrequentColor);
	}

}
