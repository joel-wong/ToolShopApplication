package Model;

/**
 * A node with data and two references to the next-left and next-right node
 * @author Dr. Moshirpour
 * @version 1.0
 * @since March 26, 2019
 */
public class Node {
	/**
	 * Student data
	 */
	Data data;
	/**
	 * Left and right node
	 */
	Node left, right;
	
	/**
	 * Constructs a Node with the specified parameters.
	 * @param id student id
	 * @param faculty faculty code
	 * @param major student's major
	 * @param year student's year of study
	 */
	public Node(String id, String faculty, String major, String year) {
		// creating a data item
		data = new Data(id, faculty, major, year);
		left = null;
        right = null;
	}
	
	public String toString() {
		return data.toString();
	}
}

