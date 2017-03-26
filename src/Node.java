import java.util.ArrayList;

/**
 * This class represents the node of a tree.
 * @author Cody Perry (cp5337), Michael Lah (mxl8113)
 *
 */
public class Node {
	// Private class members
	private String nodeName;
	private ArrayList<Neighbor> neighborList;
	private int heuristicValue;
	public boolean visited;
	private Node previousNode;
	private int pathCost;
	
	/*
	 * Construct node object.
	 * @param String name
	 * @return none
	 */
	public Node(String name, int heuristicValue) {
		this.heuristicValue = heuristicValue;
		nodeName = name;
		neighborList = new ArrayList<>();
		this.visited = false;
	}
	
	/*
	 * Method to add neighbors to the neighborList.
	 * @param none
	 * @return none
	 */
	public void addNeighbor(Node n, int cost) {
	    Neighbor neighbor = new Neighbor(n, cost);
	    neighborList.add(neighbor);

	}
	public Node getPreviousNode(){
	    return this.previousNode;
    }
    public void setPreviousNode(Node n){
	    this.previousNode = n;
    }
    public ArrayList<Neighbor> getNeighborList(){
        return this.neighborList;
    }
    public String getNodeName(){
        return this.nodeName;
    }
    public void setPathCost(int p){
        this.pathCost = p;
    }
    public int getPathCost(){
        return this.pathCost;
    }
}
