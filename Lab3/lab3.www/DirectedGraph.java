import java.util.*;
//Efterföljarlista?


public class DirectedGraph<E extends Edge> {
	private Map<Integer, LinkedList<E>> nodeMap;
	//private LinkedList<E>[] nodeList;

	public DirectedGraph(int noOfNodes) {
		nodeMap = new HashMap<Integer, LinkedList<E>>();	
		//nodeList = new LinkedList[noOfNodes];
		//for(int i = 0; i<noOfNodes; i++)
			//nodeList[i] = new LinkedList<E>();
		
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
  
