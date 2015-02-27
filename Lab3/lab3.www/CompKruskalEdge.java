import java.util.Comparator;
import java.util.LinkedList;

/**
 * 
 * @author Erik
 *
 */
public class CompKruskalEdge<E extends Edge> implements Comparator{
	//Eller extends BusEdge implements Comparable
	
	public int getStartIndex(E e, LinkedList<E>[] list){ //TODO ska man ha double linked istället?
		return 0;
	}
	public int getDestIndex(E e, LinkedList<E>[] list){
		return 0;
	}
	
	@Override
	public int compare(Object arg0, Object arg1) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	

}
