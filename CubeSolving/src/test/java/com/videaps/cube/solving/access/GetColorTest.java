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

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import lejos.nxt.I2CPort;
import lejos.nxt.addon.ColorHTSensor;

import org.junit.Before;
import org.junit.Test;

import com.videaps.cube.solving.access.sensor.SensorFactory;


/**
 *
 */
public class GetColorTest {

	@Before
	public void setUp() {
	}

	
	@Test
	public void calibrateWhite() {
		I2CPort port = new SensorFactory().getSensor("S1");
		ColorHTSensor sensor = new ColorHTSensor(port);
		int rc = sensor.initWhiteBalance();
		assertEquals(0, rc);
		
		int colorId = sensor.getColorID();
		System.out.println("colorId="+colorId);
		assertEquals(6, colorId);
	}
	
	
	@Test
	public void test() {
		List<Integer> colors = new ArrayList<Integer>();
		I2CPort port = new SensorFactory().getSensor("S1");
		ColorHTSensor sensor = new ColorHTSensor(port);
		long start = System.currentTimeMillis();
		while( (start + 10000) > System.currentTimeMillis() ) {
			int color = sensor.getColor().getColor();
			System.out.println("color="+color);
			colors.add(color);
		}
		assertTrue(colors.size() > 0);
		System.out.println("colors.size="+colors.size());
	}

}
