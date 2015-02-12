import datastructures.BinarySearchTree;

//import datastructures.BinarySearchTree.Entry;
import testSortCol.CollectionWithGet;
/**
 * A splay tree class. 
 * @author Erik �hrn
 * @version 0.1_dev
 */
public class SplayTree<E extends Comparable<? super E>> extends
BinarySearchTree<E> implements CollectionWithGet<E>{

	@Override
	public E get(E e) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	// kopierat fr�n AVL_Tree
	

    /* Rotera 1 steg i h�gervarv, dvs 
              x'                 y'
             / \                / \
            y'  C   -->        A   x'
           / \                    / \  
          A   B                  B   C
    */
  private void rotateRight( Entry x ) {
       Entry   y = x.left;
       E    temp = x.element;
       x.element = y.element;
       y.element = temp;
       x.left    = y.left;
       if ( x.left != null )
	    x.left.parent   = x;
       y.left    = y.right;
       y.right   = x.right;
       if ( y.right != null )
	    y.right.parent  = y;
       x.right   = y;

   } //   rotateRight
  
    /* Rotera 1 steg i v�nstervarv, dvs 
              x'                 y'
             / \                / \
            A   y'  -->        x'  C
               / \            / \  
              B   C          A   B   
    */
  private void left( Entry x ) {
       Entry  y  = x.right;
       E temp    = x.element;
       x.element = y.element;
       y.element = temp;
       x.right   = y.right;
       if ( x.right != null )
	    x.right.parent  = x;
       y.right   = y.left;
       y.left    = x.left;
       if ( y.left != null )
	    y.left.parent   = y;
       x.left    = y;
   } //   rotateLeft
  
 /** zigZigRight
  *     	z						x
  *        / \	    			   / \
  *       y   D            till	  A   y
  *      / \						 / \
  *     x   C						B   z
  *    / \                 			   / \
  *   A   B							  C   D
  * @param x
  */

private void zigZigRight(Entry x){
	Entry z = x.parent.parent,
			y = x.parent,
			temp;
	E e = x.element;
	x.element = z.element;
	z.element = e;

	z.right=x.right;	//D
	x.right = y.left; //B
	temp = z.left; //A
	z.left=y;		
	y.right = x.left;	//C
	x.left = temp;	//A
	y.left=x;
	z.right.parent = z;
	y.right.parent = y;
	x.right.parent = x;
	x.left.parent = x;	
	
}

/* Rotera 2 steg i v�nstervarv, dvs 
               x'                  z'
              / \                /   \
             A   y'   -->       x'    y'
                / \            / \   / \
               z   D          A   B C   D
              / \  
             B   C  
*/
private void doubleRotateLeft( Entry x ) {
Entry  y  = x.right,
z  = x.right.left;
E      e  = x.element;
x.element = z.element;
z.element = e;
y.left    = z.right;
if ( y.left != null )
y.left.parent = y;
z.right   = z.left;
z.left    = x.left;
if ( z.left != null )
z.left.parent = z;
x.left    = z;
z.parent  = x;

} //  doubleRotateLeft


  /* Rotera 2 steg i h�gervarv, dvs 
            x'                  z'
           / \                /   \
          y'  D   -->        y'    x'
         / \                / \   / \
        A   z'             A   B C   D
           / \  
          B   C  
  */
private void doubleRotateRight( Entry x ) {
     Entry   y = x.left,
	        z = x.left.right;
     E       e = x.element;
     x.element = z.element;
     z.element = e;
     y.right   = z.left;
     if ( y.right != null )
	    y.right.parent = y;
     z.left    = z.right;
     z.right   = x.right;
     if ( z.right != null )
	    z.right.parent = z;
     x.right   = z;
     z.parent  = x;
 }  //  doubleRotateRight


/** zig
 *      y					x
 *     / \				   / \
 *    x   C		till	  A   y
 *   / \					 / \
 *  A   B					B   C
 *  
 * @param x
 */

private void zigRight(Entry x){
	
	    Entry   y = x.parent,
		        w = x.right	;
	    E       e = y.element;
	    y.element = x.element;	//byter plats p� elementen i x och y f�r att beh�lla kopplingen till det ovan.
	    x.element = e;
	    y.right = w;
	    x.right = x.left;
	    x.left = y.left;
	    if ( x.left != null )
		    x.left.parent = x;
	    y.left=x;
	    w.parent=y;
	    
} //TODO borde st�mma, men testa.












 /* Rotera 2 steg i v�nstervarv, dvs 
            x'                  z'
           / \                /   \
          A   y'   -->       x'    y'
             / \            / \   / \
            z   D          A   B C   D
           / \  
          B   C  
  */
 private void zigzag( Entry x ) { //TODO kanske...
     Entry  y  = x.right,
	z  = x.right.left;
     E      e  = x.element;
     x.element = z.element;
     z.element = e;
     y.left    = z.right;
     if ( y.left != null )
	    y.left.parent = y;
     z.right   = z.left;
     z.left    = x.left;
     if ( z.left != null )
	    z.left.parent = z;
     x.left    = z;
     z.parent  = x;
 } //  doubleRotateLeft
   








}
