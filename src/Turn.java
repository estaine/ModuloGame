
public class Turn {
	public int row, column, value;
	public Turn(int row, int column, int value) {
		setTurn(row, column, value);
	}
	public void setTurn(int row, int column, int value) {
		this.row = row;
		this.column = column;
		this.value = value;
	}
}
