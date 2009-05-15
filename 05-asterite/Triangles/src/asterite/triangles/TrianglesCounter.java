package asterite.triangles;

import java.util.ArrayList;
import java.util.List;

public class TrianglesCounter {

	public int count(Graph graph) {
		int count = 0;
		
		List<String> nodes = new ArrayList<String>(graph.getNodes());
		for (int i = 0; i < nodes.size(); i++) {
			for (int j = i + 1; j < nodes.size(); j++) {
				for (int k = j + 1; k < nodes.size(); k++) {
					String one = nodes.get(i);
					String two = nodes.get(j);
					String three = nodes.get(k);
					
					int line12 = graph.getLineBetween(one, two);
					int line23 = graph.getLineBetween(two, three);
					int line13 = graph.getLineBetween(one, three);
					
					if (line12 != -1 && line23 != -1 && line13 != -1 &&
						line12 != line23 && line23 != line13 && line13 != line12) {
						System.out.println(one + " - " + two + " - " + three);
						count++;
					}
				}
			}
		}
		
		return count;
	}
	
	

}
