//Grupp 7 - Erik Öhrn & Paula Eriksson Imable

import java.util.Comparator;

/**
 * En klass som jämför två bågar för Kruskals algoritm
 * 
 * @author Erik Öhrn & Paula Eriksson Imable
 *
 */

public class CompKruskalEdge<E extends Edge> implements Comparator<E> {

	/**
	 * Compares two edges
	 * 
	 * @param e1
	 *            Instans av en klass som utökar Edge
	 * @param e2
	 *            Instans av en klass som utökar Edge
	 * @returns 0 om elementen har samma vikt, ett ta mindre än noll om e1:s
	 *          vikt är minst eller ett tal större än noll om e2:s vikt är
	 *          minst.
	 * @throws NullPointerException
	 *             om något av elementen är null
	 */
	@Override
	public int compare(E e1, E e2) {
		if (e1 == null || e2 == null)
			throw new NullPointerException("Cannot compare null elements.");
		return Double.compare(e1.getWeight(), e2.getWeight());
	}
}
