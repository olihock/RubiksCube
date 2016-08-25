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

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 *
 */
public class ScanCubeDelegate implements JavaDelegate {
	private static final Logger logger = LoggerFactory.getLogger(ScanCubeDelegate.class);

	
	public void execute(DelegateExecution execution) throws Exception {
		logger.info(execution.getCurrentActivityName());

		@SuppressWarnings("unchecked")
		Collection<String> cubeColors = (Collection<String>) execution.getVariable("cubeColors");
		logger.info("cubeColors="+cubeColors);
		
		Collection<String> replacedCubeColors = replaceCenterBrick(cubeColors);
		
		String cubeColorsStr = ArrayUtils.toString(cubeColors);
		int xCount = countUndetected(cubeColorsStr);
		logger.info("xCount="+xCount);
		if(xCount == 1) {
			replacedCubeColors = replaceOneUndetectedBrick(cubeColorsStr, replacedCubeColors);
		}
		
		execution.setVariable("cubeColors", replacedCubeColors);
		
		logger.info("cubeColors="+replacedCubeColors);
		log(replacedCubeColors);
	}


	private void log(Collection<String> replacedCubeColors) {
		StringBuffer buf = new StringBuffer();
		for(String faceState : replacedCubeColors) {
			buf.append("\"").append(faceState).append("\", ");
		}
		buf.delete(buf.length()-2, buf.length());
		logger.info(buf.toString());
	}


	public Collection<String> replaceCenterBrick(Collection<String> cubeColors) {
		Collection<String> replacedCubeColors = new ArrayList<String>();
		for(String faceColors: cubeColors) {
			if("X".equals(String.valueOf(faceColors.charAt(6)))) {
				String preStr = faceColors.substring(0, 6); 
				String postStr = faceColors.substring(7, faceColors.length());
				faceColors = preStr+"W"+postStr;
			}
			replacedCubeColors.add(faceColors);
		}
		return replacedCubeColors;
	}


	public int countUndetected(String cubeColorsStr) {
		// The prefix of the faceColors need to be ignored. For example, R: and B: (Right and Back)
		// are also used as colors Red and Blue.
		cubeColorsStr = cubeColorsStr.replaceAll("[WYOGRB]:", "");
		int xCount = StringUtils.countMatches(cubeColorsStr, "X");
		return xCount;
	}

	
	public Collection<String> replaceOneUndetectedBrick(String cubeColorStr, Collection<String> cubeColors) {
		Collection<String> replacedCubeColors = new ArrayList<String>();
		String[] colors = { "W", "Y", "O", "R", "B", "G" };
		String replaceColor = "X";
		for(String color : colors) {
			int colorCount = StringUtils.countMatches(cubeColorStr, color); 
			if(colorCount == 8) {
				replaceColor = color;
			}
		}
		
		for(String faceColors : cubeColors) {
			String replacedFaceColors = faceColors.replace("X", replaceColor);
			replacedCubeColors.add(replacedFaceColors);
		}
		return replacedCubeColors;
	}

}
