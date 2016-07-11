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
package com.videaps.cube.solving.rubik;


/**
 * This enumeration reflects the rubiks cube colors, that are returned by the Hitechnic color sensor. 
 * Color indexes are according the specification.
 * 
 * @see http://www.lejos.org/nxt/pc/api/lejos/nxt/addon/ColorHTSensor.html
 */
public enum CubeColor {
	
	RED(0, "red"),
	GREEN(1, "green"),
	BLUE(2, "blue"),
	YELLOW(3, "yellow"),
	ORANGE(5, "orange"),
	WHITE(6, "white"),
	NONE(-1, "none");
	
	private int index = -1;
	private String name = "none";
	
	private CubeColor(int index, String name) {
		this.index = index;
		this.name = name;
	}

	
	public static CubeColor getColorName(int index) {
		switch(index) {
		case 0: 
			return RED;
		case 1:
			return GREEN;
		case 2:
			return BLUE;
		case 3:
			return YELLOW;
		// Color 4 not used yet.
		case 5:
			return ORANGE;
		case 6:
			return WHITE;
		}
		return NONE;
	}


	public int getIndex() {
		return index;
	}

	public String getName() {
		return name;
	}

}
