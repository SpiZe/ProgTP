package Models;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class GameBoard 
{
	final static int DEFAULT_NUMBER_ROW = 6;
	final static int DEFAULT_NUMBER_COLS = 7;
	final static int DEFAULT_NUMBER_TOKENS_TO_WIN = 4;
	
	private int rowNb;
	private int colNb;
	private int numberOfTokenForVictory;
	
	private List<Stack<Token>> board = new ArrayList<Stack<Token>>();
	
	public GameBoard()
	{		
		this(DEFAULT_NUMBER_ROW, DEFAULT_NUMBER_COLS, DEFAULT_NUMBER_TOKENS_TO_WIN);
	}
	
	public GameBoard(int numberOfRows, int numberOfColumns, int numberInRowToWin)
	{
		rowNb = numberOfRows;
		colNb = numberOfColumns;
		numberOfTokenForVictory = numberInRowToWin;	
		
		for(int i = 0; i < colNb; i++)
		{
			board.add(new Stack<Token>());
		}
	}
	
	public boolean add(int columIdToPushOn, Token tokenToPush)
	{
		boolean isPossible = true;
		
		if(board.get(columIdToPushOn).size() == rowNb)
		{
			isPossible = false;
		}
		
		board.get(columIdToPushOn).push(tokenToPush);
		return isPossible;
	}
}
