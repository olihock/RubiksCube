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

import lejos.nxt.remote.RemoteMotor;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.videaps.cube.solving.toggling.Features;


/**
 *
 */
public class RotateMotorDelegate implements JavaDelegate {
	private static final Logger logger = LoggerFactory.getLogger(RotateMotorDelegate.class);

	
	public void execute(DelegateExecution execution) throws Exception {
		String motorPort = (String) execution.getVariable("rotateMotorMotorPort");
		Number speed = (Number) execution.getVariable("rotateMotorSpeed");
		Number acceleration = (Number) execution.getVariable("rotateMotorAcceleration");
		Number angle = (Number) execution.getVariable("rotateMotorAngle");
		Boolean immediateReturn = (Boolean) execution.getVariable("rotateMotorImmediateReturn");
		
		logInfo(motorPort, speed, acceleration, angle, immediateReturn);

		int tachoCount = -1;
		if(Features.USE_LEJOS.isActive()) {
			RemoteMotor motor = new MotorFactory().getMotor(motorPort);
			motor.setSpeed(speed!=null?speed.intValue():999);
			motor.setAcceleration(acceleration!=null?acceleration.intValue():0);
			motor.rotate(angle!=null?angle.intValue():0, immediateReturn!=null?immediateReturn:false);

			tachoCount = motor.getTachoCount();
		}
		
		execution.setVariable("rotateMotorTachoCount", tachoCount);
		logger.info("tachoCount="+tachoCount);
	}


	private void logInfo(String motorPort, Number speed, Number acceleration, Number angle, Boolean immediateReturn) {
		logger.info("motorPort="+motorPort);
		logger.info("speed="+speed);
		logger.info("acceleration="+acceleration);
		logger.info("angle="+angle);
		logger.info("immediateReturn="+immediateReturn);
	}

}
