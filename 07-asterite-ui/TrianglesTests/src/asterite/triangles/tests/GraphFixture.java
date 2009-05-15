package asterite.triangles.tests;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import asterite.triangles.Graph;

@SuppressWarnings("unchecked")
public class GraphFixture {
	
	Graph graph;
	
	@Before
	public void setUp() {
		graph = new Graph();
	}
	
	@Test
	public void shouldNotHaveLines() {
		assertEquals(-1.0, graph.getLine("A", "B"));
	}
	
	@Test
	public void shouldNotHaveConnectionByDefault() {
		assertEquals(-1.0, graph.getLineBetween("A", "B"));
	}
	
	@Test
	public void shouldAddEdge() {
		graph.addLine("A", "B", 0);
		assertEquals(0.0, graph.getLine("A", "B"));
		assertEquals(0.0, graph.getLine("B", "A"));
	}
	
	@Test
	public void shouldAddEdgeAndHaveLine() {
		graph.addLine("A", "B", 0);
		assertEquals(0.0, graph.getLineBetween("A", "B"));
		assertEquals(0.0, graph.getLineBetween("B", "A"));
	}
	
	@Test
	public void shouldAddEdgesAndHaveLine() {
		graph.addLine("A", "B", 0);
		graph.addLine("B", "C", 0);
		assertEquals(0.0, graph.getLineBetween("A", "B"));
		assertEquals(0.0, graph.getLineBetween("A", "C"));
		assertEquals(0.0, graph.getLineBetween("B", "A"));
		assertEquals(0.0, graph.getLineBetween("B", "C"));
		assertEquals(0.0, graph.getLineBetween("C", "A"));
		assertEquals(0.0, graph.getLineBetween("C", "B"));
	}
	
	@Test
	public void shouldAddEdgesAndNotHaveLine() {
		graph.addLine("A", "B", 0);
		graph.addLine("B", "C", 30);
		assertEquals(-1.0, graph.getLineBetween("A", "C"));
		assertEquals(-1.0, graph.getLineBetween("C", "A"));
	}
	
	@Test
	public void shouldAddEdgesAndHaveLine2() {
		graph.addLine("A", "B", 0);
		graph.addLine("B", "C", 0);
		graph.addLine("C", "D", 0);
		assertEquals(0.0, graph.getLineBetween("A", "B"));
		assertEquals(0.0, graph.getLineBetween("A", "C"));
		assertEquals(0.0, graph.getLineBetween("A", "D"));
		assertEquals(0.0, graph.getLineBetween("B", "A"));
		assertEquals(0.0, graph.getLineBetween("B", "C"));
		assertEquals(0.0, graph.getLineBetween("B", "D"));
		assertEquals(0.0, graph.getLineBetween("C", "A"));
		assertEquals(0.0, graph.getLineBetween("C", "B"));
		assertEquals(0.0, graph.getLineBetween("C", "D"));
		assertEquals(0.0, graph.getLineBetween("D", "A"));
		assertEquals(0.0, graph.getLineBetween("D", "B"));
		assertEquals(0.0, graph.getLineBetween("D", "C"));
	}
	
	@Test
	public void shouldAddEdgeTestNodes() {
		graph.addLine("A", "B", 0);
		Set<String> nodes = graph.getNodes();
		assertEquals(2, nodes.size());
		assertTrue(nodes.contains("A"));
		assertTrue(nodes.contains("B"));
	}
	
	@Test
	public void shouldAddEdgeTestNodes2() {
		graph.addLine("A", "B", 0);
		graph.addLine("B", "C", 0);
		Set<String> nodes = graph.getNodes();
		assertEquals(3, nodes.size());
		assertTrue(nodes.contains("A"));
		assertTrue(nodes.contains("B"));
		assertTrue(nodes.contains("C"));
	}

}
