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
package com.videaps.cube.solving.moving;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;


/**
 *
 */
public class PrepareNotationDelegate implements JavaDelegate {

	public void execute(DelegateExecution execution) throws Exception {
		String notation = (String) execution.getVariable("notation");
		Pair<String, String> pair = split(notation);
		
		String face = pair.getLeft();
		execution.setVariable("face", face);
		
		String direction = pair.getRight();
		execution.setVariable("direction", direction);
	}

	
	private Pair<String, String> split(String notation) {
		Pair<String, String> pair = null;
		
		if(notation.length() == 1) {
			pair = new ImmutablePair<String, String>(notation, "");
		} else if(notation.length() == 2) {
			pair = new ImmutablePair<String, String>(notation.substring(0, 1), notation.substring(1));
		}
		return pair;
	}

}
