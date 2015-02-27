import java.util.Comparator;
import java.util.LinkedList;

/**
 * 
 * @author Erik
 *
 */
public class CompKruskalEdge<E extends Edge>  implements Comparator{
	//Eller extends BusEdge implements Comparable
	
	public int getStartIndex(E e, LinkedList<E>[] list){ //TODO ska man ha double linked istället?
		return 0;
	}
	public int getDestIndex(E e, LinkedList<E>[] list){
		return 0;
	}
	
	@Override
	public int compare(Object o1, Object o2) {
		if(o1 instanceof BusEdge && o2 instanceof BusEdge){
			BusEdge BE1 = (BusEdge) o1;
			BusEdge BE2 = (BusEdge) o2;
			if(BE1.getWeight()==BE2.getWeight())
				return 0;
			else if(BE1.getWeight()<BE2.getWeight())
				return -1;
			else 
				return 1;			
		}
		else
			return 0;
	}
	
	

}
