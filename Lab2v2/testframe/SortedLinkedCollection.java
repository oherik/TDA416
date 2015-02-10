/**
 * A subclass to LinkedCollection. It is in all appearances identical, 
 * except that it also has a "get" method.
 * 
 * @author Erik Öhrn
 * @version 0.3
 */

import testSortCol.CollectionWithGet;
import datastructures.LinkedCollection;

public class SortedLinkedCollection<E extends Comparable<? super E>> extends
		LinkedCollection<E> implements CollectionWithGet<E> {

	public SortedLinkedCollection() {
		super();

	}

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
		return getRecursive(e, head);	
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
	 * @return The first element in the list that satisfies e.compareTo(e')==0.
	 *         Returns null if none is found.
	 */
	private E getRecursive(E e, Entry entry){
		if(entry==null)
			return null;
		else if (e.compareTo(entry.element) == 0)
			return entry.element;
		else if (e.compareTo(entry.element) > 0)  // since it's	sorted we can abort early
			return null;
		return getRecursive(e, entry.next);	
	}

	/**
	 * Adds a new element to the list, in a sorted order
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
			head = new Entry(element, null);
		else
			addSorted(element, head);
		return true;
	} // add

	/**
	 * Compares a given element to one in a given entry and adds it before if
	 * it's smaller according to compareTo.
	 * 
	 * @param element
	 *            The element which is to be added
	 * @param entry
	 *            The entry whose element it compares to
	 */
	private void addSorted(E element, Entry entry) {
		if (entry.next != null && element.compareTo(entry.element) < 0)
			addSorted(element, entry.next);
		else
			entry.next = new Entry(element, entry.next);
	}

}
