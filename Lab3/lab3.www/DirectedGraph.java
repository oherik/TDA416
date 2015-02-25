import java.util.*;
//Efterf�ljarlista?


public class DirectedGraph<E extends Edge> {
	private Map<Integer, LinkedList<E>> nodeMap;
	//private LinkedList<E>[] nodeList;

	public DirectedGraph(int noOfNodes) {
		nodeMap = new HashMap<Integer, LinkedList<E>>();	
		//nodeList = new LinkedList[noOfNodes];
		//for(int i = 0; i<noOfNodes; i++)
			//nodeList[i] = new LinkedList<E>();
		
		
		/*
		Revelation! NodeTable.java g�r om alla nodnamn till heltal. �r det s� att de �r fr�n 0-n�nting? I s� fall blir det mycket snabbare, och smidigare med ett f�lt eller en ArrayList.
		
		
		*/
	}

	public void addEdge(E e) {
		int source = e.getSource();
	/*	LinkedList<E> list;
		for(int i = 0; !nodeList[i].isEmpty(); i++){
			list = nodeList[i];
			if(list.get(0).getSource()==e.getSource()
				list.add(e);
			}
		}
		*/
	nodeMap.putIfAbsent(source, new LinkedList<E>());
	nodeMap.get(source).add(e);			
	}

	public Iterator<E> shortestPath(int from, int to) {
		return null;
	}
		
	public Iterator<E> minimumSpanningTree() {
		return null;
	}

}
  
