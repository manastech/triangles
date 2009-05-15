package asterite.triangles.tests;

import static junit.framework.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import asterite.triangles.Graph;
import asterite.triangles.TrianglesCounter;

public class TrianglesCounterFixture {
	
	Graph graph;
	TrianglesCounter counter;
	
	@Before
	public void setUp() {
		graph = new Graph();
		counter = new TrianglesCounter();
	}

	@Test
	public void testEmpty() {
		assertEquals(0, counter.count(graph));
	}
	
	@Test
	public void testOneTriangle() {
		graph.addLine("A", "B", 60);
		graph.addLine("B", "C", 120);
		graph.addLine("C", "A", 0);
		assertEquals(1, counter.count(graph));
	}
	
	@Test
	public void testFiveTriangles() {
		graph.addLine("A", "B", 60);
		graph.addLine("B", "D", 60);
		graph.addLine("A", "C", 120);
		graph.addLine("C", "F", 120);
		graph.addLine("B", "C", 0);
		graph.addLine("B", "E", 120);
		graph.addLine("C", "E", 60);
		graph.addLine("D", "E", 0);
		graph.addLine("E", "F", 0);
		assertEquals(5, counter.count(graph));
	}
	
	@Test
	public void testAnotherOne() {
		graph.addLine("A", "B", 60);
		graph.addLine("B", "D", 60);
		graph.addLine("A", "C", 120);
		graph.addLine("C", "E", 120);
		graph.addLine("D", "E", 0);
		graph.addLine("B", "F", 150);
		graph.addLine("F", "E", 150);
		graph.addLine("C", "F", 30);
		graph.addLine("D", "F", 30);
		assertEquals(8, counter.count(graph));
	}
	
	@Test
	public void testProblem() {
		graph.addLine("A", "G", 90);
		graph.addLine("G", "H", 90);
		graph.addLine("H", "F", 90);
		graph.addLine("A", "D", 60);
		graph.addLine("D", "B", 60);
		graph.addLine("A", "E", 120);
		graph.addLine("E", "C", 120);
		graph.addLine("D", "G", 0);
		graph.addLine("G", "E", 0);
		graph.addLine("B", "F", 0);
		graph.addLine("F", "C", 0);
		graph.addLine("B", "I", 30);
		graph.addLine("I", "F", 120);
		graph.addLine("I", "D", 120);
		graph.addLine("I", "H", 30);
		graph.addLine("D", "H", 150);
		graph.addLine("H", "J", 150);
		graph.addLine("J", "C", 150);
		graph.addLine("H", "E", 30);
		graph.addLine("E", "J", 60);
		graph.addLine("J", "F", 60);
		assertEquals(47, counter.count(graph));
	}
	
}
