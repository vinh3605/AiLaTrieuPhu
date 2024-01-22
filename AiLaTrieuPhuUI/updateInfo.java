package AiLaTrieuPhuUI;

public class updateInfo {
	
	String user;
	int score;
	public updateInfo(String user, int score) {
		super();
		this.user = user;
		this.score = score;
	}
	public updateInfo() {
		super();
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
	

}
