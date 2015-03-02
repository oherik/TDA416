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
		PriorityQueue<E> 	dijsktraQueue 	= new PriorityQueue<E>(from, 0, emptyPath);
		
		//While k�n inte �r tom
		while (!dijsktraQueue.isEmpty()) {
			//(nod, cost, path) = f�rsta elementet i p-k�n
		//	dijsktraQueue.
		}
		
		//if nod ej bes�kt
		//ifnod �r slutpunkt returnera path
		//else markera nod bes�kt
		//for every v on EL(nod)
		//if v ej bes�kt
		//l�gg in nytt k�element f�r v i p-k�n
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
		int 				arraySize 	= 0;
		E 					topEdge;
		
		for(E e : edgeList){
			edgeQueue.add(e);		//L�gger till alla b�gar i prioritetsk�n (enl. punkt 1)
		}
				
		for (int i = 0; i < noOfNodes; i++){
			cc[i] = new LinkedList<E>();			//G�r s� att varje entry i arrayen cc f�ren tom lista (enl. punkt 0)
		}

								System.out.println("Alla elementen i prioritesk�n: ");//TODO debug
								for(E e : edgeQueue){
									System.out.print(e + "     ");//TODO debug
								}
								System.out.println("");//TODO debug

		while(!edgeQueue.isEmpty() && arraySize<noOfNodes){//&& (cc.length < noOfNodes	) //TODO det som �r utkommenterat ska visst med, men hur? UPDATE: kanske lyckats fixa det nu. Kolla variabeln arraySize			
			topEdge 	= 	edgeQueue.remove();	//Ta ut toppelementet ur prioritetsk�n
			sourceList 	= 	cc[topEdge.getSource()];
			destList 	= 	cc[topEdge.getDest()];		
			
								System.out.println("\nB�ge: " + topEdge);	//TODO debug
								System.out.println("cc["+ topEdge.getSource()+"] : "+cc[topEdge.getSource()]);	//TODO debug
								System.out.println("cc["+ topEdge.getDest()+"] : "+ cc[topEdge.getDest()]);	//TODO debug
								if(sourceList!=destList)
									System.out.println("Listorna �r inte lika.");//TODO debug
								else
									System.out.println("Listorna �r lika. L�gger inte till.");//TODO debug
			if(sourceList!=destList){ 				
				if(sourceList.isEmpty())		//�r det s� att den �r tom kommer den inte vara det efter att elementet lagts till
					arraySize++;		//detta inneb�r att antalet icke-tomma entries i arrayen cc har �kat emd 1
				if(destList.isEmpty())		//samma f�r den andra listan
					arraySize++;
				
				if(destList.size()>sourceList.size()){	//Hitta vilken av listorna som �r minst
					smallList=sourceList;		
					largeList=destList;
				}
				else{
					smallList=destList;
					largeList=sourceList;					
				}

				for(E e : smallList){
					largeList.add(e);				//l�gg till varje element i den stora listan fr�n den lilla
					cc[e.getSource()] 	=	cc[e.getDest()] =
											largeList;
				}			
				largeList.add(topEdge);
				
				cc[topEdge.getSource()] = 	cc[topEdge.getDest()] =		
											largeList;	//peka om f�ltet s� den pekar p� den stora listan //TODO beh�vs?
				
												System.out.println("L�gger till " + topEdge);//TODO debug
			}//if
												System.out.println("Skriver ut hela listan:");	//TODO debug
												for(int i = 0; i<noOfNodes; i++){	//TODO debug
													System.out.println("cc["+ i +"] : "+cc[i]);	//TODO debug
												}
		}//while
												System.out.println("\nKlar! Minitr�det inneh�ller b�garna: "); //TODO debug
												for(E e : cc[0]){	//TODO debug. Skriver ut minitr�det
													System.out.println(e);
												}
		
		return cc[0].iterator();


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
