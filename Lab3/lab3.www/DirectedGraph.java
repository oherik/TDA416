/**
 *l�gg (startnod, 0, tom v�g) i en p-k�
	while k�n inte �r tom
		(nod, cost, path) = f�rsta elementet i p-k�n
		if nod ej �r bes�kt
			if nod �r slutpunkt returnera path
			else
				markera nod bes�kt
				for every v on EL(nod)
					if v ej �r bes�kt
						l�gg in nytt k�element f�r v i p-k�n
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
  
