import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;
import java.util.Deque;

/**
 * This is the main class for running the A* algorithm.
 * It builds the tree, runs the algorithm, and then prints the shortest path
 * and shortest cost.
 * @author Cody Perry (cp5337), Michael Lah (mxl8113)
 *
 */
public class A_Star {

	/**
	 * This method runs the A* algorithm on the tree
	 * and returns a String array like this: [shortest_path, path_cost]
	 * Then main prints these values and finishes execution.
	 * @param startNode the node to start the search at
	 * @param goalNode the node to search for.
	 */
	private static void a_star_algorithm(Node startNode, Node goalNode) {
		ArrayList<Node> generatedNodes = new ArrayList<Node>();
		ArrayList<Node> expandedNodes = new ArrayList<Node>();
		
		generatedNodes.add(startNode);
		
		while ( !generatedNodes.isEmpty() ) {
			// By default, select head node
			Node currNode = generatedNodes.get(0);
			
			// Otherwise, find the lowest cost node to expand
			if ( generatedNodes.size() > 1 ) {
				int smallestCost = Integer.MAX_VALUE;
				for ( Node node : generatedNodes ) {
					if ( node.getPathCost() + node.getHeuristicValue() < smallestCost ) {
						smallestCost = node.getPathCost() + node.getHeuristicValue();
						currNode = node;
					}
				}
			}
			
			// Found solution
			if ( currNode == goalNode ) {
				expandedNodes.add(currNode);
				break;
			}
			
			for ( Neighbor neighbor : currNode.getNeighborList() ) {
				Node nodeToAdd = neighbor.getNodeReference();
				
				// If it's already generated
				if ( generatedNodes.contains(nodeToAdd) ) {
					for ( Node node : generatedNodes ) {
						if ( node == nodeToAdd ) {
							int currValue = nodeToAdd.getHeuristicValue() + nodeToAdd.getPathCost();
							int newValue = currNode.getPathCost() + nodeToAdd.getHeuristicValue() + neighbor.getPathCost();
							
							if ( newValue < currValue ) {
								node.setPathCost(currNode.getPathCost() + neighbor.getPathCost());
								node.setPreviousNode(currNode);
							}
						}
					}	
				} else if ( expandedNodes.contains(nodeToAdd) ) {
					// If node is already expanded
					for ( Node node : expandedNodes ) {
						int currValue = nodeToAdd.getHeuristicValue() + nodeToAdd.getPathCost();
						int newValue = currNode.getPathCost() + nodeToAdd.getHeuristicValue() + neighbor.getPathCost();

						if ( newValue < currValue ) {
							node.setPathCost(currNode.getPathCost() + neighbor.getPathCost());
							node.setPreviousNode(currNode);
							ArrayDeque<Node> updateQueue = new ArrayDeque<Node>();
							for ( Neighbor neighborNode : nodeToAdd.getNeighborList() ) {
								if ( expandedNodes.contains(neighborNode) ) {
									updateQueue.addLast(neighborNode.getNodeReference());
								}
							}

							while ( updateQueue.size() > 0 ) {
								Node nodeToUpdate = updateQueue.removeFirst();
								nodeToUpdate.setPathCost(node.getPathCost() + neighbor.getPathCost());
								for ( Neighbor updatedNeighbors : nodeToUpdate.getNeighborList() ) {
									if ( expandedNodes.contains(updatedNeighbors) ) {
										updateQueue.addLast(updatedNeighbors.getNodeReference());
									}
								}
							}
						}
					}
				} else { 
					// Node is neither generated or expanded, simply add it
					nodeToAdd.setPathCost(currNode.getPathCost() + neighbor.getPathCost());
					nodeToAdd.setPreviousNode(currNode);
					generatedNodes.add(nodeToAdd);
				}
			}
			
			expandedNodes.add(currNode);
			generatedNodes.remove(currNode);
		}

		int pathCost = goalNode.getPathCost();
        Stack<Node> path = new Stack<>();
        Node currentNode = goalNode;

        //Traverse from end node to start node, keeping the path in "path"
        while(true){
            if(currentNode.getPreviousNode() == null){
                path.add(currentNode);
                break;
            }
            path.add(currentNode);
            currentNode = currentNode.getPreviousNode();
        }
        
        String pathString = "";
        String sep = "";
        while ( !path.isEmpty() ) {
            Node currPathNode = path.pop();
            pathString += sep + currPathNode.getNodeName();
            sep = " -> ";
        }
        System.out.println("Path generated: ");
        System.out.println(pathString);
		System.out.println("Total path cost: " + pathCost);

	}
	
	/**
	 * This is the main method which contains the execution flow.
	 * See class comments for actual workflow.
	 * @param args command line args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		// Load input
		Scanner reader = new Scanner(System.in);
		System.out.println("Enter start node: ");
		String startNodeName = reader.next();
		System.out.println("Enter goal node: ");
		String goalNodeName = reader.next();

		// Load input
		Scanner scanner = new Scanner(new File(args[0]));
		boolean neighbors = false;
		HashMap<String, Node> nodes = new HashMap<>();

		//Read through file and generate map.
		while(scanner.hasNext()){
			String s = scanner.next();
			s = s.trim();
			if(s .equals("NEIGHBORS")){
				neighbors = true;
			}
			else if(neighbors){
				//Reading in list of neighbors
				String[] splt = s.split(":");
				String currNodeName = splt[0];

				String[] neighborValues = splt[1].split(",");
				for(int i = 0; i< neighborValues.length; i+=2){
					String neighborName = neighborValues[i];
					int pathCost = Integer.parseInt(neighborValues[i+1]);

					// Add Neighbor to existing Node.
					nodes.get(currNodeName).addNeighbor(nodes.get(neighborName), pathCost);

				}

			}
			else{
				// Reading in node definitions
				String[] splt = s.split(",");
				Node n = new Node(splt[0],Integer.parseInt(splt[1]));
				nodes.put( splt[0],n);
			}
			//System.out.println(s);

		}
		// Done building nodes. They're all in "nodes" hashMap.


		// Run algorithm
		Node startNode = nodes.get(startNodeName);
		if(startNode == null){
			System.err.println("Please enter a valid start node name");
			System.exit(-1);
		}

		Node goalNode = nodes.get(goalNodeName);
		if(goalNode == null){
			System.err.println("Please enter a valid goal node name");
			System.exit(-1);
		}
		
		// Close scanners
		reader.close();
		scanner.close();
		
		// Run algorithm
		a_star_algorithm(startNode, goalNode);

	}

}
