package Models;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class GameBoard 
{
	private List<Stack<Token>> board = new ArrayList<Stack<Token>>();
	
	public GameBoard(int nbColumns)
	{		
		for(int i = 0; i < nbColumns; i++)
		{
			board.add(new Stack<Token>());
		}
	}
	
	public void add(int columnId, Token token)
	{		
		board.get(columnId).push(token);
	}

	public Stack<Token> getBoardColumn(int id) 
	{
		if (id >= board.size())
		{
			throw new ArrayIndexOutOfBoundsException();
		}
		return board.get(id);
	}
	
	
}
