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

import static lejos.robotics.Color.BLUE;
import static lejos.robotics.Color.GREEN;
import static lejos.robotics.Color.ORANGE;
import static lejos.robotics.Color.RED;
import static lejos.robotics.Color.WHITE;
import static lejos.robotics.Color.YELLOW;
import static lejos.robotics.Color.MAGENTA; // This is used for blue.

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 *
 */
public class ColorPicker {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(ColorPicker.class);

	public static final int NO_OF_BRICKS_PER_FACE = 9;

	private static final Map<Integer, String> colorMap = new HashMap<Integer, String>();
	
	
	public ColorPicker() {
		colorMap.put(Integer.valueOf(WHITE), "W");
		colorMap.put(Integer.valueOf(RED), "R");
		colorMap.put(Integer.valueOf(YELLOW), "Y");
		colorMap.put(Integer.valueOf(ORANGE), "O");
		colorMap.put(Integer.valueOf(GREEN), "G");
		// HT Color sensor recognizes Blue brick as Magenta, 
		// so blue and magenta is mapped to "B".
		colorMap.put(Integer.valueOf(BLUE), "B");
		colorMap.put(Integer.valueOf(MAGENTA), "B");
	}

	
	public boolean contains(String theColor) {
		boolean b = colorMap.containsValue(theColor);
		return b;
	}
	
	
	public String pickColor(int colorId) {
		String theColor = colorMap.get(Integer.valueOf(colorId));
		if(theColor == null) {
			theColor = "X";
		}
		return theColor;
	}


	public String mostFrequentColor(String colorStr) {
		String mostFrequentColor = null;
		int maxColorCount = 0;
		for(String color : colorMap.values()) {
			int count = StringUtils.countMatches(colorStr, color); 
			if(count > maxColorCount) {
				mostFrequentColor = color;
				maxColorCount = count;
			}
		}
		if(mostFrequentColor == null) {
			mostFrequentColor = "X";
		}
		return mostFrequentColor;
	}

}
