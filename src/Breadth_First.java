import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

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
	private static String[] breadth_first_algorithm() {
		return new String[2];
	}
	
	
	/**
	 * This is the main method which contains the execution flow.
	 * See class comments for actual workflow.
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		// Load input
		Scanner scanner = new Scanner(new File(args[0]));
		boolean neighbors = false;
		boolean heuristics = false;
		//ArrayList<Node> nodes = new ArrayList<Node>();
		HashMap<String, Node> nodes = new HashMap<>();

		while(scanner.hasNext()){
			String s = scanner.next();
			s = s.trim();
			if(s .equals("NEIGHBORS")){
				neighbors = true;
				System.out.println("TRUE!!!!!");
			}
			else if(heuristics){
				//do something
			}
			else if(neighbors){
				String[] splt = s.split(":");
				String currNodeName = splt[0];

				String[] neighborValues = splt[1].split(",");
				for(int i = 0; i< neighborValues.length; i+=2){
					String neighborName = neighborValues[i];
					int pathCost = Integer.parseInt(neighborValues[i+1]);

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
		// Build nodes and neighbors
		
		
		// Run algorithm
		String[] path_and_cost = breadth_first_algorithm();
		System.out.println("Shortest path: " + path_and_cost[0]);
		System.out.println("Path cost: " + path_and_cost[1]);
	}

}