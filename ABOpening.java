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

public class ABOpening {

	public static void main(String[] args) {
		// Initialize constant value
		int min = Integer.MIN_VALUE;
		int max = Integer.MAX_VALUE;

		File input = null;
		BufferedReader br = null;
		File output = null;
		BufferedWriter brw = null;

		String boardInput = args[0]; // Input File
		String boardOutput = args[1]; // Output File
		int depth = Integer.parseInt(args[2]); // Depth

		ArrayList<Character> board = new ArrayList<Character>(); // ArrayList to
																	// store the
																	// input
																	// characters
		try {
			// Read data from the input file specified on command prompt
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
			Logger.getLogger(BufferedWriter.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			try {
				br.close(); // Close BufferReader
			} catch (IOException ex) // IO Exception error
			{
				Logger.getLogger(BufferedWriter.class.getName()).log(Level.SEVERE, null, ex);
			}
		}

		// Create MorrisGame Board
		MorrisGameBoardState detailsBoard = new MorrisGameBoardState(board);

		// Perform the Alpha beta pruning
		outputPattern o = ABMiniMaxOpening(depth, detailsBoard, min, max, 1);

		try {
			// Write the output to the output specified on command line
			output = new File(boardOutput);
			brw = new BufferedWriter(new FileWriter(output));
			int data = o.data;
			int numNodes = o.num;
			MorrisGameBoardState state = (MorrisGameBoardState) o.state;
			brw.write("BoardPosition: " + state + "\t\t\t\t" + "Positions Evaluated by static estimation: " + numNodes + "\t\t\t\t"
					+ "Alpha Beta estimate: " + data);

		} catch (IOException ex) {
			Logger.getLogger(BufferedReader.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			try {
				brw.close(); // Close BufferReader
			} catch (IOException ex) // IO Exception error
			{
				Logger.getLogger(BufferedReader.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	// Finding optimal move with Alpha-Beta Pruning
	public static outputPattern ABMiniMaxOpening(int d, MorrisGameBoardState detailsBoard, int alpha, int beta,
			int flag) {
		outputPattern in = new outputPattern();
		ArrayList<MorrisGameBoardState> gameList;
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
			finalcount = cntw - cntb;
			op.data = finalcount;
			op.num = op.num + 1;
			return op;
		}

		if (flag == 1) // Flag is true call GenerateMoveOpening for White
			gameList = MorrisGameBoard.GenerateMovesOpening(detailsBoard);
		else // Flag is false call GenerateMoveOpening for Black
			gameList = MorrisGameBoard.GenerateMovesOpeningBlack(detailsBoard);

		for (MorrisGameBoardState mg : gameList) {
			if (flag == 1) { // To perform recursion and set values of alpha and
								// beta
				in = ABMiniMaxOpening(d - 1, mg, alpha, beta, 0);
				if (in.data > alpha) {
					alpha = in.data;
					op.state = mg;
				}
				op.num = op.num + in.num;
			} else { // To perform recursion and set values of alpha and beta
				in = ABMiniMaxOpening(d - 1, mg, alpha, beta, 1);
				if (in.data < beta) {
					beta = in.data;
					op.state = mg;
				}
				op.num = op.num + in.num;
			}
			if (alpha >= beta) { // If the alpha value is greater than the beta
									// value we should come out of the loop
				break;
			}
		}
		// Set the estimation values
		if (flag == 1)
			op.data = alpha;
		else
			op.data = beta;
		return op;
	}
}
