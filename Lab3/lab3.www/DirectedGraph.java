import java.util.*;

//Efterf�ljarlista?

public class DirectedGraph<E extends Edge> {
	// private Map<Integer, LinkedList<E>> nodeMap;
	private LinkedList<E>[] nodeList;

	public DirectedGraph(int noOfNodes) {
		// nodeMap = new HashMap<Integer, LinkedList<E>>();
		// nodeList = new LinkedList[noOfNodes];
		for (int i = 0; i < noOfNodes; i++)
			nodeList[i] = new LinkedList<E>();

		/*
		 * Revelation! NodeTable.java g�r om alla nodnamn till heltal. �r det s�
		 * att de �r fr�n 0-n�nting? I s� fall blir det mycket snabbare, och
		 * smidigare med ett f�lt eller en ArrayList. Testar det.
		 */
	}

	public void addEdge(E e) {
		/*
		 * Ska man bara ta med b�den om ingen kortare redan finns? Eller
		 * till�tervi flera b�gar mellan samma noder?
		 */
		// int source = e.getSource();
		nodeList[e.getSource()].add(e);
		// nodeMap.putIfAbsent(source, new LinkedList<E>());
		// nodeMap.get(source).add(e);
	}

	/*
	 * Anv�nda dijkstras
	 */
	public Iterator<E> shortestPath(int from, int to) {
		return null;
	}
/*
 * Anv�nda Kruskals algoritm
 * n-1 b�gar som binder ihop alla n hpl till l�gsta kostnad
 */
	public Iterator<E> minimumSpanningTree() {
		
		//Skapa ett f�lt cc, som f�r varje nod inneh�ller en egen tom lista
		//LinkedList<E>[] cc = new LinkedList<E>();
	
		//if (cc[temp] == null) {
		  //   cc[temp] = new LinkedList<Integer>();
		//}
		for(int i=0; i<=nodeList.length; i++){
		//	cc[i] = new LinkedList<E>;
		}
		
		//L�gg in alla b�gar i en prioritetsk�
		
		//while(pq!=null && cc <n){
		// e=(from, to, weight) (fr�n k�n?)
		//if(from
		return null;
	}

}
