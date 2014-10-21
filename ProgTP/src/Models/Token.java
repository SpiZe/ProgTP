package Models;

/**
 * 
 * @author Samuel, Keven, Olivier
 *
 */
public class Token 
{
	/**
	 * 
	 * @author Samuel, Keven, Olivier
	 *
	 */
	public enum Status
	{
		Player1, Player2
	}
	private String imgPath; 
	private Status tokenStatus; 
	
	/**
	 * Constructeur de la classe Token
	 * @param isPlayer1 Vérifie le joueur pour mettre le bon status.
	 */
	public Token(boolean isPlayer1) 
	{
		if(isPlayer1)
		{
			this.tokenStatus = Status.Player1;
		}
		else
		{
			this.tokenStatus = Status.Player2;
		}
	}
	/**
	 * Permet de savoir le chemin de l'image
	 * @return Le chemin vers l'image du Token
	 */
	public String getImgPath() {
		return imgPath;
	}
	/**
	 * Permet de changer le path vers l'image du Token
	 * @param imgPath Le nouveau chemin de l'image du Token 
	 */
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	/**
	 * Permet de savoir le status du Token
	 * @return Le status du Token
	 */
	public Status getTokenStatus() {
		return tokenStatus;
	}
	/**
	 * Permet de changer le status du Token.
	 * @param tokenStatus Le nouveau status du Token.
	 */
	public void setTokenStatus(Status tokenStatus) {
		this.tokenStatus = tokenStatus;
	}
}
