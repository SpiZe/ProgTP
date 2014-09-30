package Models;

public class Token {
	private enum Status
	{
		Player1, Player2, Empty
	}
	private String imgPath; 
	private Status tokenStatus; 
	
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
