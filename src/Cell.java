public class Cell {
	public static final int MIN_VALUE = 1;
	public static final int MAX_VALUE = 9;
	
	protected int value = 0;
	private boolean filled = false;
	
	public Cell() { }
	
	public Cell(int value) throws IncorrectCellValueException {
		setValue(value);
	}
	
	public boolean isFilled() { return filled; }
	
	public void divide(int dividend) {
		value = dividend / value;
	}
	
	public int getValue() throws CellIsEmptyException {
		try {
			if(!filled)
				throw new CellIsEmptyException("Cell is not filled\n");
		}
		catch(CellIsEmptyException e) {
			System.out.println(e.getMessage());
		}
		return value;
	}
	
	public void setValue(int value) throws IncorrectCellValueException {
		if((value < MIN_VALUE) || (value > MAX_VALUE))
			throw new IncorrectCellValueException("Cell value is incorrect\n");
		this.value = value;
		filled = true;
	}
}
