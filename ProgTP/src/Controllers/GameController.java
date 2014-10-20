package Controllers;

import Models.GameBoard;
import Models.Token;
import Models.Token.Status;
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
			
			if(isGameWon(gameBoard.getBoardColumn(columnIndex).size()-1, columnIndex))
			{
				String winner = "";
				if (isPlayer1)
				{
					winner = "Shrek";
				}
				else
				{
					winner = "Nicolas Cage";
				}
				gameView.GameIsWon(winner);
				System.out.println("Partie gagnée par " + isPlayer1);
			}
			
			isPlayer1 = !isPlayer1;
		}
		
	}
	
	public void reset()
	{
		isPlayer1 = true;
		gameView.Reset();
		gameView.initBoard(rowNb, colNb);
		gameBoard = new GameBoard(colNb);
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
	
	public boolean isGameWon(int rowId, int colId)
	{
		
		Status currentPlayerStatus = gameBoard.getBoardColumn(colId).get(rowId).getTokenStatus();
		
		if (CheckVertical(rowId, colId, currentPlayerStatus) || CheckHorizontal(rowId, colId, currentPlayerStatus) || CheckDiagonalTopToBottom(rowId, colId, currentPlayerStatus) || CheckDiagonalBottomToTop(rowId, colId, currentPlayerStatus))
		{
			return true;
		}
		
		return false;
	}
	
	private boolean CheckDiagonalBottomToTop(int rowId, int colId, Status currentPlayerStatus)
	{
		int combo = 1;
		int o = colId + 1;
		//Haut droite
		for (int i = rowId + 1; i >= rowId - this.numberOfTokenForVictory; i++)
		{
			try
			{
				if (currentPlayerStatus == gameBoard.getBoardColumn(o).get(i).getTokenStatus())
				{
					combo++;
					if (combo >= this.numberOfTokenForVictory)
					{
						return true;
					}
				}
			}
			catch (ArrayIndexOutOfBoundsException e)
			{
				break;
			}
			o++;
		}
		
		o = colId - 1;
		//Bas droit
		for (int i = rowId - 1; i <= rowId + this.numberOfTokenForVictory; i--)
		{
			try
			{
				if (currentPlayerStatus == gameBoard.getBoardColumn(o).get(i).getTokenStatus())
				{
					combo++;
					if (combo >= this.numberOfTokenForVictory)
					{
						return true;
					}
				}
			}
			catch (ArrayIndexOutOfBoundsException e)
			{
				break;
			}
			o--;
		}
		return false;
	}
	
	private boolean CheckDiagonalTopToBottom(int rowId, int colId, Status currentPlayerStatus)
	{
		int combo = 1;
		int o = colId - 1;
		//Haut gauche
		for (int i = rowId + 1; i >= rowId - this.numberOfTokenForVictory; i++)
		{
			try
			{
				if (currentPlayerStatus == gameBoard.getBoardColumn(o).get(i).getTokenStatus())
				{
					combo++;
					if (combo >= this.numberOfTokenForVictory)
					{
						return true;
					}
				}
			}
			catch (ArrayIndexOutOfBoundsException e)
			{
				break;
			}
			o--;
		}
		
		o = colId + 1;
		//Bas droit
		for (int i = rowId - 1; i <= rowId + this.numberOfTokenForVictory; i--)
		{
			try
			{
				if (currentPlayerStatus == gameBoard.getBoardColumn(o).get(i).getTokenStatus())
				{
					combo++;
					if (combo >= this.numberOfTokenForVictory)
					{
						return true;
					}
				}
			}
			catch (ArrayIndexOutOfBoundsException e)
			{
				break;
			}
			o++;
		}
		return false;
	}
	
	private boolean CheckHorizontal(int rowId, int colId, Status currentPlayerStatus)
	{
		int combo = 1;
		
		//Gauche
		for (int i = colId-1; i >= colId - this.numberOfTokenForVictory; i--)
		{
			try
			{
				if (currentPlayerStatus == gameBoard.getBoardColumn(i).get(rowId).getTokenStatus())
				{
					combo++;
					if (combo >= this.numberOfTokenForVictory)
					{
						return true;
					}
				}
			}
			catch (ArrayIndexOutOfBoundsException e)
			{
				break;
			}
		}
		
		//droite
		for (int i = colId + 1; i <= colId + this.numberOfTokenForVictory; i++)
		{
			try
			{
				if (currentPlayerStatus == gameBoard.getBoardColumn(i).get(rowId).getTokenStatus())
				{
					combo++;
					if (combo >= this.numberOfTokenForVictory)
					{
						return true;
					}
				}
			}
			catch (ArrayIndexOutOfBoundsException e)
			{
				break;
			}
		}
		return false;
	}
	
	private boolean CheckVertical(int rowId, int colId, Status currentPlayerStatus)
	{
		int combo = 0;
		
		for (int i = rowId; i >= 0; i--)
		{
			if (currentPlayerStatus == gameBoard.getBoardColumn(colId).get(i).getTokenStatus())
			{
				combo++;
				if (combo >= this.numberOfTokenForVictory)
				{
					return true;
				}
			}
			if (i <= rowId - this.numberOfTokenForVictory)
			{
				return false;
			}
		}
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
