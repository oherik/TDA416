//Grupp 7 - Erik Öhrn och Paula Eriksson Imable
import datastructures.BinarySearchTree;
import testSortCol.CollectionWithGet;
/**
 * A splay tree class.
 * References:
 * http://en.wikipedia.org/wiki/Splay_tree
 * AVL_Tree.java
 * http://lcm.csa.iisc.ernet.in/dsa/node93.html
 * splay.lock.pdf
 * https://www.cs.usfca.edu/~galles/visualization/SplayTree.html
 * @author Erik Öhrn & Paula Eriksson Imable
 * @version 0.5
 */
public class SplayTree<E extends Comparable<? super E>> extends
BinarySearchTree<E> implements CollectionWithGet<E>{
	public SplayTree(){
		super();
	}
	@Override
	public E get(E e) {
		Entry result = find(e, root);
		if(result == null)
			return null;
		else
			return(result.element);
	}
	/** Searches for an element in the splay tree.
	 * @param elem The element that is looked for
	 * @param t Where to start looking
	 * @return the element if it's found, null otherwise
	 */
	@Override
	protected Entry find(E elem, Entry t){
		if ( t == null )
			return null;
		else
			return splayFind(elem, t, t);
	}
	/**
	 * Searches for an element in the tree, and splays it, i.e. moves it to the top, if it is found.
	 * If it's not found, the last checked element is splayed to the top.
	 * @param elem The element that is looked for
	 * @param t Where to start looking
	 * @param lastChecked The last node that was checked
	 * @return	The element if it's found, otherwise null (splays on both these occasions)
	 */
	private Entry splayFind(E elem, Entry t, Entry lastChecked){
		if( lastChecked == null )
			return null;
		if ( t == null ){
			splay(lastChecked);
			return null;
		}
		else {
			int jfr = elem.compareTo( t.element );
			if ( jfr < 0 )
				return splayFind( elem, t.left, t );
			else if ( jfr > 0 )
				return splayFind( elem, t.right, t );
			else
				return splay(t);
		}
	}
	/**
	 * Splays a selected entry to the top. In order to do this it uses different rotation
	 * operations or splay steps: zig, zag and combinations of these. Once the entry reaches
	 * the top the splaying is completed.
	 * @param x The entry which to splay to the top
	 * @return null if the entry is null, otherwise the entry splayed, now at the root position.
	 */
	private Entry splay(Entry x){
		Entry parent, grandparent;
		if (x == null)
			return null;
		while(x!= root){
			parent = x.parent;
			if(parent == root){
				if(root.right == x)
					x=zag(x);
				else
					x=zig(x);
			}//end if parent is root
			else{
				grandparent = parent.parent;
				if(parent.right == x){
					if(grandparent.right == parent)
						x=zagZag(x);
					else
						x=zigZag(x);
				}
				else{
					if(grandparent.left == parent)
						x=zigZig(x);
					else
						x=zagZig(x);
				}
			}//end else (i.e. if parent isn't root)
		}//end while
		return x;	//x is now root
	}
	
/** zig
 *      y					x
 *     / \				   / \
 *    x   C		->  	  A   y
 *   / \					 / \
 *  A   B					B   C
 *  
 * @param x the entry to splay on
 * @return the new position of x
 */

	private Entry zig(Entry x){
		Entry   y = x.parent;
		E       e = y.element;
		y.element = x.element;	//byter plats på elementen i x och y för att behålla kopplingen till det ovan.
		x.element = e;
	
		y.left  = x.left;	//A
		x.left  = x.right;	//B
		x.right = y.right;	//C
		y.right = x;		//y (värdet)
	
		if ( x.right != null )
			x.right.parent = x;
		if ( y.left != null )
			y.left.parent = y;
		return y;
	} 

/** zag
		 y                 x
		/ \               / \    
	   A   x             y   C
		  / \       ->  / \ 
   	     B   C         A   B 
		 			   
*@param x the entry to splay on
*@return the new position of x
*/    
	
	private Entry zag(Entry x){
		Entry   y = x.parent;
		E       e = x.element;
		x.element = y.element;	//byt plats på x och y;
		y.element = e;
		
		y.right = x.right;	//C
		x.right = x.left;	//B
		x.left  = y.left;	//A
		y.left  = x;		//y (värdet)
	
		if ( x.left != null )
			x.left.parent = x;
		if ( y.right != null )
			y.right.parent = y;
		return y;
	}


/** zigZig
*      	  z						    x
*        / \	    				   / \
*       y   D            -> 	  A   y
*      / \							 / \
*     x   C							B   z
*    / \                           	   / \
*   A   B						  	  C   D
* @param x the entry to splay on
* @return the new position of x
*/

	private Entry zigZig(Entry x){
		Entry z = x.parent.parent,
			  y = x.parent;
		E 	  e = x.element;
		
		x.element = z.element;	//byt plats på x och z
		z.element = e;
		
		z.left 	= x.left;	//A	
		x.left 	= y.right;	//C
		y.right = x;		//z  (värdet)
		y.left 	= x.right;	//B
		x.right = z.right;	//D
		z.right = y;		//y
		
		if ( x.left != null )
			x.left.parent = x;
		if ( x.right != null )
			x.right.parent = x;
		if ( y.left != null )
			y.left.parent = y;
		if ( z.left != null )
			z.left.parent = z;
		
		return z;
	}

/** zagZag
*    z    	          x	
*   / \              / \	  
*  A   y            y   D
*	  / \   ->     / \	
*	 B   x        z   C	
*       / \      / \  
*	   C   D    A   B	
 * @param x the entry to splay on
 * @return the new position of x
 */
	
	private Entry zagZag(Entry x){
		Entry z = x.parent.parent,
			  y = x.parent;
		E e 		= x.element;
		x.element 	= z.element;	//byt plats på x och z
		z.element 	= e;
	
		z.right	= x.right;	//D
		x.right = y.left; 	//B
		y.right = x.left;	//C
		y.left	= x;			//z (värdet)
		x.left 	= z.left;	//A
		z.left	= y;			//y
		
		if ( z.right != null )
			z.right.parent = z;
		if ( y.right != null )
			y.right.parent = y;
		if ( x.right != null )
			x.right.parent = x;
		if ( x.left != null )
			x.left.parent = x;	
		return z;
	}

/** zigZag
 * 
 * 			z					x
 * 		   / \				   / \
 *        y   D 	->  	  y   z
 *       / \				 / \ / \
 *      A   x               A  B C  D
 *         / \
 *        B   C
 * 
 * @param x the entry to splay on
 * @return the new position of x
 */
	private Entry zigZag(Entry x){
		Entry	y = x.parent,
				z = x.parent.parent;
		E	 	e = z.element;
		
		z.element = x.element;	//byter plats på x och z;
		x.element = e;
		
		y.right  = x.left;	//B
		x.left	 = x.right;	//C
		x.right	 = z.right;	//D
		z.right	 = x;		//z (värdet)
		
		x.parent = z;
		if ( y.right != null )
		    y.right.parent = y;
		if ( x.right != null )
			   x.right.parent = x;
		return z;	
	}


/** zagZig
 * 
 * 			z					 	x
 * 		   / \					   / \
 *        A   y					  z   y
 *           / \		till	 / \ / \
 *          x   D 				A  B C  D
 *         / \
 *        B  C  

 * 
 * @param x
 * @return the new position of x
 */

	private Entry zagZig(Entry x){
		Entry	y = x.parent,
				z = x.parent.parent;
		E	 	e = z.element;
		
		z.element = x.element;	//byter plats på x och z;
		x.element = e;	
		
		y.left 	= x.right;	//C
		x.right = x.left;	//B
		x.left 	= z.left;	//A
		z.left	= x;		//z (värdet)
		
		x.parent = z;
		if ( y.left != null )
		    y.left.parent = y;
		if ( x.left != null )
			x.left.parent = x;
		return z;	
	}

/* Referenser: 
 * http://en.wikipedia.org/wiki/Splay_tree
 * AVL_Tree.java
 * http://lcm.csa.iisc.ernet.in/dsa/node93.html
 * splay.lock.pdf
 * https://www.cs.usfca.edu/~galles/visualization/SplayTree.html
 */
}