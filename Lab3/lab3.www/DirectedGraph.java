import java.util.*;

//Efterföljarlista?

public class DirectedGraph<E extends Edge> {
	// private Map<Integer, LinkedList<E>> nodeMap;
	private LinkedList<E>[] nodeList;

	public DirectedGraph(int noOfNodes) {
		// nodeMap = new HashMap<Integer, LinkedList<E>>();
		// nodeList = new LinkedList[noOfNodes];
		for (int i = 0; i < noOfNodes; i++)
			nodeList[i] = new LinkedList<E>();

		/*
		 * Revelation! NodeTable.java gör om alla nodnamn till heltal. Är det så
		 * att de är från 0-nånting? I så fall blir det mycket snabbare, och
		 * smidigare med ett fält eller en ArrayList. Testar det.
		 */
	}

	public void addEdge(E e) {
		/*
		 * Ska man bara ta med båden om ingen kortare redan finns? Eller
		 * tillåtervi flera bågar mellan samma noder?
		 */
		// int source = e.getSource();
		nodeList[e.getSource()].add(e);
		// nodeMap.putIfAbsent(source, new LinkedList<E>());
		// nodeMap.get(source).add(e);
	}

	/*
	 * Använda dijkstras
	 */
	public Iterator<E> shortestPath(int from, int to) {
		return null;
	}
/*
 * Använda Kruskals algoritm
 * n-1 bågar som binder ihop alla n hpl till lägsta kostnad
 */
	public Iterator<E> minimumSpanningTree() {
		
		//Skapa ett fält cc, som för varje nod innehåller en egen tom lista
		//LinkedList<E>[] cc = new LinkedList<E>();
	
		//if (cc[temp] == null) {
		  //   cc[temp] = new LinkedList<Integer>();
		//}
		for(int i=0; i<=nodeList.length; i++){
		//	cc[i] = new LinkedList<E>;
		}
		
		//Lägg in alla bågar i en prioritetskö
		
		//while(pq!=null && cc <n){
		// e=(from, to, weight) (från kön?)
		//if(from
		return null;
	}

}
