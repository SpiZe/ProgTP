package Models;

public class Token 
{
	private enum Status
	{
		Player1, Player2
	}
	private String imgPath; 
	private Status tokenStatus; 
	
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
