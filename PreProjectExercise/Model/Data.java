package Model;
/**
 * Represents studnet data within a node
 * @author Dr. Moshirpour
 * @version 1.0
 * @since March 26, 2019
 */
public class Data {
	
	/**
	 * id is the student's id
	 * faculty is the student's faculty 
	 * major is the student's major
	 * year is the student's year
	 */
	String  id,faculty, major, year;
	
	/**
	 * Constructs the Data object with the specifed parameters
	 * @param i is the id
	 * @param f is the faculty
	 * @param m is the major
	 * @param y is the year
	 */
	public Data( String i, String f, String m, String y)
	{
		id = i;
		faculty = f;
		major = m;
		year = y;
	}
	
	public String toString()
	{
		return ("id : " + id + " faculty: " + faculty + " major: " + major + 
						" year: " + year);
	}	
}
