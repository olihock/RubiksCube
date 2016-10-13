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
package com.videaps.cube.solving.moves.basic;

import lejos.nxt.remote.RemoteMotor;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.impl.el.FixedValue;
import org.camunda.bpm.engine.impl.el.JuelExpression;

import com.videaps.cube.solving.access.motor.MotorFactory;
import com.videaps.cube.solving.rubik.Direction;
import com.videaps.cube.solving.toggling.Features;


/**
 * @since 1.1
 */
public class InvertDelegate implements JavaDelegate {

	private FixedValue port;
	private JuelExpression angle;
	
	private static final int QUARTER = 90;
	private static final int VARIANCE = 5;

	
	public void execute(DelegateExecution execution) throws Exception {
		String myPort = (String) port.getValue(execution);
		Long myAngle = (Long) angle.getValue(execution); 

		long invertedAngle = 0;
		if(Features.USE_LEJOS.isActive()) {
			RemoteMotor motor = new MotorFactory().getMotor(myPort);
			invertedAngle = invert(myAngle, myAngle/Math.abs(myAngle), Math.abs(myAngle)/QUARTER, motor.getTachoCount());
		}
		execution.setVariable("angle", -invertedAngle);
	}

	
	private long invert(long originalDegrees, long direction, long count, int tachoCount) {
		long degrees = originalDegrees;
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
