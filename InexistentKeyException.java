import java.util.NoSuchElementException;

/**
 * UWO - CS2210b
 * Assignment #2 
 * @date February 9, 2018
 * @author Maryam Karimi Firozjaei <mkarimif@uwo.ca>
 * Student #: 250999590
 * 
 * This class is used to handle Inexistent Key Exceptions, it implements NoSuchElementException
 */
public class InexistentKeyException extends NoSuchElementException {
	
	private static final long serialVersionUID = 1L;

	/**
	 * The insturctor of the class
	 * @param the message to be shown
	 * @return a message (String) to be shown to the user
	 */
	public InexistentKeyException(String message){
		super(message);
	}
}
