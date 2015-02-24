/**
 *lägg (startnod, 0, tom väg) i en p-kö
	while kön inte är tom
		(nod, cost, path) = första elementet i p-kön
		if nod ej är besökt
			if nod är slutpunkt returnera path
			else
				markera nod besökt
				for every v on EL(nod)
					if v ej är besökt
						lägg in nytt köelement för v i p-kön
 */
import java.util.*;

public class DirectedGraph<E extends Edge> {


	public DirectedGraph(int noOfNodes) {
		;
	}

	public void addEdge(E e) {
		;
	}

	public Iterator<E> shortestPath(int from, int to) {
		return null;
	}
		
	public Iterator<E> minimumSpanningTree() {
		return null;
	}

}
  
