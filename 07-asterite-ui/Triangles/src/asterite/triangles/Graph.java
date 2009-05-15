package asterite.triangles;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Graph<T> {
	
	private Map<T, Map<T, Double>> nodes;
	private Map<Double, Map<T, Set<T>>> lines;
	
	public Graph() {
		nodes = new HashMap<T, Map<T,Double>>();
		lines = new HashMap<Double, Map<T, Set<T>>>();
	}
	
	public Set<T> getNodes() {
		return nodes.keySet();
	}

	public void addLine(T from, T to, double angle) {
		internalAdd(from, to, angle);
		internalAdd(to, from, angle);
		
		connectLinesRecursively(from, to, angle);
		connectLinesRecursively(to, from, angle);
	}

	public double getLine(T from, T to) {
		Map<T, Double> map = nodes.get(from);
		if (map == null)
			return -1;
		
		Double value = map.get(to);
		if (value == null)
			return -1;
		
		return value;
	}
	
	private void internalAdd(T from, T to, double angle) {
		addInNodes(from, to, angle);
		addInLines(from, to, angle);
	}

	private void addInNodes(T from, T to, double angle) {
		Map<T, Double> map = nodes.get(from);
		if (map == null) {
			map = new HashMap<T, Double>();
			nodes.put(from, map);
		}
		map.put(to, angle);
	}
	
	private void addInLines(T from, T to, double angle) {
		Map<T, Set<T>> map = lines.get(angle);
		if (map == null) {
			map = new HashMap<T, Set<T>>();
			lines.put(angle, map);
		}
		
		Set<T> list = map.get(from);
		if (list == null) {
			list = new HashSet<T>();
			map.put(from, list);
		}
		
		list.add(to);
	}
	
	private void connectLinesRecursively(T from, T to, double angle) {
		Map<T, Set<T>> map = lines.get(angle);
		
		Set<T> others = map.get(from);
		for(T other : others) {
			if (!other.equals(to)) {
				addInLines(other, to, angle);
				addInLines(to, other, angle);
			}
		}
	}

	public double getLineBetween(T from, T to) {
		for(Map.Entry<Double, Map<T, Set<T>>> entry : lines.entrySet()) {
			Map<T, Set<T>> map = entry.getValue();
			
			Set<T> list;
			
			list = map.get(from);
			if (list != null && list.contains(to))
				return entry.getKey();
			
			list = map.get(to);
			if (list != null && list.contains(from))
				return entry.getKey();
		}
		return -1;
	}

}
