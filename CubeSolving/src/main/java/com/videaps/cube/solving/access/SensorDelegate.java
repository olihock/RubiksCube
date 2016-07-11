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

import lejos.nxt.I2CPort;
import lejos.nxt.SensorPort;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.Expression;
import org.camunda.bpm.engine.delegate.JavaDelegate;


/**
 *
 */
public class SensorDelegate implements JavaDelegate {
	
	protected Expression sensorPort;
	
	
	public void execute(DelegateExecution execution) throws Exception {
	}

	
	protected I2CPort getSensorPortByName(String sensorPort) {
		if("S1".equalsIgnoreCase(sensorPort)) {
			return SensorPort.S1;
		} else if("S2".equalsIgnoreCase(sensorPort)) {
			return SensorPort.S2;
		} else if("S3".equalsIgnoreCase(sensorPort)) {
			return SensorPort.S3;
		} else if("S4".equalsIgnoreCase(sensorPort)) {
			return SensorPort.S4;
		}
		return null;
	}
	
	
	public void setSensorPort(Expression sensorPort) {
		this.sensorPort = sensorPort;
	}

}
