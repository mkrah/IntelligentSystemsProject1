import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

/**
 * This is the main class for running the backtracking algorithm.
 * It builds the tree, runs the algorithm, and then prints the shortest path
 * and shortest cost.
 * @author Cody Perry (cp5337), Michael Lah (mxl8113)
 *
 */
public class Backtracking {

	/*
	 * This method runs the backtracking algorithm on the tree
	 * and returns a String array like this: [shortest_path, path_cost]
	 * Then main prints these values and finishes execution.
	 * @param Tree structure
	 * @return String[]
	 */
	private static String[] backtracking_algorithm(Node startNode, Node goalNode) {

        //queue for BFS algorithm.
        //ArrayDeque<Node> queue = new ArrayDeque<>();
        Stack<Node> stack = new Stack<>();
        stack.push(startNode);
        //queue is ready, start looping.
        while (!stack.isEmpty()) {
            Node currentNode = stack.pop();
            //if(currentNode.getNodeName().equals(goalNodeName)){
            if (currentNode == goalNode) {
                //goal node found.
                int pathCost = currentNode.getPathCost();
                Stack<Node> path = new Stack<>();

                //Traverse from end node to start node, keeping the path in "path"
                while (true) {
                    if (currentNode.getPreviousNode() == null) {
                        path.add(currentNode);
                        break;
                    }
                    path.add(currentNode);
                    currentNode = currentNode.getPreviousNode();
                }
                String pathString = "";
                while (!path.isEmpty()) {
                    Node currPathNode = path.pop();
                    //TODO: fix output to not have trailing arrow. Holding off on this until return type (if any) is finalized.
                    pathString = pathString + currPathNode.getNodeName() + " -> ";
                }
                System.out.println(pathString);
                System.out.println("Total path cost: " + pathCost);
                //TODO change this return as well.
                return new String[2];

            }
            //if(!currentNode.visited){//Kinda redundant, but keeping for now.
            currentNode.visited = true;

            //check all neighbors of currentNode. Add to queue if they are not visited.
            //Mark them visited, and set their previous node and path cost.
            for (Neighbor n : currentNode.getNeighborList()) {
                if (!n.getNodeReference().visited) {
                    n.getNodeReference().setPreviousNode(currentNode);
                    n.getNodeReference().setPathCost(currentNode.getPathCost() + n.getPathCost());
                    stack.push(n.getNodeReference());
                }
            }
        }

        //TODO potentially remove return, or change it somehow. All that needs to be done is printing the result.
        return new String[2];
    }





	/**
	 * This is the main method which executes the flow of the method.
	 * See class comments for workflow.
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		//Get goal/start node names from user.
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

					//Add Neighbor to existing Node.
					nodes.get(currNodeName).addNeighbor(nodes.get(neighborName), pathCost);

				}

			}
			else{
				//Reading in node definitions
				String[] splt = s.split(",");
				Node n = new Node(splt[0],Integer.parseInt(splt[1]));
				nodes.put( splt[0],n);
			}
			System.out.println(s);

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


		// Run algorithm
		String[] path_and_cost = backtracking_algorithm(startNode, goalNode);
		System.out.println("Shortest path: " + path_and_cost[0]);
		System.out.println("Path cost: " + path_and_cost[1]);
	}

}
