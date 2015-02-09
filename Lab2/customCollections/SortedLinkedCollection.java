public class SortedLinkedCollection implements LinkedCollection {

	
	
	/*
	 * göra en subklass av LinkedCollection 
	 * och skriva över add-metoden samt lägga till get-metoden. 
	 * Notera att elementen skall ligga med det minsta elementet 
	 * först och största elementet sist.
	 */

	
    /**
     * Adds an element to the collection.
     * The element added last will be the first element 
     * given by the iterator and the first element 
     * added will be the last given by the iterator.
     * 
     * @param element the object to add into the list
     * @return true if the object has been added to the list.
     * @throws NullPointerException if parameter <tt>element<tt> is null. 
     */
	@override
    public boolean add( E element ) {
        if ( element == null )
	    throw new NullPointerException();
	else {
	    head = new Entry( element, head );
	    return true;
	}
    } // add



}