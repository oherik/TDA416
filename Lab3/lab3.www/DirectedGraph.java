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
		 * Ska man bara ta med båden om ingen kortare redan finns? Eller
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
		PriorityQueue<QueueElement<E>> 	dijkstraQueue 	= new PriorityQueue<QueueElement<E>>();
		
		dijkstraQueue.add(new QueueElement(from, 0, new ArrayList<E>()));
		
		QueueElement<E> currentElement;
		ArrayList<E> currentPath;
		ArrayList<Integer> visitedNodes = new ArrayList<Integer>();
		
		//While kön inte är tom
		while (!dijkstraQueue.isEmpty()) {
			//(nod, cost, path) = första elementet i p-kön
			
		currentElement = dijkstraQueue.poll();
		int currentNode = currentElement.getNode();
		if (!visitedNodes.contains(currentNode)){
			if (currentNode==to){
				//for(Q)		
				visitedNodes.add(currentNode);
				return currentElement.getPath().iterator();
			}
			else{
				visitedNodes.add(currentNode);
				for(E e: edgeListArray[currentNode]){
					if(!visitedNodes.contains(e.getDest())){
						currentPath =  new ArrayList<E>(currentElement.getPath());
						currentPath.add(e);
						dijkstraQueue.add(new QueueElement(e.getDest(), e.getWeight()+currentElement.getWeight(), currentPath));	
						
					}
				}
					
				
				
				
			}
			
			
		}
			
			
		//	dijsktraQueue.
		}
		
		//if nod ej besökt
		//ifnod är slutpunkt returnera path
		//else markera nod besökt
		//for every v on EL(nod)
		//if v ej besökt
		//lägg in nytt köelement för v i p-kön
		return null;
	}
	
	
/*
 * Använda Kruskals algoritm
 * n-1 bågar som binder ihop alla n hpl till lägsta kostnad
 */
	public Iterator<E> minimumSpanningTree() {
		System.out.println("startar mST");//TODO debug
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
		PriorityQueue<E> 	edgeQueue 	= new PriorityQueue<E>(11, new CompKruskalEdge<E>());	//11 är standard för java. Använder sig av CompKruskalEdge som jämförare
		LinkedList<E>[] 	cc 			= (LinkedList<E>[]) new LinkedList[noOfNodes];	//Listan cc. Består av en array av listor
		LinkedList<E> 		smallList,
							largeList,
							sourceList,
							destList;	
		int 				minTreeSize 	= 0;
		int 				longField	= 0; 		//TODO kom igen hitta ett bättre namn
		E 					topEdge;
		int					destListSize, sourceListSize, largeListSize;	 
		
		
			edgeQueue.addAll(edgeList);		//Lägger till alla bågar i prioritetskön (enl. punkt 1)
		
				
		for (int i = 0; i < noOfNodes; i++){
			cc[i] = new LinkedList<E>();			//Gör så att varje entry i arrayen cc fåren tom lista (enl. punkt 0)
		}

							/*	System.out.println("Alla elementen i prioriteskön: ");//TODO debug
								for(E e : edgeQueue){
									System.out.print(e + "     ");//TODO debug
								}
								System.out.println("");//TODO debug*/

		while(!edgeQueue.isEmpty() && minTreeSize<noOfNodes){//&& (cc.length < noOfNodes	) //TODO det som är utkommenterat ska visst med, men hur? UPDATE: kanske lyckats fixa det nu. Kolla variabeln arraySize			
			topEdge 	= 	edgeQueue.remove();	//Ta ut toppelementet ur prioritetskön
			sourceList 	= 	cc[topEdge.getSource()];
			destList 	= 	cc[topEdge.getDest()];		
			
								/*System.out.println("\nBåge: " + topEdge);	//TODO debug
								System.out.println("cc["+ topEdge.getSource()+"] : "+cc[topEdge.getSource()]);	//TODO debug
								System.out.println("cc["+ topEdge.getDest()+"] : "+ cc[topEdge.getDest()]);	//TODO debug
								if(sourceList!=destList)
									System.out.println("Listorna är inte lika.");//TODO debug
								else
									System.out.println("Listorna är lika. Lägger inte till.");//TODO debug*/
			if(sourceList!=destList){ 				
			/*	if(sourceList.isEmpty())		//är det så att den är tom kommer den inte vara det efter att elementet lagts till
					arraySize++;		//detta innebär att antalet icke-tomma entries i arrayen cc har ökat emd 1
				if(destList.isEmpty())		//samma för den andra listan
					arraySize++;*/
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
				largeList.add(topEdge);
				
				if(largeListSize>minTreeSize){
					minTreeSize=largeListSize + 1;  //Since we just added one
					longField=topEdge.getSource();
				}
				
				cc[topEdge.getSource()] = 	cc[topEdge.getDest()] =		
											largeList;	//peka om fältet så den pekar på den stora listan //TODO behövs?

				/*
				for(E e : largeList){	//TODO debug. Skriver ut miniträdet
					System.out.println(e);
				}
				for(E e : cc[longField]){	//TODO debug. Skriver ut miniträdet
					System.out.print(e);
				}
				*/
												//System.out.println("Lägger till " + topEdge);//TODO debug
			}//if
											/*	System.out.println("Skriver ut hela listan:");	//TODO debug
												for(int i = 0; i<noOfNodes; i++){	//TODO debug
													System.out.println("cc["+ i +"] : "+cc[i]);	//TODO debug
												}*/
		}//while
												System.out.println("\nKlar! Miniträdet innehåller bågarna: "); //TODO debug
												for(E e : cc[longField]){	//TODO debug. Skriver ut miniträdet
													System.out.print(e + "  ");
												}
												
		
		return cc[longField].iterator();


		//Skapa ett fält cc, som för varje nod innehåller en egen tom lista
		//LinkedList<E>[] cc = new LinkedList<E>();

		//if (cc[temp] == null) {
		//   cc[temp] = new LinkedList<Integer>();
		//}
	/*
		for(int i=0; i<=nodeList.length; i++){
			//	cc[i] = new LinkedList<E>;
		}
*/
		//Lägg in alla bågar i en prioritetskö

		//while(pq!=null && cc <n){
		// e=(from, to, weight) (från kön?)
		//if(from
	//	return null;
	}

}
