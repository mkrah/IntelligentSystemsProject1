import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

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
	private static String[] backtracking_algorithm() {
		return new String[2];
	}
	
	/**
	 * This is the main method which executes the flow of the method.
	 * See class comments for workflow.
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		// Load input
		Scanner scanner = new Scanner(new File(args[0]));
		
		// Build nodes and neighbors
		
		
		// Run algorithm
		String[] path_and_cost = backtracking_algorithm();
		System.out.println("Shortest path: " + path_and_cost[0]);
		System.out.println("Path cost: " + path_and_cost[1]);
	}

}
