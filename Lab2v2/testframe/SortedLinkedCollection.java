//Grupp 7 - Erik Öhrn & Paula Eriksson Imable

import testSortCol.CollectionWithGet;
import datastructures.LinkedCollection;

/**
 * An extension to LinkedCollection which also sorts the elements. Additionally
 * it has get function which enables it to search for an element in the list.
 * 
 * @author Erik Öhrn & Paula Eriksson Imable
 * @version 0.5
 * @param <E>
 *            E needs to extend Comparable
 */

public class SortedLinkedCollection<E extends Comparable<? super E>> extends
		LinkedCollection<E> implements CollectionWithGet<E> {

	public SortedLinkedCollection() {
		super();
	}

	/**
	 * Finds the first occurrence of an element in the list that is equal to the
	 * argument e with respect to its natural order. I.e. e.compateTo(element)
	 * is 0. It compares the element in the argument with the one in the entry
	 * and goes through all the entries until the element is found.
	 * 
	 * @param e
	 *            The element which is to be found
	 * @throws NullPointerException
	 *             if the argument is null
	 * @return The first element in the list that satisfies
	 *         e.compareTo(entry.element)==0. Returns null if none is found.
	 */
	@Override
	public E get(E e) {
		if (e == null)
			throw new NullPointerException();
		int compare;
		for (E entry : this) {
			compare = e.compareTo(entry);
			if (compare < 0)
				return null; // Lika bra att bryta när compare hamnar under 0
								// eftersom listan nu är ordnad
			if (compare == 0)
				return entry; // Rätt element hittat, returnera det
		}
		return null; // Inget element hittat, eller listan är tom.
	} // get

	/**
	 * Adds a new element to the list, in a sorted order according to compareTo.
	 * 
	 * @param element
	 *            The element which is to be added
	 * @throws NullPointerException
	 *             if element is null
	 * @return true if the element has been added
	 */
	@Override
	public boolean add(E element) {
		if (element == null)
			throw new NullPointerException();
		if (head == null || element.compareTo(head.element) < 0)
			head = new Entry(element, head); //I fallet då head är null kommer head.next också bli null.
		else {
			Entry entry = head;
			while (entry.next != null
					&& element.compareTo(entry.next.element) >= 0) {
				entry = entry.next;
			}
			entry.next = new Entry(element, entry.next);
		}
		return true;
	} // add
}