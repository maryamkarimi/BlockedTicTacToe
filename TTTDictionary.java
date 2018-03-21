import java.util.ArrayList;



/**
 * UWO - CS2210b
 * Assignment #2 
 * @date February 9, 2018
 * @author Maryam Karimi Firozjaei <mkarimif@uwo.ca>
 * Student #: 250999590
 * 
 * This class implements a dictionary, it implements TTTDictionaryADT
 */

public class TTTDictionary implements TTTDictionaryADT {
	
	/**
	 * Attributes:	
	 * elementCounter					the number of records in the dictionary
	 * tableSize							the size of the table
	 * ArrayList<ArrayList<TTTRecord>>	the table of records
	 */
	private int elementCounter;
	private int tableSize;
	private ArrayList<ArrayList<TTTRecord>> dictionary;
	
	
	/**
	 * The instructor creates an empty TTTdictionary of size dicSize
	 * @param size size of the dictionary to be created
	 */
	public TTTDictionary(int size){
		// initialize the instance variables
		 tableSize = size;
		 elementCounter = 0;
		 dictionary = new ArrayList<ArrayList<TTTRecord>>(size);
		 for (int i = 0; i<tableSize; i++) {
			 dictionary.add(new ArrayList<TTTRecord>());
		 }
	}
	
	/**
	 * This method inserts a given record to the dictionary
	 * @param record 	record to be added to the dictionary
	 * @throws DuplicatedKeyException when the record already exists in the dictionary
	 * @returns 1 if the insertion of record produces a collision, 0 otherwise
	 */
	public int put(TTTRecord record) throws DuplicatedKeyException {
		
		int index = hashFunction(record.getConfiguration());
		
		//get the list where this record is in
		ArrayList<TTTRecord> list = dictionary.get(index);
		int result = 0;
		
		if (!list.isEmpty()) {
			for (TTTRecord Currecord: list) {
				// throw exception if the record is already in the dictionary
				if (Currecord.getConfiguration().equals(record.getConfiguration())) {
				throw new DuplicatedKeyException("The record already exists in the dictionary!");
				}
			}
			result = 1;
		}
		
		// add the record to the right list
		list.add(record);
		elementCounter++;
		return result;
	}

	/**
	 * This method removes a specific record from the dictionary using its config
	 * @param 	config		the configuration (key) of the record to be removed
	 * @throws	InexistentKeyException		if no record with this key exists
	 * */
	public void remove(String config) throws InexistentKeyException {
		// find the index using the hash function
		int index = hashFunction(config);
		
		ArrayList<TTTRecord> list = dictionary.get(index);
		
		if (!list.isEmpty()) {
			for (TTTRecord record: list) {
				if (record.getConfiguration().equals(config)) {
					
					//remove the record, update the counter
					list.remove(record);
					elementCounter--;
					return;
				}
			}
		}
		throw new InexistentKeyException("No record with this configuration exists");
		
	}

	/**
	 * This method finds the record with a specific key (config) and returns it
	 * @param config 	the key of the record to be found
	 * @return	the record with the key of config or null no record with this key is in the dictionary.
	 * */
	public TTTRecord get(String config) {
		
		// find the index using the hash function
		int index = hashFunction(config);
		
		ArrayList<TTTRecord> list= dictionary.get(index);
		
		if (!list.isEmpty()) {
			
			// finds the record with a string configuration of config
			for (TTTRecord record : list) {
				if (record.getConfiguration().equals(config)) {
					return record;
				}
			}
		}
		return null;
		
	}

	/**
	 * This method returns the number of TTTRecord objects stored in the dictionary
	 @return number of TTTRecord object in the dictionary
	 */
	public int numElements() {
		return elementCounter;
	}
	
	/**
	 * The hash function to implement the dictionary
	 * @param the key of a specific record
	 * @return the index of the array where the record with key of config should be stored
	 * */
	private int hashFunction(String config) {
		int a = 0;
		for (char c: config.toCharArray()) {
			a = ((int)c+a*47)% tableSize;
		}
		return a;
	}

}