import java.util.*;

//Efterföljarlista?

public class DirectedGraph<E extends Edge> {
	// private Map<Integer, LinkedList<E>> nodeMap;
	private List<E>[] nodeList;
	private int noOfNodes;
	private List<E> edgeList;

	public DirectedGraph(int noOfNodes) {
		this.noOfNodes=noOfNodes;
		nodeList = new ArrayList[noOfNodes];
		edgeList = new ArrayList<E>(); 
		// nodeMap = new HashMap<Integer, LinkedList<E>>();
		// nodeList = new LinkedList[noOfNodes];
		for (int i = 0; i < noOfNodes; i++)
			nodeList[i] = new ArrayList<E>();

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
		edgeList.add(e);
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
		PriorityQueue<E> edgeQueue = new PriorityQueue<E>(11, new CompKruskalEdge<E>());	//11 är standard för java. ANvänder sig av CompKruskalEdge som jämförare
		ArrayList<E>[] cc = new ArrayList[noOfNodes];	//Listan cc. Består av en array av listor
		
		int arraySize = 0;
		
		for(E e : edgeList){
			edgeQueue.add(e);		//Lägger till alla bågar i prioritetskön (enl. punkt 1)
		}
		
		
		for (int i = 0; i < noOfNodes; i++){
			cc[i] = new ArrayList<E>();			//Gör så att varje entry i arrayen cc fåren tom lista (enl. punkt 0)
		}
		E topEdge;
		System.out.println("Alla elementen i prioriteskön: ");//TODO debug
		for(E e : edgeQueue){
			System.out.print(e + "     ");//TODO debug
		}
		System.out.println("");//TODO debug

		while(!edgeQueue.isEmpty() && arraySize<noOfNodes){//&& (cc.length < noOfNodes	) //TODO det som är utkommenterat ska visst med, men hur? UPDATE: kanske lyckats fixa det nu. Kolla variabeln arraySize
			topEdge = edgeQueue.remove();

			ArrayList<E> smallList, largeList;
			ArrayList<E> listA = cc[topEdge.getSource()];
			ArrayList<E> listB = cc[topEdge.getDest()];		
			
			System.out.println("\nBåge: " + topEdge);
			System.out.println("cc["+ topEdge.getSource()+"] : "+cc[topEdge.getSource()]);	//TODO debug
			System.out.println("cc["+ topEdge.getDest()+"] : "+ cc[topEdge.getDest()]);	//TODO debug
			if(listA!=listB)
				System.out.println("Listorna är inte lika.");//TODO debug
			else
				System.out.println("Listorna är lika. Lägger inte till.");//TODO debug
			if(listA!=listB){ 
				
				if(listA.isEmpty())
					arraySize++;
				if(listB.isEmpty())	
					arraySize++;
				
				if(listB.size()>listA.size()){	//Hitta vilken av listorna som är minst
					smallList=listA;		
					largeList=listB;
				}
				else{
					largeList=listB;
					smallList=listA;					
				}

				for(E e : smallList){
					largeList.add(e);				//lägg till varje element i den stora listan från den lilla
					cc[e.getSource()]=largeList;
					cc[e.getDest()]=largeList;
				}			
				largeList.add(topEdge);
				cc[topEdge.getSource()]=largeList;	//peka om fältet så den pekar på den stora listan //TODO behövs?
				cc[topEdge.getDest()]=largeList; //TODO behövs?
				
				
				

				System.out.println("Lägger till " + topEdge);//TODO debug
			}//if
			System.out.println("Skriver ut hela listan:");	//TODO debug
			for(int i = 0; i<noOfNodes; i++){	//TODO debug
				System.out.println("cc["+ i +"] : "+cc[i]);	//TODO debug

			}

		}//while
	
		for(E e : cc[0]){	//TODO debug. Skriver ut miniträdet
			System.out.print(e + "     ");

		}


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
