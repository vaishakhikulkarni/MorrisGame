package AI;

import java.util.ArrayList;
import java.util.List;

//Common functions
public class MorrisGameBoard {

	// Function for opening of the game
	public static ArrayList<MorrisGameBoardState> GenerateMovesOpening(MorrisGameBoardState openingboard) {
		return GenerateAdd(openingboard);
	}

	// Function to flip the board and then perform the Opening for Black
	public static ArrayList<MorrisGameBoardState> GenerateMovesOpeningBlack(MorrisGameBoardState board) {
		int i = 0;
		MorrisGameBoardState tempb = board.FlipBoard();
		ArrayList<MorrisGameBoardState> moves = GenerateMovesOpening(tempb);
		for (i = 0; i < moves.size(); i++) {
			MorrisGameBoardState mg= moves.get(i);
			moves.set(i, mg.FlipBoard());
		}
		return moves;
	}

	// List of all possible additions for white on the board
	public static ArrayList<MorrisGameBoardState> GenerateAdd(MorrisGameBoardState detailBoard) {
		int i = 0;
		ArrayList<MorrisGameBoardState> l = new ArrayList<MorrisGameBoardState>();
		for (i = 0; i < detailBoard.gamePositions.size(); i++) {
			if (detailBoard.get(i) == choice.X) {
				ArrayList<Character> out = new ArrayList<Character>();
				for (choice pos : detailBoard.gamePositions) {
					out.add(pos.val);
				}
				MorrisGameBoardState mg = new MorrisGameBoardState(out);
				mg.set(i, choice.W);
				if (closeMill(i, mg)) { // Check if close Mill is formed
					l = GenerateRemove(mg, l);
				} else {
					l.add(mg);
				}
			}
		}
		return l;
	}

	// Check if close mill is present
	public static boolean closeMill(int i, MorrisGameBoardState detailBoard) {
		choice C = detailBoard.get(i);
		if (C == choice.X) {
			return false;
		} else {
			return checkAllMills(i, C, detailBoard);
		}
	}

	// Combinations of mills
	public static boolean checkAllMills(int i, choice C, MorrisGameBoardState mgs) {

		switch (i) {
		case 0: {
			if ((mgs.get(1) == C && mgs.get(2) == C) || (mgs.get(8) == C && mgs.get(20) == C)
					|| (mgs.get(3) == C && mgs.get(6) == C))
				return true;
			else
				return false;
		}
		case 1: {
			if ((mgs.get(0) == C && mgs.get(2) == C))
				return true;
			else
				return false;
		}
		case 2: {
			if ((mgs.get(0) == C && mgs.get(1) == C) || (mgs.get(5) == C && mgs.get(7) == C)
					|| (mgs.get(13) == C && mgs.get(22) == C))
				return true;
			else
				return false;
		}
		case 3: {
			if ((mgs.get(0) == C && mgs.get(6) == C) || (mgs.get(4) == C && mgs.get(5) == C)
					|| (mgs.get(9) == C && mgs.get(17) == C))
				return true;
			else
				return false;
		}
		case 4: {
			if ((mgs.get(3) == C && mgs.get(5) == C))
				return true;
			else
				return false;
		}
		case 5: {
			if ((mgs.get(3) == C && mgs.get(4) == C) || (mgs.get(2) == C && mgs.get(7) == C)
					|| (mgs.get(12) == C && mgs.get(19) == C))
				return true;
			else
				return false;
		}
		case 6: {
			if ((mgs.get(0) == C && mgs.get(3) == C) || (mgs.get(10) == C && mgs.get(14) == C))
				return true;
			else
				return false;
		}
		case 7: {
			if ((mgs.get(2) == C && mgs.get(5) == C) || (mgs.get(11) == C && mgs.get(16) == C))
				return true;
			else
				return false;
		}
		case 8: {
			if ((mgs.get(0) == C && mgs.get(20) == C) || (mgs.get(9) == C && mgs.get(10) == C))
				return true;
			else
				return false;
		}
		case 9: {
			if ((mgs.get(8) == C && mgs.get(10) == C) || (mgs.get(3) == C && mgs.get(17) == C))
				return true;
			else
				return false;
		}
		case 10: {
			if ((mgs.get(8) == C && mgs.get(9) == C) || (mgs.get(6) == C && mgs.get(14) == C))
				return true;
			else
				return false;
		}
		case 11: {
			if ((mgs.get(7) == C && mgs.get(16) == C) || (mgs.get(12) == C && mgs.get(13) == C))
				return true;
			else
				return false;
		}
		case 12: {
			if ((mgs.get(11) == C && mgs.get(13) == C) || (mgs.get(5) == C && mgs.get(9) == C))
				return true;
			else
				return false;
		}
		case 13: {
			if ((mgs.get(11) == C && mgs.get(12) == C) || (mgs.get(2) == C && mgs.get(22) == C))
				return true;
			else
				return false;
		}
		case 14: {
			if ((mgs.get(17) == C && mgs.get(20) == C) || (mgs.get(15) == C && mgs.get(16) == C)
					|| (mgs.get(6) == C && mgs.get(14) == C))
				return true;
			else
				return false;
		}
		case 15: {
			if ((mgs.get(14) == C && mgs.get(16) == C) || (mgs.get(18) == C && mgs.get(21) == C))
				return true;
			else
				return false;
		}
		case 16: {
			if ((mgs.get(14) == C && mgs.get(15) == C) || (mgs.get(19) == C && mgs.get(22) == C)
					|| (mgs.get(7) == C && mgs.get(11) == C))
				return true;
			else
				return false;
		}
		case 17: {
			if ((mgs.get(3) == C && mgs.get(9) == C) || (mgs.get(14) == C && mgs.get(20) == C)
					|| (mgs.get(18) == C && mgs.get(19) == C))
				return true;
			else
				return false;
		}
		case 18: {
			if ((mgs.get(17) == C && mgs.get(19) == C) || (mgs.get(15) == C && mgs.get(21) == C))
				return true;
			else
				return false;
		}
		case 19: {
			if ((mgs.get(17) == C && mgs.get(18) == C) || (mgs.get(16) == C && mgs.get(22) == C)
					|| (mgs.get(5) == C && mgs.get(12) == C))
				return true;
			else
				return false;
		}
		case 20: {
			if ((mgs.get(0) == C && mgs.get(8) == C) || (mgs.get(14) == C && mgs.get(17) == C)
					|| (mgs.get(21) == C && mgs.get(22) == C))
				return true;
			else
				return false;
		}
		case 21: {
			if ((mgs.get(20) == C && mgs.get(22) == C) || (mgs.get(15) == C && mgs.get(18) == C))
				return true;
			else
				return false;
		}
		case 22: {
			if ((mgs.get(2) == C && mgs.get(13) == C) || (mgs.get(16) == C && mgs.get(19) == C)
					|| (mgs.get(20) == C && mgs.get(21) == C))
				return true;
			else
				return false;
		}
		default:
			return false;

		}
	}

	// All possible removals that white perform on the board
	public static ArrayList<MorrisGameBoardState> GenerateRemove(MorrisGameBoardState detailBoard,
			ArrayList<MorrisGameBoardState> l) {
		int i = 0;
		for (i = 0; i < detailBoard.gamePositions.size(); i++) {
			if (detailBoard.get(i) == choice.B) {
				if (!closeMill(i, detailBoard)) { // check whether closeMill is
													// formed
					ArrayList<Character> out = new ArrayList<Character>();
					for (choice pos : detailBoard.gamePositions) {
						out.add(pos.val);
					}
					MorrisGameBoardState mg = new MorrisGameBoardState(out);
					mg.set(i, choice.X);
					l.add(mg);
				}
			}
		}
		return l;
	}

	// All possibles moves for white on the given board
	public static ArrayList<MorrisGameBoardState> GenerateMove(MorrisGameBoardState detailBoard) {
		ArrayList<MorrisGameBoardState> l = new ArrayList<MorrisGameBoardState>();
		int i = 0;
		for (i = 0; i < detailBoard.gamePositions.size(); i++) {
			if (detailBoard.get(i) == choice.W) {
				List<Integer> n = neighbors(i);
				for (int j : n) {
					if (detailBoard.get(j) == choice.X) {
						ArrayList<Character> out = new ArrayList<Character>();
						for (choice pos : detailBoard.gamePositions) {
							out.add(pos.val);
						}
						MorrisGameBoardState mg = new MorrisGameBoardState(out);
						mg.set(i, choice.X);
						mg.set(j, choice.W);
						if (closeMill(j, mg)) {
							l = GenerateRemove(mg, l);
						} else {
							l.add(mg);
						}
					}
				}
			}
		}
		return l;
	}

	// To find all neighbors
	public static ArrayList<Integer> neighbors(int i) {
		ArrayList<Integer> adj = new ArrayList<Integer>();
		switch (i) {
		case 0: {
			adj.add(1);
			adj.add(3);
			adj.add(8);
			return adj;
		}
		case 1: {
			adj.add(0);
			adj.add(2);
			adj.add(4);
			return adj;
		}
		case 2: {
			adj.add(1);
			adj.add(5);
			adj.add(13);
			return adj;
		}
		case 3: {
			adj.add(0);
			adj.add(4);
			adj.add(6);
			adj.add(9);
			return adj;
		}
		case 4: {
			adj.add(1);
			adj.add(3);
			adj.add(5);
			return adj;
		}
		case 5: {
			adj.add(2);
			adj.add(4);
			adj.add(7);
			adj.add(12);
			return adj;
		}
		case 6: {
			adj.add(3);
			adj.add(7);
			adj.add(10);
			return adj;
		}
		case 7: {
			adj.add(5);
			adj.add(6);
			adj.add(11);
			return adj;
		}
		case 8: {
			adj.add(0);
			adj.add(9);
			adj.add(20);
			return adj;
		}
		case 9: {
			adj.add(3);
			adj.add(8);
			adj.add(10);
			adj.add(17);
			return adj;
		}
		case 10: {
			adj.add(6);
			adj.add(9);
			adj.add(14);
			return adj;
		}
		case 11: {
			adj.add(7);
			adj.add(12);
			adj.add(16);
			return adj;
		}
		case 12: {
			adj.add(5);
			adj.add(11);
			adj.add(13);
			adj.add(19);
			return adj;
		}
		case 13: {
			adj.add(2);
			adj.add(12);
			adj.add(22);
			return adj;
		}
		case 14: {
			adj.add(10);
			adj.add(15);
			adj.add(17);
			return adj;
		}
		case 15: {
			adj.add(14);
			adj.add(16);
			adj.add(18);
			return adj;
		}
		case 16: {
			adj.add(11);
			adj.add(15);
			adj.add(19);
			return adj;
		}
		case 17: {
			adj.add(9);
			adj.add(14);
			adj.add(18);
			adj.add(20);
			return adj;
		}
		case 18: {
			adj.add(15);
			adj.add(17);
			adj.add(19);
			adj.add(21);
			return adj;
		}
		case 19: {
			adj.add(12);
			adj.add(16);
			adj.add(18);
			adj.add(22);
			return adj;
		}
		case 20: {
			adj.add(8);
			adj.add(17);
			adj.add(21);
			return adj;
		}
		case 21: {
			adj.add(18);
			adj.add(20);
			adj.add(22);
			return adj;
		}
		case 22: {
			adj.add(13);
			adj.add(19);
			adj.add(21);
			return adj;
		}
		default:
			return (new ArrayList<Integer>());
		}

	}

	// Generate move for midgameEndgame phase for white
	public static ArrayList<MorrisGameBoardState> GenerateMovesMidgameEndgame(MorrisGameBoardState detailBoard) {

		// Variables to calculate the count of white cells
		int cntw = 0;
		// Count number of white
		for (choice x : detailBoard.gamePositions) {
			if (x == choice.W)
				cntw++;
		}
		if (cntw == 3)
			return GenerateHopping(detailBoard);
		else
			return GenerateMove(detailBoard);

	}

	// Flip the board and then Generate move for midgameEndgame phase for blank
	public static ArrayList<MorrisGameBoardState> GenerateMovesMidgameEndgameBlack(MorrisGameBoardState detailBoard) {
		int i = 0;
		// flip the board
		MorrisGameBoardState tempb = detailBoard.FlipBoard();
		ArrayList<MorrisGameBoardState> moves = GenerateMovesMidgameEndgame(tempb);
		ArrayList<MorrisGameBoardState> out = new ArrayList<MorrisGameBoardState>();

		while (i < moves.size()) {
			MorrisGameBoardState mg = moves.get(i);
			out.add(mg.FlipBoard());
			i++;
		}
		return out;
	}

	// Decide how much hopping needs to be done when white can hop where number
	// of white is 2
	public static ArrayList<MorrisGameBoardState> GenerateHopping(MorrisGameBoardState detailBoard) {
		ArrayList<MorrisGameBoardState> l = new ArrayList<MorrisGameBoardState>();
		int i = 0, j = 0;
		for (i = 0; i < detailBoard.gamePositions.size(); i++) {
			if (detailBoard.get(i) == choice.W) {
				for (j = 0; j < detailBoard.gamePositions.size(); j++) {
					if (detailBoard.get(j) == choice.X) {
						ArrayList<Character> out = new ArrayList<Character>();
						for (choice pos : detailBoard.gamePositions) {
							out.add(pos.val);
						}
						MorrisGameBoardState mg = new MorrisGameBoardState(out);
						mg.set(i, choice.X);
						mg.set(j, choice.W);
						if (closeMill(j, mg)) {
							GenerateRemove(mg, l);
						} else {
							l.add(mg);
						}
					}
				}
			}
		}
		return l;
	}
}

// Return object
class outputPattern {
	public int data, num;
	public MorrisGameBoardState state;
}

// to make a choice
enum choice {
	X('X'), B('B'), W('W');
	public final char val;

	choice(char val) {
		this.val = val;
	}
}

// Morris Game state
class MorrisGameBoardState {

	public ArrayList<choice> gamePositions = new ArrayList<choice>();

	public MorrisGameBoardState() {
		int i = 0;
		while (i < 23) { // set default all position values as X
			gamePositions.add(choice.X);
			i++;
		}
	}

	// Set values
	public MorrisGameBoardState(ArrayList<Character> inputBoard) {
		gamePositions = new ArrayList<choice>();
		for (char c : inputBoard) {
			if (c == 'W')
				gamePositions.add(choice.W);
			else if (c == 'B')
				gamePositions.add(choice.B);
			else
				gamePositions.add(choice.X);
		}
	}

	// Flip the board contents to perform operations for black
	public MorrisGameBoardState FlipBoard() {
		MorrisGameBoardState out = new MorrisGameBoardState();
		for (int i = 0; i < gamePositions.size(); i++) {
			choice val = gamePositions.get(i);
			if (val == choice.B) {
				out.set(i, choice.W);
			} else if (val == choice.W) {
				out.set(i, choice.B);
			} else {
				out.set(i, choice.X);
			}
		}
		return out;
	}

	// Get the value at position i
	public choice get(int i) {
		return gamePositions.get(i);
	}

	// Set value of particular position i with specific value
	public void set(int i, choice val) {
		gamePositions.set(i, val);
	}

	// Perform toString n the object to display the output
	public String toString() {
		int i = 0;
		String s = " ";
		int n = gamePositions.size();
		while (i < n) {
			s = s + gamePositions.get(i).val;
			i++;
		}
		return (s);
	}
}
