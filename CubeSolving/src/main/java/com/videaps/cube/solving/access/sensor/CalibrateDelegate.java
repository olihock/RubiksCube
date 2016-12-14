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

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.videaps.cube.solving.toggling.Features;


public class CalibrateDelegate implements JavaDelegate {
	private static final Logger logger = LoggerFactory.getLogger(CalibrateDelegate.class);

	public static final String SENSOR_PORT_KEY = "sensorPort"; 
	public static final String RETURN_CODE_KEY = "returnCode"; 
	
	public static final int RETURN_CODE_OK = 0;
	public static final int RETURN_CODE_ERROR = -1;
	public static final int RETURN_CODE_NEUTRAL = 1;
	
	private int returnCode = RETURN_CODE_NEUTRAL;
	
	
	public void execute(DelegateExecution execution) throws Exception {
		String sensorPort = (String) execution.getVariable(SENSOR_PORT_KEY);
		logger.trace(SENSOR_PORT_KEY+"="+sensorPort);
		
		if(Features.USE_LEJOS.isActive()) {
			I2CPort i2cPort = new SensorFactory().getSensor(sensorPort);
			ColorHTSensor sensor = new ColorHTSensor(i2cPort);
			
			returnCode = sensor.initWhiteBalance();
			execution.setVariable(RETURN_CODE_KEY, returnCode);
		}
		
		logger.trace(RETURN_CODE_KEY+"="+returnCode);
	}

}
