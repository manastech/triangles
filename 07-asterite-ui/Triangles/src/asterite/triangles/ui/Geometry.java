package asterite.triangles.ui;

import java.awt.Point;

public class Geometry {
	
	public static double angle(Point p1, Point p2) {
		int dy = p2.y - p1.y;
		int dx = p1.x - p2.x;
		double result = 180 * Math.atan2(dy, dx) / Math.PI;
		if (result >= 180)
			result -= 180;
		if (result < 0)
			result += 180;
		if (result > 90)
			result -= 180;
		return Math.round(result / 10) * 10;
	}

}
