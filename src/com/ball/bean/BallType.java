package com.ball.bean;


public class BallType {
	public static final int FootBall = 1;
	public static final int BasketBall = 2;
	public static final int PingPong = 3;
	public static final int Billiards = 4;
	public static final int Badminton = 5;
	public static final int Tennis = 6;
	public static final int VolleyBall = 7;

	
	public static String BallTypeString(int ballType){
		switch (ballType) {
		case FootBall:
			return "足球";
		case BasketBall:
			return "篮球";
		case VolleyBall:
			return "排球";
		case PingPong:
			return "乒乓球";
		case Billiards:
			return "̨台球";
		case Badminton:
			return "羽毛球";
		case Tennis:
			return "网球";
		default:
			break;
		}
		return null;
	}
	
}
