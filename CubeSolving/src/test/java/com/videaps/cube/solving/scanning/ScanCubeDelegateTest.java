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
package com.videaps.cube.solving.scanning;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Before;
import org.junit.Test;

public class ScanCubeDelegateTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
	}

	
	@Test
	public void testColorUndetected() {
		String[] cubeColors = { "U:BGYRGYBWW", "F:YBBOOGOBG", "D:YYWOBRGWX", "L:RGRYWBRWB", "R:OGGOYROBR", "B:OWWYROYRW" };
		
		ScanCubeDelegate scanCube = new ScanCubeDelegate();
		String cubeColorStr = ArrayUtils.toString(cubeColors);
		
		int xCount = scanCube.countUndetected(cubeColorStr);
		assertEquals(1, xCount);
		Collection<String> replacedCubeColors = scanCube.replaceOneUndetectedBrick(cubeColorStr, Arrays.asList(cubeColors));
		
		assertEquals(0, scanCube.countUndetected(ArrayUtils.toString(replacedCubeColors)));
	}
}
