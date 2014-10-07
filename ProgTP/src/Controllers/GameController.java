package Controllers;

import Models.GameBoard;
import Models.Token;
import Views.View;

public class GameController 
{
	private View gameView;
	private GameBoard gameBoard;
	
	private boolean isPlayer1;
	
	private int rowNb = 6;
	private int colNb = 7;
	private int numberOfTokenForVictory = 4;
	
	public GameController()
	{
		isPlayer1 = true;
		gameView = new View(this);
		gameView.initBoard(rowNb, colNb);
		
		gameBoard = new GameBoard(colNb);
	}

	public void add(int columnIndex) 
	{		
		if(isAddPossible(columnIndex))
		{
			Token newToken = new Token(isPlayer1);
			gameBoard.add(columnIndex, newToken);
			
			gameView.UpdateToken(gameBoard.getBoardColumn(columnIndex).size()-1, columnIndex, isPlayer1);
			
			isGameWon(gameBoard.getBoardColumn(columnIndex).size()-1, columnIndex);
			
			isPlayer1 = !isPlayer1;
		}
		
	}
	
	public boolean isAddPossible(int columnId)
	{
		boolean isPossible = true;
		
		if(gameBoard.getBoardColumn(columnId).size() == this.rowNb)
		{
			isPossible = false;
		}
		return isPossible;		
	}
	
	public boolean isGameWon(int i, int j)
	{
		int comboCounter = 1;
		

		
		return false;
	}

	public void setNbRow(int nbRows) 
	{
		this.rowNb = nbRows;
	}
	
	public void setNbColumns(int nbColumns) 
	{
		this.colNb = nbColumns;
	}
	
	public void setNbTokenToWin(int nbTokenToWin) 
	{
		this.numberOfTokenForVictory = nbTokenToWin;
	}	

	public int getNbRows() 
	{
		return this.rowNb;
	}
	
	public int getNbCols() 
	{
		return this.colNb;
	}
	
	public static void main(String[] args)
	{
		int i = 0;
		GameController controller = new GameController();
		
		i++;
		
	}
	
}
