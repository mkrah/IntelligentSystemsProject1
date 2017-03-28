import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;


/**
 * This is the main class for running the BFS algorithm.
 * It builds the tree, runs the algorithm, and then prints the shortest path
 * and shortest cost.
 * @author Cody Perry (cp5337), Michael Lah (mxl8113)
 *
 */
public class Breadth_First {
	/*
	 * This method runs the breadth first search algorithm on the tree
	 * and returns a String array like this: [shortest_path, path_cost]
	 * Then main prints these values and finishes execution.
	 * @param Tree structure
	 * @return String[]
	 */
	private static void breadth_first_algorithm(Node startNode, Node goalNode) {

	    //queue for BFS algorithm.
	    ArrayDeque<Node> queue = new ArrayDeque<>();

        queue.add(startNode);
        //queue is ready, start looping.
        while(!queue.isEmpty()){
            Node currentNode = queue.pop();
            //if(currentNode.getNodeName().equals(goalNodeName)){
            if(currentNode == goalNode){
                //goal node found.
                int pathCost = currentNode.getPathCost();
                Stack<Node> path = new Stack<>();

                //Traverse from end node to start node, keeping the path in "path"
                while(true){
                    if(currentNode.getPreviousNode() == null){
                        path.add(currentNode);
                        break;
                    }
                    path.add(currentNode);
                    currentNode = currentNode.getPreviousNode();
                }
				//Print output
				String pathString = "";
				String sep = "";
				while (!path.isEmpty()) {
					Node currPathNode = path.pop();
					pathString = pathString + sep + currPathNode.getNodeName();
					sep = " -> ";
				}
				System.out.println(pathString);
				System.out.println("Total path cost: " + pathCost);

				return;

            }
            currentNode.visited = true;

            //check all neighbors of currentNode. Add to queue if they are not visited.
            //Mark them visited, and set their previous node and path cost.
            for(Neighbor n : currentNode.getNeighborList()){
                if(!n.getNodeReference().visited){
                    n.getNodeReference().setPreviousNode(currentNode);
                    n.getNodeReference().setPathCost( currentNode.getPathCost() + n.getPathCost());
                    queue.add(n.getNodeReference());
                }
            }
        }

	}
	
	
	/**
	 * This is the main method which contains the execution flow.
	 * See class comments for actual workflow.
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


		breadth_first_algorithm(startNode, goalNode);
	}

}
