//Grupp 7 - Erik �hrn & Paula Eriksson Imable

import java.util.Comparator;

/**
 * En klass som j�mf�r tv� b�gar f�r Kruskals algoritm
 * 
 * @author Erik �hrn & Paula Eriksson Imable
 *
 */

public class CompKruskalEdge<E extends Edge> implements Comparator<E> {

	/**
	 * Compares two edges
	 * 
	 * @param e1
	 *            Instans av en klass som ut�kar Edge
	 * @param e2
	 *            Instans av en klass som ut�kar Edge
	 * @returns 0 om elementen har samma vikt, ett ta mindre �n noll om e1:s
	 *          vikt �r minst eller ett tal st�rre �n noll om e2:s vikt �r
	 *          minst.
	 * @throws NullPointerException
	 *             om n�got av elementen �r null
	 */
	@Override
	public int compare(E e1, E e2) {
		if (e1 == null || e2 == null)
			throw new NullPointerException("Cannot compare null elements.");
		return Double.compare(e1.getWeight(), e2.getWeight());
	}
}
