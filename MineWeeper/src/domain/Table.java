package domain;

public class Table {
	private Grid[][] gridList;
	private int row, column;
	private double rate;
	
	public Table(int row, int column, double rate) {
		this.row = row;
		this.column = column;
		this.rate = rate;
		gridList = new Grid[row][column];
		addGrid();
		putNum();
	}
	
	public Grid getGrid(int x, int y) {
		return gridList[x][y];
	}
	
	private void addGrid() {
		for (int i=0; i<row; i++)
			for (int j=0; j<column; j++)
				gridList[i][j] = new Grid(rate);
	}
	
	private void putNum() {
		for (int i=0; i<row; i++)
			for (int j=0; j<column; j++)
				if (!gridList[i][j].isBomb()) {
					int temp = 0;
					try {
						if (gridList[i-1][j-1].isBomb())
							temp++;
					}
					catch (Exception e) {}
					try {
						if (gridList[i-1][j].isBomb())
							temp++;
					}
					catch (Exception e) {}
					try {
						if (gridList[i-1][j+1].isBomb())
							temp++;
					}
					catch (Exception e) {}
					try {
						if (gridList[i][j-1].isBomb())
							temp++;
					}
					catch (Exception e) {}
					try {
						if (gridList[i][j+1].isBomb())
							temp++;
					}
					catch (Exception e) {}
					try {
						if (gridList[i+1][j-1].isBomb())
							temp++;
					}
					catch (Exception e) {}
					try {
						if (gridList[i+1][j].isBomb())
							temp++;
					}
					catch (Exception e) {}
					try {
						if (gridList[i+1][j+1].isBomb())
							temp++;
					}
					catch (Exception e) {}
					gridList[i][j].setNumOfBomb(temp);
				}
	}
}
