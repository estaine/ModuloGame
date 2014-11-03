
public interface Move {

	Turn nextMove(Board board) throws CellIsFilledException, CellIsEmptyException, IncorrectCellValueException;

}
