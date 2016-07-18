package com.videaps.cube.solving.rubik;


public enum Rotation {

	RIGHT("", -90),
	LEFT("'", 90),
	HALF("2", 180);
	
	private String notation;
	private int angle;
	
	private Rotation(String notation, int angle) {
		this.notation = notation;
		this.angle = angle;
	}
	
	public String getNotation() {
		return notation;
	}
	
	public int getAngle() {
		return angle;
	}
	
	public static Rotation getRotationByNotation(String notation) {
		if("".equals(notation)) {
			return RIGHT;
		} else if("'".equals(notation)) {
			return LEFT;
		} else if("2".equals(notation)) {
			return HALF;
		} else {
			throw new NotationError("Unknown notation: "+notation);
		}
	}

	public static int getAngleByNotation(String notation) {
		if("".equals(notation)) {
			return RIGHT.getAngle();
		} else if("'".equals(notation)) {
			return LEFT.getAngle();
		} else if("2".equals(notation)) {
			return HALF.getAngle();
		} else {
			throw new NotationError("Unknown notation: "+notation);
		}
	}

}
