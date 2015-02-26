import java.util.*;

//Efterf�ljarlista?

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
		nodeQueue.add(e);
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
		/*
		 * 
		 * @author Erik
		 * 
		Pseudokod vi fick:
		0
		skapa ett f�lt cc som f�r varje nod
		inneh�ller en egen tom lista (som skall
		inneh�lla b�gar s� sm�ningom)
		(dvs varje nod �r i en egen komponent)
		1
		L�gg in alla b�gar i en prioritetsk�
		2
		S� l�nge pq, ej �r tom &&
		|cc| < n
		3
		h�mta e = (from, to,
		weight
		) fr�n k�n
		5
		om from och to inte refererar till
		samma lista i cc
		6
		flytta �ver alla elementen fr�n den
		kortare listan till den andra och se till
		att alla ber�rda noder i cc refererar
		till den p�fyllda listan
		8
		l�gg slutligen e i den p�fyllda listan
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
				if(cc[start].size()>cc[dest].size()){	//Hitta vilken av listorna som �r minst
					smallList=cc[dest];
					largeList=cc[start];				
				}
				else{
					smallList=cc[start];
					largeList=cc[dest];
				}

				for(E e : smallList){
				    largeList.add(e);				//l�gg till varje element i den stora listan fr�n den lilla
				    cc[e.getSource()]=largeList;	//peka om f�ltet s� den pekar p� den stora listan
				    smallList.remove(e); //TODO �r detta smart tro?
				  }
				largeList.add(temp);
			}//if		
		}//while
		
		
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
