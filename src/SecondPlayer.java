import java.util.Random;

public class SecondPlayer implements Move {

	@Override
	public Turn nextMove(Board board) {
		// TODO Auto-generated method stub
		Cell[][] boardCells = board.getCells();
		int generatedRow, generatedColumn, generatedValue;
		
		Random randCell = new Random();
		
		do {
			generatedRow = randCell.nextInt(boardCells.length);
			generatedColumn = randCell.nextInt(boardCells.length);
		} while(boardCells[generatedRow][generatedColumn].isFilled());
		
		generatedValue = randCell.nextInt(Cell.MAX_VALUE) + Cell.MIN_VALUE;
		
		return new Turn(generatedRow, generatedColumn, generatedValue);		
	}
}
