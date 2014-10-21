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
	/**
	 * Ajoute un jeton dans la colonne spécifié.
	 * @param columnIndex l'index de la colonne où il faut ajouter le jeton.
	 */
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
	/**
	 * Appel les fonctions nécessaire pour réinitialiser la partie
	 */
	public void reset()
	{
		isPlayer1 = true;
		gameView.Reset();
		gameView.initBoard(rowNb, colNb);
		gameBoard = new GameBoard(colNb);
	}
	/**
	 * Vérifie s'il est possible d'ajouter un jeton dans la colonne spécifié.
	 * @param columnId L'Id de la colonne 
	 * @return boolean Si c'est possible ou pas. 
	 */
	public boolean isAddPossible(int columnId)
	{
		boolean isPossible = true;
		
		if(gameBoard.getBoardColumn(columnId).size() == this.rowNb)
		{
			isPossible = false;
		}
		return isPossible;		
	}
	/**
	 * Vérifie si le joueur à gagnée la partie. 
	 * @param rowId L'Id de la rangée.
	 * @param colId L'Id de la colonne.
	 * @return Boolean, Si le joueur qui a jouer a gagner
	 */
	public boolean isGameWon(int rowId, int colId)
	{
		
		Status currentPlayerStatus = gameBoard.getBoardColumn(colId).get(rowId).getTokenStatus();
		
		if (CheckVertical(rowId, colId, currentPlayerStatus) || CheckHorizontal(rowId, colId, currentPlayerStatus) || CheckDiagonalTopToBottom(rowId, colId, currentPlayerStatus) || CheckDiagonalBottomToTop(rowId, colId, currentPlayerStatus))
		{
			return true;
		}
		
		return false;
	}
	/**
	 * Regarde si la partie est gagné.
	 * @param rowId L'Id de la rangée.
	 * @param colId L'Id de la colonne.
	 * @param currentPlayerStatus Le status du joueur qui a jouer le coup.
	 * @return Si le joueur gagne
	 */
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
	/**
	 * Regarde si la partie est gagné.
	 * @param rowId L'Id de la rangée.
	 * @param colId L'Id de la colonne.
	 * @param currentPlayerStatus Le status du joueur qui a jouer le coup.
	 * @return Si le joueur gagne
	 */
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
	/**
	 * Regarde si la partie est gagné.
	 * @param rowId L'Id de la rangée.
	 * @param colId L'Id de la colonne.
	 * @param currentPlayerStatus Le status du joueur qui a jouer le coup.
	 * @return Si le joueur gagne
	 */
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
	/**
	 * Regarde si la partie est gagné.
	 * @param rowId L'Id de la rangée.
	 * @param colId L'Id de la colonne.
	 * @param currentPlayerStatus Le status du joueur qui a jouer le coup.
	 * @return Si le joueur gagne
	 */
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

	/**
	 * Permet de régler le nombre de rangées de la grille
	 * @param nbRows le nombre de rangées de la grille.
	 */
	public void setNbRow(int nbRows) 
	{
		this.rowNb = nbRows;
	}
	/**
	 * Permet de régler le nombre de colonnes de la grille
	 * @param nbColumns le nombre de colonnes de la grille
	 */
	public void setNbColumns(int nbColumns) 
	{
		this.colNb = nbColumns;
	}
	/**
	 * Permet de régler le nombre de Token nécessaire pour gagner la partie.
	 * @param nbTokenToWin le nombre Token
	 */
	public void setNbTokenToWin(int nbTokenToWin) 
	{
		this.numberOfTokenForVictory = nbTokenToWin;
	}	
/**
 * Retourne le nombre de rangée de la grille 
 * @return Le nombre de rangées de la grille
 */
	public int getNbRows() 
	{
		return this.rowNb;
	}
	/**
	 * Retourne le nombre de colonnes de la grille 
	 * @return le nombre de colonnes de la grille
	 */
	public int getNbCols() 
	{
		return this.colNb;
	}
	/**
	 * Main de l'application
	 * @param args 
	 */
	public static void main(String[] args)
	{
		int i = 0;
		GameController controller = new GameController();
		
		i++;
		
	}
	
}
