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
	
	/*
	 * Construct node object.
	 * @param String name
	 * @return none
	 */
	public Node(String name, int heuristicValue) {
		this.heuristicValue = heuristicValue;
		nodeName = name;
		neighborList = new ArrayList<>();
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
}
