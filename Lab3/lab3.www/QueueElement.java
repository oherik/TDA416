
public class QueueElement {
	private NodeObject to;
	private boolean known;
	private double cost;
	private NodeObject path;
	
	public QueueElement(NodeObject to){
		this.to = to;
	}
	
	/*
	 * Behöver en som innehåller  to, cost och path
	 */
	public QueueElement(NodeObject to, double cost, NodeObject path){
		this.to =to;
		this.cost = cost;
		this.path = path;
	}
}
