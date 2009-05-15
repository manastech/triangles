package asterite.triangles.ui;

import java.awt.FlowLayout;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class TrianglesUI extends JFrame {
	
	public TrianglesUI() {
		super("¿Cuántos triángulos hay...?");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		LineDrawing drawing = new LineDrawing();
		LineDrawingInfo info = new LineDrawingInfo();
		
		setLayout(new FlowLayout());
		getContentPane().add(drawing);
		getContentPane().add(info);
		
		drawing.setLineDrawingListener(info);
	}
	
	public static void main(String[] args) {
		TrianglesUI ui = new TrianglesUI();
		ui.pack();
		ui.setVisible(true);
	}

}
