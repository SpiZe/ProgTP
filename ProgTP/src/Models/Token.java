package Models;

public class Token {
	private enum Status
	{
		Player1, Player2, Empty
	}
	private int posY; 
	private String imgPath; 
	private int posX;
	private Status tokenStatus; 
	
	public int getPosX() {
		return posX;
	}
	
	public void setPosX(int posX) {
		this.posX = posX;
	}
	
	public int getPosY() {
		return posY;
	}
	
	public void setPosY(int posY) {
		this.posY = posY;
	}
	
	public String getImgPath() {
		return imgPath;
	}
	
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public Status getTokenStatus() {
		return tokenStatus;
	}

	public void setTokenStatus(Status tokenStatus) {
		this.tokenStatus = tokenStatus;
	}
	
	
	
}
