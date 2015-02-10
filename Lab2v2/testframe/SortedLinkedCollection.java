/**
 * A subclass to LinkedCollection. It is in all appearances identical, 
 * except that it also has a "get" method.
 * 
 * @author Erik Öhrn
 * @version 0.1
 */

import testSortCol.CollectionWithGet;
import datastructures.LinkedCollection;

public class SortedLinkedCollection<E extends Comparable<? super E>> extends
		LinkedCollection<E> implements CollectionWithGet<E> {

	/**
	 * Initializer function for getRecursive.
	 * 
	 * @param e
	 *            The element which is to be found
	 * @throws NullPointerException
	 *             if the argument is null
	 * @return The first element in the list that satisfies e.compareTo(e')==0.
	 *         Returns null if none is found.
	 */
	@Override
	public E get(E e) {
		if (e == null)
			throw new NullPointerException();
		return compareToEntry(e, head);
	}

	/**
	 * Find the first occurrence of an element in the list that is equal to the
	 * argument e with respect to its natural order. I.e. e.compateTo(element)
	 * is 0. It compares the element in the argument with the one in the entry
	 * and goes through all the entries until the element is found.
	 * 
	 * @param e
	 *            The element which is to be found
	 * @param entry
	 *            The current entry in which to compare e to the element in the
	 *            entry.
	 * @return	The first element in the list that satisfies e.compareTo(e')==0.
	 *         Returns null if none is found.
	 */
	private E compareToEntry(E e, Entry entry) {
		if (entry == null)
			return null; // Element e not found
		else if (e.compareTo(entry.element) == 0)
			return entry.element;
		return compareToEntry(e, entry.next);
	}
	
	@Override
	public boolean add( E element ) {
        if ( element == null )
	    throw new NullPointerException();
	else {
	  
		
		
		
	}
    } // add
}
