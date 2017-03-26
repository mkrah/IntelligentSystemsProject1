import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This is the main class for running the A* algorithm.
 * It builds the tree, runs the algorithm, and then prints the shortest path
 * and shortest cost.
 * @author Cody Perry (cp5337), Michael Lah (mxl8113)
 *
 */
public class A_Star {

	/*
	 * This method runs the A* algorithm on the tree
	 * and returns a String array like this: [shortest_path, path_cost]
	 * Then main prints these values and finishes execution.
	 * @param Tree structure
	 * @return String[]
	 */
	private static String[] a_star_algorithm() {
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
		
		// Build nodes and neighbors
		
		
		// Run algorithm
		String[] path_and_cost = a_star_algorithm();
		System.out.println("Shortest path: " + path_and_cost[0]);
		System.out.println("Path cost: " + path_and_cost[1]);
	}

}
