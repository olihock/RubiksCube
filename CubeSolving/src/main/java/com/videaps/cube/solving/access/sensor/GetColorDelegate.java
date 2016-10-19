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

import lejos.nxt.I2CPort;
import lejos.nxt.addon.ColorHTSensor;
import lejos.robotics.Color;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.videaps.cube.solving.access.ColorPicker;
import com.videaps.cube.solving.toggling.Features;


/**
 * 
 */
public class GetColorDelegate implements JavaDelegate {
	private static final Logger logger = LoggerFactory.getLogger(GetColorDelegate.class);

	
	public void execute(DelegateExecution execution) throws Exception {
		String sensorPort = (String) execution.getVariable("getColorSensorPort");
		
		int colorId = -1;
		if(Features.USE_LEJOS.isActive()) {
			I2CPort port = new SensorFactory().getSensor(sensorPort);
			ColorHTSensor sensor = new ColorHTSensor(port);
			
			Color color = sensor.getColor();
			colorId = color.getColor();

			// The ColorHTSensor mixes up Red and Orange and always returns Red.
			// Therefore, the red color is treated separately in such, that
			// the RGB values are fetched and evaluated if red or orange.
			if(colorId == Color.RED || colorId == Color.ORANGE) {
				colorId = distinguishRedAndOrange(colorId, color);
			}
		}
		String theColor = new ColorPicker().pickColor(colorId);

		execution.setVariable("getColorColor", theColor);
	}


	/*
	 * This method evaluates if the RGB components of the given color
	 * has a certain red value and green value range. Based on the result,
	 * the decision is made if the color is red or orange.
	 */
	private int distinguishRedAndOrange(int colorId, Color color) {
		int red = color.getRed();
		int green = color.getGreen();
		int blue = color.getBlue();
		
		if(logger.isDebugEnabled()) {
			logger.debug("rgb="+red+","+green+","+blue);
		}
		
		if(red <= 36 && green <= 7) {
			colorId = Color.RED;
		} else if(red >= 37 && green >= 8) {
			colorId = Color.ORANGE;
		}
		return colorId;
	}

	
}
