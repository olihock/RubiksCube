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

import lejos.nxt.Motor;
import lejos.nxt.remote.RemoteMotor;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.Expression;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 *
 */
public class RotateMotorDelegate implements JavaDelegate {
	private static final Logger logger = LoggerFactory.getLogger(RotateMotorDelegate.class);

	private Expression motorPort;
	private Expression speed;
	private Expression acceleration;
	private Expression angle;
	private Expression immediateReturn;
	
	
	public void execute(DelegateExecution execution) throws Exception {
		String motorPortValue = (String) motorPort.getValue(execution);
		RemoteMotor motor = getMotorPortByName(motorPortValue);

		Number speedValue = motor.getSpeed();
		if(speed != null) {
			speedValue = (Number) speed.getValue(execution);
			motor.setSpeed(speedValue.intValue());
		}
		
		Number accelerationValue = 0L;
		if(acceleration != null) {
			accelerationValue = (Number) acceleration.getValue(execution);
			motor.setAcceleration(accelerationValue.intValue());
		}
		
		Number angleValue = 0L;
		if(angle != null) {
			angleValue = (Number) angle.getValue(execution);
		} 
		
		Boolean immediateReturnValue = true;
		if(immediateReturn != null) {
			immediateReturnValue = (Boolean) immediateReturn.getValue(execution);
		}
		
		logInfo(motor.getTachoCount(), motorPortValue, speedValue, accelerationValue, angleValue, immediateReturnValue);

		motor.rotate(angleValue.intValue(), immediateReturnValue);
		
		int tachoCount = motor.getTachoCount();
		execution.setVariable("tachoCount", tachoCount);
		
		logger.info("tachoCount="+tachoCount);
	}


	private RemoteMotor getMotorPortByName(String motorPort) {
		if("A".equalsIgnoreCase(motorPort)) {
			return Motor.A;
		} else if("B".equalsIgnoreCase(motorPort)) {
			return Motor.B;
		} else if("C".equalsIgnoreCase(motorPort)) {
			return Motor.C;
		}
		return null;
	}

	
	private void logInfo(int tachoCount, String motorPortValue, Number speedValue, Number accelerationValue, Number angleValue, Boolean immediateReturnValue) {
		logger.info("tachoCount="+tachoCount);
		logger.info("motorPortValue="+motorPortValue);
		logger.info("speed="+speedValue);
		logger.info("accelerationValue="+accelerationValue);
		logger.info("angleValue="+angleValue);
		logger.info("immediateReturnValue="+immediateReturnValue);
	}


	public void setMotorPort(Expression motorPort) {
		this.motorPort = motorPort;
	}


	public void setSpeed(Expression speed) {
		this.speed = speed;
	}


	public void setAcceleration(Expression acceleration) {
		this.acceleration = acceleration;
	}


	public void setAngle(Expression angle) {
		this.angle = angle;
	}


	public void setImmediateReturn(Expression immediateReturn) {
		this.immediateReturn = immediateReturn;
	}

}
