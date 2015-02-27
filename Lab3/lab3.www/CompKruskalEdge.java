import java.util.Comparator;
import java.util.LinkedList;

/**
 * 
 * @author Erik
 *
 */
public class CompKruskalEdge<E extends Edge> implements Comparator<E>{

	//Eller implements Comparator
	
	public int getStartIndex(E e, LinkedList<E>[] list){ //TODO ska man ha double linked istället?
		return 0;
	}
	public int getDestIndex(E e, LinkedList<E>[] list){
		return 0;
	}
	
	@Override
	public int compare(E e1, E e2) {
			if(e1.getWeight()==e2.getWeight())
				return 0;
			else if(e1.getWeight()<e2.getWeight())
				return -1;
			else 
				return 1;			

	}/*
	@Override
	public int compareTo(E edge) {
		if(this == edge)
			return 0;
		if(this.getWeight()==edge.getWeight())
			return 0;
		else if(this.getWeight()<edge.getWeight())
			return -1;
		else 
			return 1;
		
		// TODO Auto-generated method stub
	}*/
	
	

}
