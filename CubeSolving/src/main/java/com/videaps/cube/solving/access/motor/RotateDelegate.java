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
package com.videaps.cube.solving.access.motor;

import lejos.nxt.remote.RemoteMotor;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.videaps.cube.solving.toggling.Features;


/**
 * @since 1.1
 */
public class RotateDelegate implements JavaDelegate {
	private static final Logger logger = LoggerFactory.getLogger(RotateDelegate.class);
	
	public void execute(DelegateExecution execution) throws Exception {
		String port = (String) execution.getVariable("port");
		Integer angle = Integer.valueOf(String.valueOf(execution.getVariable("angle")));
		Boolean immediateReturn = Boolean.valueOf((String)execution.getVariable("immediateReturn"));
		Integer speed = readSpeed(execution);

		log(execution, port, angle, immediateReturn, speed);
		
		if(Features.USE_LEJOS.isActive()) {
			RemoteMotor motor = new MotorFactory().getMotor(port);
			motor.setSpeed(speed != null ? speed : (int)motor.getMaxSpeed());
			motor.rotate(angle.intValue(), immediateReturn);
		}
	}


	private Integer readSpeed(DelegateExecution execution) {
		Object speedVar = execution.getVariable("speed");
		Integer speed = speedVar != null ? Integer.valueOf(String.valueOf(speedVar)) : null;
		return speed;
	}

	
	private void log(DelegateExecution execution, String port, Integer angle, Boolean immediateReturn, Integer speed) {
		if(logger.isInfoEnabled()) {
			logger.info(execution.getCurrentActivityName()+" -->");
			logger.info("port="+port);
			logger.info("angle="+angle);
			logger.info("immediateReturn="+immediateReturn);
			logger.info("speed="+speed);
			logger.info("<--");
		}
	}

}
