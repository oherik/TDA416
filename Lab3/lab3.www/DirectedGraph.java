//Grupp 7 - Erik �hrn & Paula Eriksson Imable
import java.util.*;

/**
 * En klass som kan hitta kortaste v�gen mellan tv� noder samt skapa ett
 * minimalt uppsp�nnande tr�d.
 * 
 * @author Erik �hrn & Paula Eriksson Imable
 *
 * @param <E>
 *            en klass som ut�kar Edge
 */
public class DirectedGraph<E extends Edge> {
	// Variabeldeklaration
	private List<E>[] edgeListArray;
	private List<E> edgeList;
	private int noOfNodes;

	/**
	 * Konstruktor, initialerar en ny graf. Grafen anv�nder sig av en
	 * efterf�ljarlisa f�r att spara b�garna som g�r fr�n varje nod. Denna
	 * anv�nder sig av ett f�lt av listor, d�r f�ltets storlek �r antalet noder.
	 * 
	 * @param noOfNodes
	 *            Antalet noder i grafen
	 * @throws IndexOutOfBoundsException
	 *             Om antalet noder �r mindre �n ett
	 * 
	 */
	public DirectedGraph(int noOfNodes) {
		if (noOfNodes < 1)
			throw new IndexOutOfBoundsException(
					"DirectedGraph: antalet noder m�ste vara 1 eller fler");
		this.noOfNodes = noOfNodes;
		edgeListArray = (ArrayList<E>[]) new ArrayList[noOfNodes];
		edgeList = new ArrayList<E>();
		for (int i = 0; i < noOfNodes; i++)
			edgeListArray[i] = new ArrayList<E>(); // Fyller listf�ltet med
													// tomma listor
	}

	/**
	 * L�gger till en ny b�ge, dels i efterf�ljarlistan och dels i listan som
	 * inneh�ller alla b�gar
	 * 
	 * @param e
	 *            b�gen som l�ggs till
	 * @throws NullPointerException
	 *             om e �r null
	 */
	public void addEdge(E e) {
		if (e == null)
			throw new NullPointerException(
					"DirectedGraph - addEdge(E e): e f�r inte vara null");
		edgeListArray[e.getSource()].add(e);
		edgeList.add(e);
	}

	/**
	 * Anv�nder en variant av Dijkstras algoritm (en till en) f�r att hitta den
	 * kortaste v�gen mellan tv� noder. Den sparar de bes�kta noderna i ett
	 * HashSet, mest f�r att uppslagning av contains() har komplexiteten O(1).
	 * 
	 * @param from
	 *            startnoden
	 * @param to
	 *            slutnoden
	 * @throws IndexOutOfBoundsException
	 *             om from eller to �r mindre �n noll
	 * @return en iterator �ver v�gen fr�n startnoden till slutnoden, eller null
	 *         om ingen v�g hittas
	 */
	public Iterator<E> shortestPath(int from, int to) {
		if (from < 0)
			throw new IndexOutOfBoundsException(
					"DirectedGraph - shortestPath(int from, int to): from f�r inte vara negativ");
		else if (to < 0)
			throw new IndexOutOfBoundsException(
					"DirectedGraph - shortestPath(int from, int to): to f�r inte vara negativ");
		// Variabeldeklaration
		PriorityQueue<CompDijkstraPath<E>> dijkstraQueue = new PriorityQueue<CompDijkstraPath<E>>();
		ArrayList<E> currentPath;
		CompDijkstraPath<E> currentElement;
		Set<Integer> visitedNodes = new HashSet<Integer>();

		dijkstraQueue.add(new CompDijkstraPath<E>(from, 0, new ArrayList<E>())); // L�gg
																					// till
																					// startnoden
		while (!dijkstraQueue.isEmpty()) {
			currentElement = dijkstraQueue.poll(); // H�mta elementet med
													// kortast v�g
			int currentNode = currentElement.getNode();
			if (!visitedNodes.contains(currentNode)) {
				if (currentNode == to)
					return currentElement.getPath().iterator(); // Har kommit
																// fram,
																// returnera
																// v�gen dit
				else {
					visitedNodes.add(currentNode);
					for (E e : edgeListArray[currentNode]) {
						if (!visitedNodes.contains(e.getDest())) {
							currentPath = new ArrayList<E>(
									currentElement.getPath()); // Kopiera v�gen
																// fr�n
																// elementet
																// till en ny
																// lista
							currentPath.add(e); // Och l�gg till den aktuella
												// b�gen
							dijkstraQueue.add(new CompDijkstraPath<E>(e
									.getDest(), e.getWeight()
									+ currentElement.getCost(), currentPath)); // L�gg
																				// till
																				// k�elementet
																				// i
																				// prioritetsk�n
						}
					}
				}
			}
		}
		return null; // Ingen v�g funnen
	}

	/**
	 * Hittar ett minsta uppsp�nnande tr�d hos grafen med hj�lp av Kruskals
	 * algoritm Prioritetsk�n anv�nder sig av CompKruskalEdge som j�mf�rare. De
	 * olika listornas storlekar sparas inte som variabeln d� detta efter test
	 * tycktes f�rsv�ra l�sbarheten samtidigt oms .size() �nd� p� dessa g�r p�
	 * O(1) i tidskomplexitet. Kr�ver en hel sammanbunden graf f�r att ge ett korrekt svar
	 * @return en iterator �ver b�garna som utg�r tr�det
	 */
	public Iterator<E> minimumSpanningTree() {
		// Variabeldeklaration
		PriorityQueue<E> edgeQueue = new PriorityQueue<E>(11,
				new CompKruskalEdge<E>()); // 11 som startstorlek �r standard
											// f�r Java.
		List<E>[] cc = (LinkedList<E>[]) new LinkedList[noOfNodes];
		List<E> smallList, largeList, sourceList, destList;
		E currentEdge;
		int minTreeSize = 0, minTreeIndex = 0;

		edgeQueue.addAll(edgeList); // L�gger till alla b�gar i prioritetsk�n
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

				for (E e : smallList) { // L�gger till och pekar om
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
