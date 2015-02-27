//Grupp 7 - Erik Öhrn & Paula Eriksson Imable

import java.util.Comparator;

/**
 * A class which compares two edges.
 * 
 * @author Erik
 *
 */

/*
 * Okej Pow jag har det. Det den här klassen ska göra är att jämföra två bågar.
 * Det är nästan allt tror jag. Jag trodde att den skulle vara en slags edge,
 * som extends BusEdge, men det blev krångligt. Detta verkar funka. Sen
 * implementerar prioritetskön i DirectedGraph denna, vilket gör att den kan
 * sortera BusEdges. Blev tvungen att skriva implements Comparator<E> istället
 * för bra implements Comparator för att det skulle funka.
 */
public class CompKruskalEdge<E extends Edge> implements Comparator<E> {

	/**
	 * Compares two edges
	 * 
	 * @param e1
	 *            A class which extends Edge
	 * @param e2
	 *            A class which extends Edge
	 * @returns 0 if the elements have the same weight, -1 if e1 is smaller than
	 *          e2 or 1 if e1 is larger than e2
	 * @throws NullPointerException
	 *             if either element is null.
	 */
	@Override
	public int compare(E e1, E e2) {
		if (e1 == null || e2 == null)
			throw new NullPointerException("Cannot compare null elements.");
		else {
			if (e1.getWeight() == e2.getWeight())
				return 0;
			else if (e1.getWeight() < e2.getWeight())
				return -1;
			else
				return 1;
		}
	}
}
