/**
 * UWO - CS2210b
 * Assignment #2 
 * @date February 9, 2018
 * @author Maryam Karimi Firozjaei <mkarimif@uwo.ca>
 * Student #: 250999590
 * 
 * This class is used to handle duplicated key exceptions
 */
public class DuplicatedKeyException extends Exception {
	
	private static final long serialVersionUID = 1L;

	/**
	 * The insturctor of the class
	 * @param the message to be shown
	 * @return a message (String) to be shown to the user
	 */
	public DuplicatedKeyException(String message) {
		super(message);
	}
}