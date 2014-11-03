import java.io.File;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;


public class GameManager {

	/**
	 * @param args
	 * @throws IncorrectCellValueException 
	 * @throws CellIsEmptyException 
	 * @throws CellIsFilledException 
	 */
	public static void printState(Cell[][] cells, PrintWriter PW) throws CellIsEmptyException {
		for(int rowIndex = 0; rowIndex < cells.length; rowIndex++) {
			for(int columnIndex = 0; columnIndex < cells[rowIndex].length; columnIndex++)
				if(cells[rowIndex][columnIndex].isFilled())
					PW.print(cells[rowIndex][columnIndex].getValue());
				else
					PW.print("*");
			PW.print("\t");
			PW.println();
		}
		PW.println();
	}
	
	public static void main(String[] args) throws IncorrectCellValueException, CellIsFilledException, CellIsEmptyException, FileNotFoundException {
		Board B = new Board();
		Move P1 = new FirstPlayer();
		Move P2 = new SecondPlayer();
		File F = new File("D:\\output.txt");
		PrintWriter PW = new PrintWriter(F);
		int points1 = 0, points2 = 0;
		while(!B.endOfGame()) {
			points1 += B.fillCell(P1.nextMove(B), true);
			printState(B.getCells(), PW);
			points2 += B.fillCell(P2.nextMove(B), true);
			printState(B.getCells(), PW);
			PW.println("P1:  " + points1 + "\tP2:  " + points2);
		}
		PW.close();
		System.out.print("OK");
	}

}
