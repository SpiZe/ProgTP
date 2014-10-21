package Models;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 
 * @author Olivier, Keven, Samuel
 *
 */
public class GameBoard 
{
	private List<Stack<Token>> board = new ArrayList<Stack<Token>>();
	
	/**
	 * Constructeur de la classe gameboard.
	 * @param nbColumns Le nombre de colonnes du gameboard
	 */
	public GameBoard(int nbColumns)
	{		
		for(int i = 0; i < nbColumns; i++)
		{
			board.add(new Stack<Token>());
		}
	}
	/**
	 * Ajoute un token dans la colonne dont l'Id est pass� en param�tre
	 * @param columnId L'Id de la colonne
	 * @param token Le token
	 */
	public void add(int columnId, Token token)
	{		
		board.get(columnId).push(token);
	}
	
	/**
	 * Retourne une colonne dont l'Id est pass� en param�tre.
	 * @param id L'Id de la colonne d�sir�e.
	 * @return La stack de token qui repr�sente la colonne du gameboard
	 */
	public Stack<Token> getBoardColumn(int id) 
	{
		if (id >= board.size())
		{
			throw new ArrayIndexOutOfBoundsException();
		}
		return board.get(id);
	}
	
	
}
