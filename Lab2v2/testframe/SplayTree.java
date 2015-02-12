import datastructures.BinarySearchTree;

//import datastructures.BinarySearchTree.Entry;
import testSortCol.CollectionWithGet;
/**
 * A splay tree class. 
 * @author Erik �hrn
 * @version 0.3_dev
 */
public class SplayTree<E extends Comparable<? super E>> extends
BinarySearchTree<E> implements CollectionWithGet<E>{

	public SplayTree(){
		super();
	}
	
	@Override
	public E get(E e) {
		Entry result = find(e, root);
		if(result==null)
			return null;
		else{
			splay(result);	
			return(root.element);		//TODO fult?
		} 		
	}
	

	private void splay(Entry x){	//TODO alt skicka x.parent.parent eller x.parent och l�ta get ha hand om loopen.
		while(x != root){		//TODO �r det r�tt att loopa hela v�gen?
			if(x.parent.parent != null){
				if(x.parent.right == x && x.parent.parent.right == x.parent)
					zagZag(x);
				else if(x.parent.left == x && x.parent.parent.left == x.parent)
					zigZig(x);
				else if(x.parent.left == x && x.parent.parent.right == x.parent)
					zagZig(x); 
				else
					zigZag(x);

				x = x.parent.parent;
			}else{
				if(x.parent.right ==  x)
					zag(x);
				else
					zig(x);

				x = x.parent;
			}
		}
	}
	



/** zig
 *      y					x
 *     / \				   / \
 *    x   C		till	  A   y
 *   / \					 / \
 *  A   B					B   C
 *  
 * @param x
 */

private void zig(Entry x){

	Entry   y = x.parent;
	E       e = y.element;
	y.element = x.element;	//byter plats p� elementen i x och y f�r att beh�lla kopplingen till det ovan.
	x.element = e;

	y.left  = x.left;	//A
	x.left  = x.right;	//B
	x.right = y.right;	//C
	y.right = x;		//y (v�rdet)

	if ( x.right != null )
		x.right.parent = x;
	if ( y.left != null )
		y.left.parent = y;

} //TODO borde st�mma, men testa.

/** zag
		 y                 x
		/ \               / \    
	   A   x             y   C
		  / \       ->  / \ 
   	     B   C         A   B 
		 	
		   
*/    

private void zag(Entry x){
	Entry   y = x.parent;
	E       e = x.element;
	x.element = y.element;	//byt plats p� x och y;
	y.element = e;
	
	y.right = x.right;	//C
	x.right = x.left;	//B
	x.left  = y.left;	//A
	y.left  = x;		//y (v�rdet)

	if ( x.left != null )
		x.left.parent = x;
	if ( y.right != null )
		y.right.parent = y;
}


/** zigZig
*      	  z						    x
*        / \	    			   / \
*       y   D            till	  A   y
*      / \							 / \
*     x   C							B   z
*    / \                           	   / \
*   A   B						  	  C   D
* @param x
*/

private void zigZig(Entry x){
	Entry z = x.parent.parent,
		  y = x.parent;
	E 	  e = x.element;
	
	x.element = z.element;	//byt plats p� x och z
	z.element = e;
	
	z.left = x.left;	//A	
	x.left = y.right;	//C
	y.right = x;		//z  (v�rdet)
	y.left = x.right;	//B
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
	
}

/** zagZag
*    z    	          x	
*   / \              / \	  
*  A   y            y   D
*	  / \   till   / \	
*	 B   x        z   C	
*       / \      / \  
*	   C   D    A   B	
 * @param x
 */

private void zagZag(Entry x){
	Entry z = x.parent.parent,
		 y = x.parent;
	E e = x.element;
	x.element = z.element;	//byt plats p� x och z
	z.element = e;

	z.right=x.right;	//D
	x.right = y.left; 	//B
	y.right = x.left;	//C
	y.left=x;			//z (v�rdet)
	x.left = z.left;	//A
	z.left=y;			//y
	
	if ( z.right != null )
		z.right.parent = z;
	if ( y.right != null )
		y.right.parent = y;
	if ( x.right != null )
		x.right.parent = x;
	if ( x.left != null )
		x.left.parent = x;	
	
}

/** zigZag
 * 
 * 			z					x
 * 		   / \				   / \
 *        y   D 	till	  y   z
 *       / \				 / \ / \
 *      A   x               A  B C  D
 *         / \
 *        B   C
 * 
 * @param x
 */
private void zigZag(Entry x){
	Entry	y = x.parent,
			z = x.parent.parent;
	E	 	e = z.element;
	
	z.element = x.element;	//byter plats p� x och z;
	x.element = e;
	
	y.right  = x.left;	//B
	x.left	 = x.right;	//C
	x.right	 = z.right;	//D
	z.right	 = x;		//z (v�rdet)
	
	x.parent = z;
	if ( y.right != null )
	    y.right.parent = y;
	if ( x.right != null )
		   x.right.parent = x;
	
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
 */

private void zagZig(Entry x){
	Entry	y = x.parent,
			z = x.parent.parent;
	E	 	e = z.element;
	
	z.element = x.element;	//byter plats p� x och z;
	x.element = e;	
	
	y.left 	= x.right;	//C
	x.right = x.left;	//B
	x.left 	= z.left;	//A
	z.left	= x;		//z (v�rdet)
	
	x.parent = z;
	if ( y.left != null )
	    y.left.parent = y;
	if ( x.left != null )
		x.left.parent = x;
	
	
	
	
}

/* Referenser: 
 * http://en.wikipedia.org/wiki/Splay_tree
 * AVL_Tree.java
 * http://lcm.csa.iisc.ernet.in/dsa/node93.html
 * splay.lock.pdf
 */


//	Nedanst�ende kod �r fr�n AVL_Tree.java
/*-------------------------------------------------------------------*/



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

/*---------------------------------------------------*/

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
