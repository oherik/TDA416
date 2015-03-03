import java.util.ArrayList;
import java.util.PriorityQueue;


public class CompDijkstraPath<E extends Edge> implements Comparable<CompDijkstraPath>{
	private int node;
	private double cost;
	ArrayList<E> path;
	
	/*
	 * Behöver en som innehåller  to, cost och path
	 */

	public CompDijkstraPath(int node, double cost, ArrayList<E> path){
		this.node = node;
		this.cost = cost;
		this.path = path;	
	}
	
	public ArrayList<E> getPath(){
		return path;
	}
	
	public double getCost(){
		return cost;
	}
	
	public int getNode(){
		return node;
	}
	
	@Override
	public int compareTo(CompDijkstraPath dijkstra) {
		if (dijkstra == null)
			throw new NullPointerException("Cannot compare null elements.");
		else {
			if (this.getCost() == dijkstra.getCost())
				return 0;
			else if (this.getCost() < dijkstra.getCost())
				return -1;
			else
				return 1;
		}
	
	}
}