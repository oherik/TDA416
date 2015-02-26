import java.util.*;

//Efterföljarlista?

public class DirectedGraph<E extends Edge> {
	// private Map<Integer, LinkedList<E>> nodeMap;
	private LinkedList<E>[] nodeList;
	private int noOfNodes;
	private PriorityQueue nodeQueue;

	public DirectedGraph(int noOfNodes) {
		this.noOfNodes=noOfNodes;
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
		nodeQueue.add(e);
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
		/*
		 * 
		 * @author Erik
		 * 
		Pseudokod vi fick:
		0
		skapa ett fält cc som för varje nod
		innehåller en egen tom lista (som skall
		innehålla bågar så småningom)
		(dvs varje nod är i en egen komponent)
		1
		Lägg in alla bågar i en prioritetskö
		2
		Så länge pq, ej är tom &&
		|cc| < n
		3
		hämta e = (from, to,
		weight
		) från kön
		5
		om from och to inte refererar till
		samma lista i cc
		6
		flytta över alla elementen från den
		kortare listan till den andra och se till
		att alla berörda noder i cc refererar
		till den påfyllda listan
		8
		lägg slutligen e i den påfyllda listan
		 *
		 */
		PriorityQueue<E> pq = nodeQueue;
		LinkedList<E>[] cc = new LinkedList[noOfNodes];
		CompKruskalEdge comp = new CompKruskalEdge();
		for (int i = 0; i < noOfNodes; i++){
			cc[i] = new LinkedList<E>();
		}
		E temp;
		
		while(!pq.isEmpty() && (cc.length < noOfNodes	)){
			temp = pq.remove();
			
			
			
			int start = comp.getStartIndex(temp,cc);
			int dest = comp.getDestIndex(temp,cc);
			if(start!=dest){ //if inte samma? //TODO
				LinkedList<E> smallList, largeList;
				if(cc[start].size()>cc[dest].size()){	//Hitta vilken av listorna som är minst
					smallList=cc[dest];
					largeList=cc[start];				
				}
				else{
					smallList=cc[start];
					largeList=cc[dest];
				}

				for(E e : smallList){
				    largeList.add(e);				//lägg till varje element i den stora listan från den lilla
				    cc[e.getSource()]=largeList;	//peka om fältet så den pekar på den stora listan
				    smallList.remove(e); //TODO är detta smart tro?
				  }
				largeList.add(temp);
			}//if		
		}//while
		
		
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
