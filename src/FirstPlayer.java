import java.util.Random;


public class FirstPlayer implements Move{

	@Override
	public Turn nextMove(Board board) throws CellIsFilledException, CellIsEmptyException, IncorrectCellValueException {
		Cell[][] boardCells = board.getCells();
		int maxPoints = 0, currentPoints;
		int minValue = Cell.MAX_VALUE;
		Turn turn = new Turn(0,0,0);
		Turn bestTurn = new Turn(0,0,0);
		for(int rowIndex = 0; rowIndex < boardCells.length; rowIndex++)
			for(int columnIndex = 0; columnIndex < boardCells[rowIndex].length; columnIndex++) {
				if(!boardCells[rowIndex][columnIndex].isFilled())
					for(int currentCellValue = Cell.MIN_VALUE; currentCellValue <= Cell.MAX_VALUE; currentCellValue++) {
						turn.setTurn(rowIndex, columnIndex, currentCellValue);
						currentPoints = board.fillCell(turn, false);
						if(maxPoints < currentPoints)
						{
							bestTurn.setTurn(rowIndex, columnIndex, currentCellValue);
							maxPoints = currentPoints;
							minValue = currentCellValue;
						}
							
					}
			}
				
		return bestTurn;	
	}
}
