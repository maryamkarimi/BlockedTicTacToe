/**
 * UWO - CS2210b
 * Assignment #2 
 * @date February 9, 2018
 * @author Maryam Karimi Firozjaei <mkarimif@uwo.ca>
 * Student #: 250999590
 * 
 * This class implements all the methods needed by algorithm computerPlay
 */
public class BlockedTicTacToe {
	
	/**
	 * Attributes:
	 * gameBoard		the gameBoard
	 * boardSize		the size of the gameBoard
	 * inLine		the number of symbols that need to be placed in-line to win the game
	 * maxLevels		the maximum number of levels of the program will explore
	 */
	private char[][] gameBoard;
	private int boardSize;
	private int inLine;
	private int maxLevels;
	
	/**
	 * Constructor to create a game board with specific characteristics
	 * @param 	boardSize the size of the gameBoard
	 * @param	inLine		the number of symbols that need to be placed in-line to win the game
	 * @param	maxLevels	the maximum number of levels of the program will explore
	 */
	public BlockedTicTacToe (int boardSize, int inLine, int maxLevels) {
		
		this.boardSize = boardSize;
		this.inLine = inLine;
		this.maxLevels = maxLevels;
		this.gameBoard = new char[boardSize][boardSize];
		
		// initialize the gameBoard - every entry stores a space
		for (int i = 0; i<boardSize; i++) {
			for (int j = 0; j<boardSize; j++) {
				gameBoard[i][j] = ' ';
			}
		}
	}
	
	/**
	 * @return an empty dictionary of the size 5903 (prime number - between 4000 & 6000)
	 */
	public TTTDictionary createDictionary() {
		return new TTTDictionary(5903);
	}
	
	/**
	 * @return String representation of the gameBoard
	 */
	private String getGameBoardString() {
		
		String gameBoardString="";
		for (int i = 0; i<boardSize; i++) {
			for (int j = 0; j<boardSize; j++) {
				gameBoardString+=gameBoard[i][j];
			}
		}
		return gameBoardString;
	}
	
	/**
	 * This method checks whether the string representation of the gameBoard 
	 * is in the configurations dictionary
	 * @param 	configurations		an object of TTTDictionary
	 * @return -1 if the string is not in the dictionary, associated score if it is in the dictionary
	 */
	public int repeatedConfig(TTTDictionary configurations) {
		// get the record in configurations that has the same config as gameBoard
		TTTRecord record = configurations.get(getGameBoardString());
		
		// string rep of the gameBoard IS in the configurations - returns its associated score
		if (record!=null) {
			return record.getScore();
		}
		
		// string rep of the gameBoard is NOT in the configurations - returns -1
		return -1;
	}
	
	/**
	 * This method inserts a new record to the dictionary
	 * @param configurations 	the dictionary that the record needs to be inserted in
	 * @param score 		the score associated with this record
	 * @param level		the level associated with this record
	 * @throws exception if the record already exists in the dictionary
	 * */
	public void insertConfig(TTTDictionary configurations, int score, int level) {
		// the record to be inserted
		TTTRecord newRecord = new TTTRecord (getGameBoardString(),score,level);
		
		try {
			configurations.put(newRecord);
		}
		catch(DuplicatedKeyException e) {
			System.out.println("The record already exists in the dictionary!");
		}
	}
	
	/**
	 * This method stores the character symbol in gameBoard[row][col]
	 * @param 	row		the row that the symbol needs to be stored in
	 * @param 	col		the col that the symbol needs to be stored in
	 * @param 	symbol	the symbol to be store in gameBoard[row][col]
	 */
	public void storePlay(int row, int col, char symbol) {
		gameBoard[row][col] = symbol;
	}
	
	/**
	 * This method checks if the gameBoard[row][col] is a space
	 * @param 	row		the row to be checked
	 * @param 	col		the col to be checked
	 * @return true if gameBoard[row][col] is a space, false otherwise
	 */
	public boolean squareIsEmpty (int row, int col) {
		if (gameBoard[row][col] == ' ') return true;
		else return false;
	}
	
	/**
	 * This method checks whether there are k adjacent occurrences of
	 * symbol in the same row, col or diagonal (k = inLine)
	 * @param 	symbol 		either 'x' or 'o': the method checks whether there are k adjacent 'x's or 'o's
	 * @return true if there are k adjacent occurrences, false otherwise.
	 */
	public boolean wins (char symbol) {
		// target to be searched: for example, 'xxx' if we are looking for 3 adjacent 'x's
		String target = "";
		String row, col, mainDiag1, mainDiag2, antiDiagonal1, antiDiagonal2;
		for (int i = 0; i<inLine; i++) {
			target+=symbol;
		}
		
		/**
		 * This algorithm moves at 6 directions at the same time:
		 * (1) It represents rows, cols ,and different diagonals as a string
		 * (2) Checks the strings to see if they contain the target
		 * 
		 * Clarification:
		 * row		   col		   mainDiag1		mainDiag2		antiDiag1	 antiDiag2
		 * ooooo       o↓oooo		↘oooo		o↘↘↘			↗↗↗o		 ooooo↗
		 * →→→→→       o↓oooo		↘↘oo		ooo↘↘			↗↗ooo       ooo↗↗
		 * ooooo       o↓oooo	  	↘↘↘		ooooo↘	    		↗ooooo       o↗↗↗		
		 * 
		 */
		
		for (int j = 0; j<boardSize; j++) {
			row = "";
			col = "";
			mainDiag1 = "";
			mainDiag2 = "";
			antiDiagonal1 = "";
			antiDiagonal2 = "";
			
			// helping variables
			int k = 0;
			int m = boardSize-1;
			
				for (int i = 0; i<boardSize; i++) {
					row+=gameBoard[j][i];
					col+=gameBoard[i][j];
					
					if (j+k<boardSize) {
						mainDiag1+=gameBoard[j+k][i];
						mainDiag2+=gameBoard[i][j+k];
					}
					
					if (m-j-k>-1 && m-i>-1) {
						antiDiagonal1+=gameBoard[m-j-k][i];
						antiDiagonal2+=gameBoard[m-i][i+j];
					}
					
					k++;
				}
			
			// returns true if target is in one of the strings 
			if (row.contains(target) || col.contains(target) ||
				mainDiag1.contains(target) || mainDiag2.contains(target) ||
				antiDiagonal1.contains(target) || antiDiagonal2.contains(target)) {
				return true;
			}
		}
		
		return false;	
	}
	
	/**
	 * This method checks whether the gameBoard has empty positions
	 * @return true if it has empty positions, false otherwise. 
	 */
	private boolean isThereEmptyPosition() {
		// check every single entry
		for (int i = 0; i<boardSize; i++) {
			for (int j = 0; j<boardSize; j++) {
				if (squareIsEmpty(i,j)) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * This method checks whether the game is draw
	 * @return true if the game is a draw, false otherwise
	 */
	public boolean isDraw() {
		// returns true if the gameBoard has no empty position left and no player has won the game
		if ( !isThereEmptyPosition() && !wins('x') && !wins('o')) {
			return true;
		}
		return false;	
	}
	
	/**
	 * This method evaluates the gameBoard
	 * @return 	0 	if the human player has won
	 * @return 	1   if the game is a draw
	 * @return	2	if the game is undecided
	 * @return	3 	if the computer has won		
	 * */
	public int evalBoard() {
		if (wins('x')) return 0;
		else if(wins('o')) return 3;
		else if (isDraw()) return 1;
		else return 2;
		
	}
	
}
