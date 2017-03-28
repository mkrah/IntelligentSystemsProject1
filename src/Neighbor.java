
/**
 * This class represents the neighbor in the tree.
 * @author Cody Perry (cp5337), Michael Lah (mxl8113)
 *
 */
public class Neighbor {
	// Private class members
	private Node nodeReference;
	private int pathCost;

	/**
	 * Construct a new Neighbor class.
	 * @param n The node this neighbor points to
	 * @param cost the cost of traversing the edge.
	 */
	public Neighbor(Node n, int cost){
	    nodeReference = n;
	    pathCost = cost;
    }


    /*
    Getters/setters
     */
    public Node getNodeReference(){
	    return this.nodeReference;
    }
    public int getPathCost(){
        return this.pathCost;
    }
}
