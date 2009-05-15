package asterite.triangles.ui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;

import javax.swing.JLabel;
import javax.swing.JPanel;

import asterite.triangles.Graph;
import asterite.triangles.TrianglesCounter;

@SuppressWarnings("serial")
public class LineDrawingInfo extends JPanel implements ILineDrawingListener {
	
	private JLabel labelX, labelXcontent;
	private JLabel labelY, labelYcontent;
	private JLabel labelDX, labelDXcontent;
	private JLabel labelDY, labelDYcontent;
	private JLabel labelAngle, labelAngleContent;
	private JLabel labelTriangles, labelTrianglesContent;
	
	private Graph<Point> graph = new Graph<Point>();
	private TrianglesCounter counter = new TrianglesCounter();
	
	public LineDrawingInfo() {
		this.labelX = new JLabel("X: ");
		this.labelY = new JLabel("Y: ");
		this.labelDX = new JLabel("DX: ");
		this.labelDY = new JLabel("DY: ");
		this.labelAngle = new JLabel("Angle: ");
		this.labelTriangles = new JLabel("Triangles: ");
		this.labelXcontent = new JLabel();
		this.labelYcontent = new JLabel();
		this.labelDXcontent = new JLabel();
		this.labelDYcontent = new JLabel();
		this.labelAngleContent = new JLabel();
		this.labelTrianglesContent = new JLabel("0");
		
		this.setLayout(new GridLayout(6, 2));
		this.add(labelX);
		this.add(labelXcontent);
		this.add(labelY);
		this.add(labelYcontent);
		this.add(labelDX);
		this.add(labelDXcontent);
		this.add(labelDY);
		this.add(labelDYcontent);
		this.add(labelAngle);
		this.add(labelAngleContent);
		this.add(labelTriangles);
		this.add(labelTrianglesContent);
		
		setPreferredSize(new Dimension(140, 140));
	}

	public void aboutToDrawALine(Point fromPoint, Point toPoint) {
		labelXcontent.setText(String.valueOf(toPoint.x / LineDrawing.Snap));
		labelYcontent.setText(String.valueOf(toPoint.y / LineDrawing.Snap));
		labelDXcontent.setText(String.valueOf((toPoint.x - fromPoint.x) / LineDrawing.Snap));
		labelDYcontent.setText(String.valueOf((fromPoint.y - toPoint.y) / LineDrawing.Snap));
		labelAngleContent.setText(
				String.valueOf(
						Geometry.angle(fromPoint, toPoint) * 100 / 100));
	}

	public void aboutToStartALine(Point fromPoint) {
		labelXcontent.setText(String.valueOf(fromPoint.x / LineDrawing.Snap));
		labelYcontent.setText(String.valueOf(fromPoint.y / LineDrawing.Snap));
		labelDXcontent.setText("-");
		labelDYcontent.setText("-");
		labelAngleContent.setText("-");
	}

	public void lineDrawn(Point fromPoint, Point toPoint) {
		graph.addLine(fromPoint, toPoint, Geometry.angle(fromPoint, toPoint));
		labelTrianglesContent.setText(String.valueOf(counter.count(graph)));
	}

}
