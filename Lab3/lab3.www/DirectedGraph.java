import java.util.*;

//Efterf�ljarlista?

public class DirectedGraph<E extends Edge> {
	// private Map<Integer, LinkedList<E>> nodeMap;
	private List<E>[] edgeListArray;
	private int noOfNodes;
	private List<E> edgeList;

	public DirectedGraph(int noOfNodes) {
		this.noOfNodes=noOfNodes;
		edgeListArray = (ArrayList<E>[])new ArrayList[noOfNodes];
		edgeList = new ArrayList<E>(); 
		// nodeMap = new HashMap<Integer, LinkedList<E>>();
		// nodeList = new LinkedList[noOfNodes];
		for (int i = 0; i < noOfNodes; i++)
			edgeListArray[i] = new ArrayList<E>();

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
		edgeListArray[e.getSource()].add(e);
		edgeList.add(e);
		// nodeMap.putIfAbsent(source, new LinkedList<E>());
		// nodeMap.get(source).add(e);
	}

	/*
	 * Anv�nda dijkstras
	 */
	public Iterator<E> shortestPath(int from, int to) {
		//l�gg (startnod, 0, tom v�g) i en p-k�
		
		//beh�ver jag skapa en ny? �ndra namn p� denna som anv i minimunspanningtree
		PriorityQueue<CompDijkstraPath<E>> 	dijkstraQueue 	= new PriorityQueue<CompDijkstraPath<E>>();
		ArrayList<E> currentPath;
		dijkstraQueue.add(new CompDijkstraPath<E>(from, 0, new ArrayList<E>()));//	dijsktraQueue.
		
		
		
		CompDijkstraPath<E> currentElement;
		Set<Integer> visitedNodes = new HashSet<Integer>();	//contains �r O(1) f�r hash set
		
		//While k�n inte �r tom
		while (!dijkstraQueue.isEmpty()) {
			currentElement = dijkstraQueue.poll(); 	//(nod, cost, path) = f�rsta elementet i p-k�n
			int currentNode = currentElement.getNode();
			if (!visitedNodes.contains(currentNode)){	//if nod ej bes�kt
				if (currentNode==to)
					return currentElement.getPath().iterator();//ifnod �r slutpunkt returnera path
				else{
					visitedNodes.add(currentNode);	//else markera nod bes�kt
					for(E e: edgeListArray[currentNode]){//for every v on EL(nod)
						if(!visitedNodes.contains(e.getDest())){//if v ej bes�kt
							currentPath =  new ArrayList<E>(currentElement.getPath());
							currentPath.add(e);
							dijkstraQueue.add(new CompDijkstraPath<E>(e.getDest(), e.getWeight()+currentElement.getCost(), currentPath)); 	//l�gg in nytt k�element f�r v i p-k�n						
						}
					}		
				}			
			}	
		}
		return null;
	}
	
	
/*
 * Anv�nda Kruskals algoritm
 * n-1 b�gar som binder ihop alla n hpl till l�gsta kostnad
 */
	public Iterator<E> minimumSpanningTree() {
		System.out.println("startar mST");//TODO debug
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
		PriorityQueue<E> 	edgeQueue 	= new PriorityQueue<E>(11, new CompKruskalEdge<E>());	//11 �r standard f�r java. Anv�nder sig av CompKruskalEdge som j�mf�rare
		LinkedList<E>[] 	cc 			= (LinkedList<E>[]) new LinkedList[noOfNodes];	//Listan cc. Best�r av en array av listor
		LinkedList<E> 		smallList,
							largeList,
							sourceList,
							destList;						
		E 					topEdge;
		int					destListSize, sourceListSize, largeListSize,
							minTreeSize 	= 0,
							minTreeIndex	= 0;  
		
		edgeQueue.addAll(edgeList);		//L�gger till alla b�gar i prioritetsk�n (enl. punkt 1)
				
		for (int i = 0; i < noOfNodes; i++){
			cc[i] = new LinkedList<E>();			//G�r s� att varje entry i arrayen cc f�ren tom lista (enl. punkt 0)
		}


		while(!edgeQueue.isEmpty() && minTreeSize<noOfNodes){
			topEdge 	= 	edgeQueue.remove();	//Ta ut toppelementet ur prioritetsk�n
			sourceList 	= 	cc[topEdge.getSource()];
			destList 	= 	cc[topEdge.getDest()];		
			
			if(sourceList!=destList){ 				
				destListSize=destList.size();
				sourceListSize = sourceList.size();
				if(destListSize>sourceListSize){	//Hitta vilken av listorna som �r minst
					smallList=sourceList;		
					largeList=destList;
					largeListSize = destListSize;
					
				}
				else{
					smallList=destList;
					largeList=sourceList;	
					largeListSize = sourceListSize;
				}
				
				for(E e : smallList){
					largeList.add(e);				//l�gg till varje element i den stora listan fr�n den lilla
					cc[e.getSource()] 	=	cc[e.getDest()] =
											largeList;
				}			
				largeList.add(topEdge);
				
				if(largeListSize>minTreeSize){
					minTreeSize=largeListSize + 1;  //Since we just added one
					minTreeIndex=topEdge.getSource();
				}
				
				cc[topEdge.getSource()] = 	cc[topEdge.getDest()] =		
											largeList;	//peka om f�ltet s� den pekar p� den stora listan //TODO beh�vs?

			}//if											
		}//while
		//TODO varna om inget uppsp�nnande tr�d hittas?
		return cc[minTreeIndex].iterator();


		//Skapa ett f�lt cc, som f�r varje nod inneh�ller en egen tom lista
		//LinkedList<E>[] cc = new LinkedList<E>();

		//if (cc[temp] == null) {
		//   cc[temp] = new LinkedList<Integer>();
		//}
	/*
		for(int i=0; i<=nodeList.length; i++){
			//	cc[i] = new LinkedList<E>;
		}
*/
		//L�gg in alla b�gar i en prioritetsk�

		//while(pq!=null && cc <n){
		// e=(from, to, weight) (fr�n k�n?)
		//if(from
	//	return null;
	}

}
