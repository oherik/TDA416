import java.util.ArrayList;

public class testGraph<E extends Edge>{

	// Grafen som finns på
	// http://www.geeksforgeeks.org/greedy-algorithms-set-2-kruskals-minimum-spanning-tree-mst/
	public static void main(String[] args) {
		ArrayList<Edge> edgeList = new ArrayList<Edge>();
		edgeList.add(new BusEdge(0, 1, 4, ""));
		edgeList.add(new BusEdge(1, 2, 8, ""));
		edgeList.add(new BusEdge(2, 3, 7, ""));
		edgeList.add(new BusEdge(3, 4, 9, ""));
		edgeList.add(new BusEdge(4, 5, 10, ""));
		edgeList.add(new BusEdge(5, 6, 2, ""));
		edgeList.add(new BusEdge(6, 7, 1, ""));
		edgeList.add(new BusEdge(7, 0, 8, ""));
		edgeList.add(new BusEdge(1, 7, 11, ""));
		edgeList.add(new BusEdge(2, 8, 2, ""));
		edgeList.add(new BusEdge(8, 6, 6, ""));
		edgeList.add(new BusEdge(3, 5, 14, ""));
		edgeList.add(new BusEdge(7, 8, 7, ""));
		edgeList.add(new BusEdge(2, 5, 4, ""));

		DirectedGraph DG = new DirectedGraph(9);
		
		for(Edge e:edgeList){
			DG.addEdge(e);
		}
		
		
		
		
		ShortRoute SR = new ShortRoute(null);

	}

}
