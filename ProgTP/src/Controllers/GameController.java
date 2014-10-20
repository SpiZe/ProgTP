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
				System.out.println("Partie gagnée par " + isPlayer1);
			}
			
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
	
	public boolean isGameWon(int rowId, int colId)
	{
		int comboCounter = 1;
		
		Status currentPlayerStatus = gameBoard.getBoardColumn(colId).get(rowId).getTokenStatus();
		
		for(int i = -1; i < 1; i++)
		{
			for(int j = -1; i < 1; i++)
			{
				if(i != 0 && j != 0)
				{
					if((colId+i > -1 && colId+i < this.colNb)
					|| (rowId+j > -1 && rowId+j < this.rowNb))
					{				
						Status tokenAroundStatus = gameBoard.getBoardColumn(colId+i).get(rowId+j).getTokenStatus();
						
						if(tokenAroundStatus == currentPlayerStatus)
						{
							int currentRow = rowId+j;
							int currentCol = colId+i;
							
							for(int k = 0; k < this.numberOfTokenForVictory; k++)
							{
								Status nextTokenStatus = gameBoard.getBoardColumn(currentCol+i).get(currentRow+j).getTokenStatus();
								
								if(nextTokenStatus == currentPlayerStatus)
								{
									comboCounter++;
								}
								else
								{
									break;
								}
							}
							
							if(comboCounter == this.numberOfTokenForVictory)
							{
								return true;
							}
						}
					}
				}
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
