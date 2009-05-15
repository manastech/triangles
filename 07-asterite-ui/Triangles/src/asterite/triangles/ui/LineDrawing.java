package asterite.triangles.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;

@SuppressWarnings("serial")
public class LineDrawing extends JComponent implements MouseListener, MouseMotionListener {
	
	public final static int Snap = 10;
	
	private enum State {
		AboutToDrawLine,
		DrawingLine,
	}
	
	private class Line {
		Point p1;
		Point p2;
		public Line(Point p1, Point p2) {
			this.p1 = p1;
			this.p2 = p2;
		}
	}
	
	private boolean mouseIsInside;
	private Point currentPoint;
	private Point previousPoint;
	private State state = State.AboutToDrawLine;
	private List<Line> lines = new ArrayList<Line>();
	private ILineDrawingListener listener;
	
	public LineDrawing() {
		setPreferredSize(new Dimension(640, 480));
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	
	public void setLineDrawingListener(ILineDrawingListener listener) {
		this.listener = listener;
	}
	
	@Override
	public void paint(Graphics g) {
		Dimension d = getSize();
		
		// Clear everything
		g.setColor(Color.WHITE);
		g.clearRect(0, 0, d.width, d.height);
		
		if (mouseIsInside) {
			switch(state) {
			case AboutToDrawLine:
				drawBigMarker(g, currentPoint);
				break;
			case DrawingLine:
				drawBigMarker(g, previousPoint);
				drawBigMarker(g, currentPoint);
				drawLine(g, previousPoint, currentPoint);
				break;
			}
		}
		
		// Draw the lines already painted
		for(Line line : lines) {
			drawLine(g, line.p1, line.p2);
		}
		
		// Draw the markers for the lines already painted
		for(Line line : lines) {
			drawSmallMarker(g, line.p1);
			drawSmallMarker(g, line.p2);
		}
		
		// Draw guide-points
		g.setColor(Color.GRAY);
		for (int x = 0; x < d.width; x += Snap) {
			for (int y = 0; y < d.height; y += Snap) {
				g.drawLine(x, y, x, y);
			}
		}
	}
	
	private void drawBigMarker(Graphics g, Point p) {
		g.setColor(Color.RED);
		g.fillOval(p.x - 3, p.y - 3, 6, 6);
		g.setColor(Color.BLACK);
		g.drawOval(p.x - 3, p.y - 3, 6, 6);
	}
	
	private void drawSmallMarker(Graphics g, Point p) {
		g.setColor(Color.GREEN);
		g.fillOval(p.x - 2, p.y - 2, 4, 4);
		g.setColor(Color.BLACK);
		g.drawOval(p.x - 2, p.y - 2, 4, 4);
	}
	
	private void drawLine(Graphics g, Point p1, Point p2) {
		g.setColor(Color.BLUE);
		g.drawLine(p1.x, p1.y, p2.x, p2.y);
	}
	
	private Point roundToGuideLines(Point p) {
		int x = p.x;
		int y = p.y;
		int mx = x % Snap;
		int my = y % Snap;
		if (mx >= Snap / 2) {
			x = x - mx + Snap;
		} else {
			x -= mx;
		}
		if (my >= Snap / 2) {
			y = y - my + Snap;
		} else {
			y -= my;
		}
		return new Point(x, y);
	}

	public void mouseClicked(MouseEvent mouseevent) {
		if (mouseevent.getButton() == MouseEvent.BUTTON1) {
			switch(state) {
			case AboutToDrawLine:
				previousPoint = currentPoint;
				state = State.DrawingLine;
				break;
			case DrawingLine:
				lines.add(new Line(previousPoint, currentPoint));
				state = State.AboutToDrawLine;
				
				if (listener != null)
					listener.lineDrawn(previousPoint, currentPoint);
				break;
			}
		} else {
			if (state == State.DrawingLine)
				state = State.AboutToDrawLine;
		}
		repaint();
	}

	public void mouseEntered(MouseEvent mouseevent) {
		this.mouseIsInside = true;
	}

	public void mouseExited(MouseEvent mouseevent) {
		this.mouseIsInside = false;
	}

	public void mousePressed(MouseEvent mouseevent) {
		
	}

	public void mouseReleased(MouseEvent mouseevent) {
		
	}

	public void mouseDragged(MouseEvent mouseevent) {
		
	}

	public void mouseMoved(MouseEvent mouseevent) {
		Point newLocation = roundToGuideLines(mouseevent.getPoint());
		if (!newLocation.equals(currentPoint)) {
			currentPoint = newLocation;
			repaint();
		}
		
		if (listener != null) {
			switch(state) {
			case AboutToDrawLine:
				listener.aboutToStartALine(currentPoint);
				break;
			case DrawingLine:
				listener.aboutToDrawALine(previousPoint, currentPoint);
				break;
			}
			
		}
	}

}
