/**
 * UWO - CS2210b
 * Assignment #2 
 * @date February 9, 2018
 * @author Maryam Karimi Firozjaei <mkarimif@uwo.ca>
 * Student #: 250999590
 * 
 * This class represents one of the configurations that will be stored
 * in the dictionary along with its integer score and level.
 */

public class TTTRecord {
	
	/**
	 * Attributes:
	 * config	the string obtained by concatenating all characters placed on the board
	 * score		the associated score to the record
	 * level		the associated level to the record
	 */
	String config;
	int score;
	int level;
	
	
	/**
	 * The instuctor returns a new TTTRecord with the specified configuration, level, and score
	 * @param 	config	the key attribute of TTTRecord object
	 * @param	score	the associated score to the record
	 * @param	level	the associated level to the record
	 */
	public TTTRecord(String config, int score, int level) {
		this.config = config;
		this.score = score;
		this.level = level;
	}
	
	/**
	 * This method returns the configuration attribute of this TTTRecord
	 * @return the configuration of this TTTRecord
	 */
	public String getConfiguration() {
		return config;
	}
	
	/**
	 * This method returns the score associated with this TTTRecord
	 * @return the score associated with this TTTRecord
	 */
	public int getScore(){
		return score;
	}

	/**
	 * This method returns the level associated with this TTTRecord
	 * @return the level associated with this TTTRecord
	 */
	public int getLevel() {
		return level;
	}
}
