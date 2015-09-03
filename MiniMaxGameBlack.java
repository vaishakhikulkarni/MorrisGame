package AI;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MiniMaxGameBlack {

	// Initialize values for min and max values
	final static int min = Integer.MIN_VALUE;
	final static int max = Integer.MAX_VALUE;

	public static void main(String[] args) {

		File input = null;
		BufferedReader br = null;
		File output = null;
		BufferedWriter brw = null;

		// Read inputs from the command line
		String boardInput = args[0]; // Input File
		String boardOutput = args[1]; // Output File
		int depth = Integer.parseInt(args[2]); // Depth

		ArrayList<Character> board = new ArrayList<Character>(); // ArrayList to
																	// store the
																	// input
																	// characters
		try {
			// Read data from the input file specified in the command line
			input = new File(boardInput);
			br = new BufferedReader(new FileReader(input));
			String line = br.readLine();

			for (char a : line.toCharArray())
				board.add(a);

		} catch (FileNotFoundException e) // File not find
		{
			System.out.println("Please make sure the directory file is actually there.");
		} catch (IOException ex) // Handle IO exception error
		{
			Logger.getLogger(BufferedReader.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			try {
				br.close(); // Close BufferReader
			} catch (IOException ex) // IO Exception error
			{
				Logger.getLogger(BufferedReader.class.getName()).log(Level.SEVERE, null, ex);
			}
		}

		// Create MorrisGame Board
		MorrisGameBoardState detailsBoard = new MorrisGameBoardState(board);

		// Perform the Alpha beta pruning
		outputPattern o = MiniMaxGameBlackPlay(depth, detailsBoard, 1);

		try {
			// Write he output in the output file specified in the command line
			output = new File(boardOutput);
			brw = new BufferedWriter(new FileWriter(output));
			int data = o.data;
			int numNodes = o.num;
			MorrisGameBoardState state = (MorrisGameBoardState) o.state;
			brw.write("BoardPosition: " + state + "\t\t\t\t" + "Positions Evaluated by static estimation: " + numNodes + "\t\t\t\t"
					+ "MINIMAX estimate: " + data);

		} catch (IOException ex) {// Handle IO Exception error
			Logger.getLogger(BufferedWriter.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			try {
				brw.close(); // Close BufferReader
			} catch (IOException ex) // IO Exception error
			{
				Logger.getLogger(BufferedWriter.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	// Finding optimal move with MiniMax function
	public static outputPattern MiniMaxGameBlackPlay(int d, MorrisGameBoardState detailsBoard, int flag) {
		outputPattern in = new outputPattern();
		ArrayList<MorrisGameBoardState> gameList = null;
		outputPattern op = new outputPattern();

		// Base case for termination
		if (d == 0) {
			// Variables to calculate the count of white and black cells
			int cntw = 0, cntb = 0, finalcount = 0;
			// Static Estimation
			for (choice x : detailsBoard.gamePositions) {
				if (x == choice.W)
					cntw++;
				if (x == choice.B)
					cntb++;
			}
			finalcount = cntb - cntw;

			ArrayList<MorrisGameBoardState> l = MorrisGameBoard.GenerateMovesMidgameEndgame(detailsBoard.FlipBoard());
			// Perform static estimation for midgameEndgame phase for black move
			int listsize = l.size();
			if (cntw <= 2) {
				op.data = 10000;
			} else if (cntb <= 2) {
				op.data = -10000;
			} else if (listsize == 0) {
				op.data = 10000;
			} else {
				op.data = 1000 * (finalcount) - listsize;
			}
			op.num = op.num + 1;
			return op;
		}

		if (flag == 1) // Flag is true call GenerateMoveMidgameEndgameBlack for
						// Black
		{
			gameList = MorrisGameBoard.GenerateMovesMidgameEndgameBlack(detailsBoard);
			op.data = min;
		} else // Flag is false call GenerateMoveMidgameEndgame for White
		{
			gameList = MorrisGameBoard.GenerateMovesMidgameEndgame(detailsBoard);
			op.data = max;
		}

		for (MorrisGameBoardState mg : gameList) {

			if (flag == 1) { // To perform recursion and set the estimation
								// values
				in = MiniMaxGameBlackPlay(d - 1, mg, 0);
				if (in.data > op.data) {
					op.data = in.data;
					op.state = mg;
				}
				op.num = op.num + in.num;
			} else { // To perform recursion and set the estimation values
				in = MiniMaxGameBlackPlay(d - 1, mg, 1);
				if (in.data < op.data) {
					op.data = in.data;
					op.state = mg;
				}
				op.num = op.num + in.num;
			}
		}
		return op;
	}
}
