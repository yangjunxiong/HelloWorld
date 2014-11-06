import javax.swing.JButton;


public class Grid {
	private boolean isClicked;
	private boolean isBomb;
	private int numOfBomb;
	private JButton button;
	
	public Grid(double rate) {
		double temp = Math.random();
		if (temp <= rate) {
			isBomb = true;
			numOfBomb = -1;
		}
		else {
			isBomb = false;
			numOfBomb = 0;
		}
		isClicked = false;
	}
	
	public void setClicked() {
		isClicked = true;
	}
	
	public void setNumOfBomb(int num) {
		numOfBomb = num;
	}
	
	public void setButton(JButton button) {
		this.button = button;
	}
	
	public boolean isClicked() {
		return isClicked;
	}
	
	public boolean isBomb() {
		return isBomb;
	}
	
	public int getNumOfBomb() {
		return numOfBomb;
	}
	
	public JButton getButton() {
		return button;
	}
	
}
