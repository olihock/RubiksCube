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
package com.videaps.cube.solving.access.cube;

import lejos.nxt.remote.RemoteMotor;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.videaps.cube.solving.access.motor.MotorFactory;
import com.videaps.cube.solving.rubik.Direction;
import com.videaps.cube.solving.toggling.Features;


/**
 *
 */
public class InvertListener implements ExecutionListener {
	private static final Logger logger = LoggerFactory.getLogger(InvertListener.class);

	private static final int QUARTER = 90;
	private static final int VARIANCE = 5;

	
	public void notify(DelegateExecution execution) throws Exception {
		logger.info(execution.getCurrentActivityName());

		String port = (String) execution.getVariable("turnTablePort");
		Number direction = (Number) execution.getVariable("turnTableDirection");
		Number count = (Number) execution.getVariable("turnTableCount");
		
		int invertedAngle = 0;
		if(Features.USE_LEJOS.isActive()) {
			RemoteMotor motor = new MotorFactory().getMotor(port);
			int angle = 90 * count.intValue() * direction.intValue();
			invertedAngle = invert(angle, direction.intValue(), count.intValue(), motor.getTachoCount());
		}
		execution.setVariable("rotateMotorAngle", invertedAngle);
		logger.info("invertedAngle="+invertedAngle);
	}

	
	private int invert(int originalDegrees, int direction, int count, int tachoCount) {
		int degrees = originalDegrees;
		if(Direction.LEFT.getValue() == direction && inInterval(0, tachoCount)) {
			degrees = QUARTER * 3 * direction * (-1);
		} else if(Direction.RIGHT.getValue() == direction && inInterval(-270, tachoCount)) {
			degrees = QUARTER * 3 * direction * (-1);
		} else if(count == 2) {
			if(inInterval(0, tachoCount) || inInterval(-90, tachoCount)) {
				degrees = QUARTER * 2 * direction;
			} else {
				degrees = QUARTER * 2 * direction * (-1);
			}
		} else if(count == 3) {
			if(Direction.RIGHT.getValue() == direction && tachoCount < -45) {
				degrees = QUARTER * direction * (-1);
			} else if(Direction.LEFT.getValue() == direction && tachoCount > -225) {
				degrees = QUARTER * direction * (-1);
			}
		}
		return degrees;
	}
	
	
	private boolean inInterval(int expected, int actual) {
		expected = Math.abs(expected);
		actual = Math.abs(actual);
		if( (expected - VARIANCE) <= actual && actual <= (expected + VARIANCE)) {
			return true;
		}
		return false;
	}


}
