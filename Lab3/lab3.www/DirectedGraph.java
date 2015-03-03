import java.util.*;

//Efterföljarlista?

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
		 * Revelation! NodeTable.java gör om alla nodnamn till heltal. Är det så
		 * att de är från 0-nånting? I så fall blir det mycket snabbare, och
		 * smidigare med ett fält eller en ArrayList. Testar det.
		 */
	}

	public void addEdge(E e) {
		/*
		 *TODO Ska man bara ta med båden om ingen kortare redan finns? Eller
		 * tillåtervi flera bågar mellan samma noder?
		 */
		// int source = e.getSource();
		edgeListArray[e.getSource()].add(e);
		edgeList.add(e);
		// nodeMap.putIfAbsent(source, new LinkedList<E>());
		// nodeMap.get(source).add(e);
	}

	/*
	 * Använda dijkstras
	 */
	public Iterator<E> shortestPath(int from, int to) {
		//lägg (startnod, 0, tom väg) i en p-kö
		
		//behöver jag skapa en ny? ändra namn på denna som anv i minimunspanningtree
		PriorityQueue<CompDijkstraPath<E>> 	dijkstraQueue 	= new PriorityQueue<CompDijkstraPath<E>>();
		ArrayList<E> currentPath;
		dijkstraQueue.add(new CompDijkstraPath<E>(from, 0, new ArrayList<E>()));//	dijsktraQueue.
		
		
		
		CompDijkstraPath<E> currentElement;
		Set<Integer> visitedNodes = new HashSet<Integer>();	//contains är O(1) för hash set
		
		//While kön inte är tom
		while (!dijkstraQueue.isEmpty()) {
			currentElement = dijkstraQueue.poll(); 	//(nod, cost, path) = första elementet i p-kön
			int currentNode = currentElement.getNode();
			if (!visitedNodes.contains(currentNode)){	//if nod ej besökt
				if (currentNode==to)
					return currentElement.getPath().iterator();//ifnod är slutpunkt returnera path
				else{
					visitedNodes.add(currentNode);	//else markera nod besökt
					for(E e: edgeListArray[currentNode]){//for every v on EL(nod)
						if(!visitedNodes.contains(e.getDest())){//if v ej besökt
							currentPath =  new ArrayList<E>(currentElement.getPath());
							currentPath.add(e);
							dijkstraQueue.add(new CompDijkstraPath<E>(e.getDest(), e.getWeight()+currentElement.getCost(), currentPath)); 	//lägg in nytt köelement för v i p-kön						
						}
					}		
				}			
			}	
		}
		return null;
	}
	
	public Iterator<E> minimumSpanningTree() {
		PriorityQueue<E> 	edgeQueue 	= new PriorityQueue<E>(11, new CompKruskalEdge<E>());	//11 är standard för java. Använder sig av CompKruskalEdge som jämförare
		List<E>[] 			cc 			= (LinkedList<E>[]) new LinkedList[noOfNodes];	//Listan cc. Består av en array av listor
		List<E> 			smallList,
							largeList,
							sourceList,
							destList;						
		E 					currentEdge;
		int					destListSize, sourceListSize, largeListSize,
							minTreeSize 	= 0,
							minTreeIndex	= 0;  
		
		edgeQueue.addAll(edgeList);		//Lägger till alla bågar i prioritetskön (enl. punkt 1)		
		for (int i = 0; i < noOfNodes; i++){
			cc[i] = new LinkedList<E>();			//Gör så att varje entry i arrayen cc fåren tom lista (enl. punkt 0)
		}
		while(!edgeQueue.isEmpty() && minTreeSize<noOfNodes){
			currentEdge = 	edgeQueue.remove();	//Ta ut toppelementet ur prioritetskön
			sourceList 	= 	cc[currentEdge.getSource()];
			destList 	= 	cc[currentEdge.getDest()];		
			
			if(sourceList!=destList){ 				
				destListSize=destList.size();
				sourceListSize = sourceList.size();
				if(destListSize>sourceListSize){	//Hitta vilken av listorna som är minst
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
					largeList.add(e);				//lägg till varje element i den stora listan från den lilla
					cc[e.getSource()] 	=	cc[e.getDest()] =
											largeList;
				}			
				largeList.add(currentEdge);
				
				if(largeListSize>minTreeSize){
					minTreeSize=largeListSize + 1;  //Since we just added one
					minTreeIndex=currentEdge.getSource();
				}		
				cc[currentEdge.getSource()] = 	cc[currentEdge.getDest()] =		
											largeList;	//peka om fältet så den pekar på den stora listan //TODO behövs?
			}//if											
		}//while
		//TODO varna om inget uppspännande träd hittas?
		return cc[minTreeIndex].iterator();
	}
}
