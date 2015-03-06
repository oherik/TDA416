//Grupp 7 - Erik Öhrn & Paula Eriksson Imable
import java.util.*;

/**
 * En klass som kan hitta kortaste vägen mellan två noder samt skapa ett
 * minimalt uppspännande träd.
 * 
 * @author Erik Öhrn & Paula Eriksson Imable
 *
 * @param <E>
 *            en klass som utökar Edge
 */
public class DirectedGraph<E extends Edge> {
	// Variabeldeklaration
	private List<E>[] edgeListArray;
	private List<E> edgeList;
	private int noOfNodes;

	/**
	 * Konstruktor, initialerar en ny graf. Grafen använder sig av en
	 * efterföljarlisa för att spara bågarna som går från varje nod. Denna
	 * använder sig av ett fält av listor, där fältets storlek är antalet noder.
	 * 
	 * @param noOfNodes
	 *            Antalet noder i grafen
	 * @throws IndexOutOfBoundsException
	 *             Om antalet noder är mindre än ett
	 * 
	 */
	public DirectedGraph(int noOfNodes) {
		if (noOfNodes < 1)
			throw new IndexOutOfBoundsException(
					"DirectedGraph: antalet noder måste vara 1 eller fler");
		this.noOfNodes = noOfNodes;
		edgeListArray = (ArrayList<E>[]) new ArrayList[noOfNodes];
		edgeList = new ArrayList<E>();
		for (int i = 0; i < noOfNodes; i++)
			edgeListArray[i] = new ArrayList<E>(); // Fyller listfältet med
													// tomma listor
	}

	/**
	 * Lägger till en ny båge, dels i efterföljarlistan och dels i listan som
	 * innehåller alla bågar
	 * 
	 * @param e
	 *            bågen som läggs till
	 * @throws NullPointerException
	 *             om e är null
	 */
	public void addEdge(E e) {
		if (e == null)
			throw new NullPointerException(
					"DirectedGraph - addEdge(E e): e får inte vara null");
		edgeListArray[e.getSource()].add(e);
		edgeList.add(e);
	}

	/**
	 * Använder en variant av Dijkstras algoritm (en till en) för att hitta den
	 * kortaste vägen mellan två noder. Den sparar de besökta noderna i ett
	 * HashSet, mest för att uppslagning av contains() har komplexiteten O(1).
	 * 
	 * @param from
	 *            startnoden
	 * @param to
	 *            slutnoden
	 * @throws IndexOutOfBoundsException
	 *             om from eller to är mindre än noll
	 * @return en iterator över vägen från startnoden till slutnoden, eller null
	 *         om ingen väg hittas
	 */
	public Iterator<E> shortestPath(int from, int to) {
		if (from < 0)
			throw new IndexOutOfBoundsException(
					"DirectedGraph - shortestPath(int from, int to): from får inte vara negativ");
		else if (to < 0)
			throw new IndexOutOfBoundsException(
					"DirectedGraph - shortestPath(int from, int to): to får inte vara negativ");
		// Variabeldeklaration
		PriorityQueue<CompDijkstraPath<E>> dijkstraQueue = new PriorityQueue<CompDijkstraPath<E>>();
		ArrayList<E> currentPath;
		CompDijkstraPath<E> currentElement;
		Set<Integer> visitedNodes = new HashSet<Integer>();

		dijkstraQueue.add(new CompDijkstraPath<E>(from, 0, new ArrayList<E>())); // Lägg
																					// till
																					// startnoden
		while (!dijkstraQueue.isEmpty()) {
			currentElement = dijkstraQueue.poll(); // Hämta elementet med
													// kortast väg
			int currentNode = currentElement.getNode();
			if (!visitedNodes.contains(currentNode)) {
				if (currentNode == to)
					return currentElement.getPath().iterator(); // Har kommit
																// fram,
																// returnera
																// vägen dit
				else {
					visitedNodes.add(currentNode);
					for (E e : edgeListArray[currentNode]) {
						if (!visitedNodes.contains(e.getDest())) {
							currentPath = new ArrayList<E>(
									currentElement.getPath()); // Kopiera vägen
																// från
																// elementet
																// till en ny
																// lista
							currentPath.add(e); // Och lägg till den aktuella
												// bågen
							dijkstraQueue.add(new CompDijkstraPath<E>(e
									.getDest(), e.getWeight()
									+ currentElement.getCost(), currentPath)); // Lägg
																				// till
																				// köelementet
																				// i
																				// prioritetskön
						}
					}
				}
			}
		}
		return null; // Ingen väg funnen
	}

	/**
	 * Hittar ett minsta uppspännande träd hos grafen med hjälp av Kruskals
	 * algoritm Prioritetskön använder sig av CompKruskalEdge som jämförare. De
	 * olika listornas storlekar sparas inte som variabeln då detta efter test
	 * tycktes försvåra läsbarheten samtidigt oms .size() ändå på dessa går på
	 * O(1) i tidskomplexitet. Kräver en hel sammanbunden graf för att ge ett korrekt svar
	 * @return en iterator över bågarna som utgör trädet
	 */
	public Iterator<E> minimumSpanningTree() {
		// Variabeldeklaration
		PriorityQueue<E> edgeQueue = new PriorityQueue<E>(11,
				new CompKruskalEdge<E>()); // 11 som startstorlek är standard
											// för Java.
		List<E>[] cc = (LinkedList<E>[]) new LinkedList[noOfNodes];
		List<E> smallList, largeList, sourceList, destList;
		E currentEdge;
		int minTreeSize = 0, minTreeIndex = 0;

		edgeQueue.addAll(edgeList); // Lägger till alla bågar i prioritetskön
		for (int i = 0; i < noOfNodes; i++) {
			cc[i] = new LinkedList<E>();
		}
		while (!edgeQueue.isEmpty() && minTreeSize < noOfNodes) {
			currentEdge = edgeQueue.remove();
			sourceList = cc[currentEdge.getSource()];
			destList = cc[currentEdge.getDest()];

			if (sourceList != destList) {
				if (destList.size() > sourceList.size()) {
					smallList = sourceList;
					largeList = destList;
				} else {
					smallList = destList;
					largeList = sourceList;
				}

				for (E e : smallList) { // Lägger till och pekar om
					largeList.add(e);
					cc[e.getSource()] = cc[e.getDest()] = largeList;
				}

				largeList.add(currentEdge);

				if (largeList.size() > minTreeSize) {
					minTreeSize = largeList.size();
					minTreeIndex = currentEdge.getSource();
				}
				cc[currentEdge.getSource()] = cc[currentEdge.getDest()] = largeList;
			}// if
		}// while
		return cc[minTreeIndex].iterator();
	}
}
