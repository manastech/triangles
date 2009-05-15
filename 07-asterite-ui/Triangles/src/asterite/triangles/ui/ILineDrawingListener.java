package asterite.triangles.ui;

import java.awt.Point;

public interface ILineDrawingListener {
	
	void aboutToStartALine(Point fromPoint);
	
	void aboutToDrawALine(Point fromPoint, Point toPoint);
	
	void lineDrawn(Point fromPoint, Point toPoint);

}
