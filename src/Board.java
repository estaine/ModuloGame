import java.util.ArrayList;


public class Board {

	private static final int SIZE = 8;
	
	private Cell[][] cells;
	
	public Board() throws IncorrectCellValueException {
		cells = new Cell[SIZE][SIZE];
		for(int rowIndex = 0; rowIndex < SIZE; rowIndex++) {
			for(int columnIndex = 0; columnIndex < SIZE; columnIndex++)
				cells[rowIndex][columnIndex] = new Cell();
		}

		cells[0][0].setValue(2);
		cells[0][SIZE - 1].setValue(2);
		cells[SIZE - 1][0].setValue(2);
		cells[SIZE - 1][SIZE - 1].setValue(2);				
	}
	
	private Cell getCellByIndex(int row, int column) {
		if((row < 0) || (row >= SIZE) || (column < 0) || (column >= SIZE))
			return null;
		return cells[row][column];
	}
	
	private ArrayList<Cell> getAdjacentCells (Cell cell, int row, int column) {
		ArrayList<Cell> adjacentCells = new ArrayList<Cell>();
		Cell currentCell;
		
		for(int rowIndex = row - 1; rowIndex <= row + 1; rowIndex++)
			for(int columnIndex = column - 1; columnIndex <= column + 1; columnIndex++) {
				if((rowIndex == row) && (columnIndex == column))
						continue;
				currentCell = getCellByIndex(rowIndex, columnIndex);
				if(currentCell != null)
					adjacentCells.add(currentCell);
			}
		
		return adjacentCells;
	}
	
	public int fillCell(Turn turn, boolean changeState) throws CellIsFilledException, CellIsEmptyException, IncorrectCellValueException {
		Cell selectedCell = getCellByIndex(turn.row, turn.column);
		int gainedPoints = 0;
		int modulo = 0;
		
		try {
			if(selectedCell == null)
				throw new IndexOutOfBoundsException("Cell row and/or column are incorrect\n");
			if(selectedCell.isFilled())
				throw new CellIsFilledException("Cell is already filled\n");
		}
		catch(IndexOutOfBoundsException | CellIsFilledException e) {
			System.out.println(e.getMessage());
		}
		
		if(changeState)
			selectedCell.setValue(turn.value);
		
		ArrayList<Cell> adjacentCells = getAdjacentCells(selectedCell, turn.row, turn.column);
		for(Cell cell : adjacentCells)
			if(cell.isFilled()) {
				modulo = turn.value % cell.getValue();
				gainedPoints += modulo;
				if((modulo == 0) && changeState)
					cell.flush();				
			}
		
		return gainedPoints;
	}
	
	public boolean endOfGame() {
		for(Cell[] row : cells)
			for(Cell cell : row)
				if(!cell.isFilled())
					return false;
		return true;
	}
	
	public Cell[][] getCells() {
		return cells;				
	}
}
