package asterite.triangles;

import java.util.ArrayList;
import java.util.List;

public class TrianglesCounter {

	public <T> int count(Graph<T> graph) {
		int count = 0;
		
		List<T> nodes = new ArrayList<T>(graph.getNodes());
		for (int i = 0; i < nodes.size(); i++) {
			for (int j = i + 1; j < nodes.size(); j++) {
				for (int k = j + 1; k < nodes.size(); k++) {
					T one = nodes.get(i);
					T two = nodes.get(j);
					T three = nodes.get(k);
					
					double line12 = graph.getLineBetween(one, two);
					double line23 = graph.getLineBetween(two, three);
					double line13 = graph.getLineBetween(one, three);
					
					if (line12 != -1 && line23 != -1 && line13 != -1 &&
						line12 != line23 && line23 != line13 && line13 != line12) {
						count++;
					}
				}
			}
		}
		
		return count;
	}
	
	

}
