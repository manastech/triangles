package asterite.triangles;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Graph {
	
	private Map<String, Map<String, Integer>> nodes;
	private Map<Integer, Map<String, Set<String>>> lines;
	
	public Graph() {
		nodes = new HashMap<String, Map<String,Integer>>();
		lines = new HashMap<Integer, Map<String, Set<String>>>();
	}
	
	public Set<String> getNodes() {
		return nodes.keySet();
	}

	public void addLine(String from, String to, int angle) {
		internalAdd(from, to, angle);
		internalAdd(to, from, angle);
		
		connectLinesRecursively(from, to, angle);
		connectLinesRecursively(to, from, angle);
	}

	public int getLine(String from, String to) {
		Map<String, Integer> map = nodes.get(from);
		if (map == null)
			return -1;
		
		Integer value = map.get(to);
		if (value == null)
			return -1;
		
		return value;
	}
	
	private void internalAdd(String from, String to, int angle) {
		addInNodes(from, to, angle);
		addInLines(from, to, angle);
	}

	private void addInNodes(String from, String to, int angle) {
		Map<String, Integer> map = nodes.get(from);
		if (map == null) {
			map = new HashMap<String, Integer>();
			nodes.put(from, map);
		}
		map.put(to, angle);
	}
	
	private void addInLines(String from, String to, int angle) {
		Map<String, Set<String>> map = lines.get(angle);
		if (map == null) {
			map = new HashMap<String, Set<String>>();
			lines.put(angle, map);
		}
		
		Set<String> list = map.get(from);
		if (list == null) {
			list = new HashSet<String>();
			map.put(from, list);
		}
		
		list.add(to);
	}
	
	private void connectLinesRecursively(String from, String to, int angle) {
		Map<String, Set<String>> map = lines.get(angle);
		
		Set<String> others = map.get(from);
		for(String other : others) {
			if (!other.equals(to)) {
				addInLines(other, to, angle);
				addInLines(to, other, angle);
			}
		}
	}

	public int getLineBetween(String from, String to) {
		for(Map.Entry<Integer, Map<String, Set<String>>> entry : lines.entrySet()) {
			Map<String, Set<String>> map = entry.getValue();
			
			Set<String> list;
			
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
