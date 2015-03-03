import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class QueueElement<E extends Edge> implements Comparable<QueueElement>{
	private int node;
	private double cost;
	ArrayList<E> path;
	int path2;
	//private List<Integer> PathList;
	
	public QueueElement(int node){
		this.node = node;
	}
	
	/*
	 * Behöver en som innehåller  to, cost och path
	 */

	public QueueElement(int node, double cost, ArrayList<E> path){
		this.node = node;
		
		this.cost = cost;
		this.path = path;
		
	}
	public QueueElement(int node, double cost, int path){
		this.node = node;
		
		this.cost = cost;
		this.path2 = path;
		
	}
	public int getPath2(){
		return path2;
	}
	
	

	

	public double getWeight(){
		return cost;
	}
	
	public int getNode(){
		return node;
	}
	/*
	public int getPath(){
		return path;
		
	}*/
	
	
	public ArrayList<E> getPath(){
		return path;
		
	}
	
	@Override
	public int compareTo(QueueElement QE) {
		if (QE == null)
			throw new NullPointerException("Cannot compare null elements.");
		else {
			if (this.getWeight() == QE.getWeight())
				return 0;
			else if (this.getWeight() < QE.getWeight())
				return -1;
			else
				return 1;
		}
	
	}
	
	@Override
	public String toString(){
		return (node + ", " + cost +", " + path);
		
		
	}
}
