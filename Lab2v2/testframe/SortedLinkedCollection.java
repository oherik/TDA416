/**
 * A subclass to LinkedCollection. It is in all appearances identical, 
 * except that it also has a "get" method and the add method sorts it.
 *
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
	 * Find the first occurrence of an element in the list that is equal to the
	 * argument e with respect to its natural order. I.e. e.compateTo(element)
	 * is 0. It compares the element in the argument with the one in the entry
	 * and goes through all the entries until the element is found.
	 * 
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

			Entry entry = head;
			while(entry!=null){
				if(e.compareTo(entry.element) == 0)
					return entry.element;
				entry = entry.next;
			}
		
		return null;
				
			
	}//TODO: get ä paj

	

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
		else if (head == null || element.compareTo(head.element) < 0)
			head = new Entry(element, head);
		else{
			Entry entry = head;
			while(entry.next!=null && element.compareTo(entry.next.element) >= 0)
				entry = entry.next;				
			
			entry.next = new Entry(element, entry.next);
		}
		return true;
	} // add
}