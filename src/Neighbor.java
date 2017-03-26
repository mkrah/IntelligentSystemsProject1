
/**
 * This class represents the neighbor in the tree.
 * @author Cody Perry (cp5337), Michael Lah (mxl8113)
 *
 */
public class Neighbor {
	// Private class members
	private Node nodeReference;
	private int pathCost;

	public Neighbor(Node n, int cost){
	    nodeReference = n;
	    pathCost = cost;
    }

    public Node getNodeReference(){
	    return this.nodeReference;
    }
    public int getPathCost(){
        return this.pathCost;
    }
}
