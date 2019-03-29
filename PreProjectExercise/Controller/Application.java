package Controller;
import Model.BinSearchTree;
import View.MyFrame;

/**
 * This class runs the application to maintain student records.
 * @author Wenjia Yang and Joel Wong
 * @version 1.0
 * @since March 26, 2019
 *
 */
public class Application {
	
	/**
	 * The main method which run the student record application.
	 * @param args
	 */
	public static void main(String args[]) {
		BinSearchTree tree = new BinSearchTree();
		MyFrame frame = new MyFrame("Frame 1");
		Controller controller= new Controller(frame, tree);
		
		frame.setVisible(true);
	}
}
