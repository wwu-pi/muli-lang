package testfiles;

import de.wwu.muli.Find;
import de.wwu.muli.SearchStrategy;
import de.wwu.muli.Solution;
import de.wwu.muli.Muli;
import java.util.function.Supplier;



class EightQueens {

	private static int queen1 free;
	private static int queen2 free;
	private static int queen3 free;
	private static int queen4 free;
	private static int queen5 free;
	private static int queen6 free;
	private static int queen7 free;
	private static int queen8 free;
	private static Integer[] queens;

	public static void eightQueens() {

		EightQueens.queens = new Integer[]{queen1, queen2, queen3, queen4, queen5, queen6, queen7, queen8}; //free

		// allSolutions:
		Solution[] solutions = Muli.search(Find.All, SearchStrategy.IterativeDeepening, EightQueens::findSolutions); //() -> { return false; });
	}

	public static boolean modelConstraints(Integer[] queens) {
		// Constraint 0: Rows must be different.
		// By definition; no code required.
		boolean satisfied = true;
		for (int i = 0; i < queens.length; i++) {
			// Constraint 1: Limit domain.
			if (queens[i] >= 0 && queens[i] < queens.length) {
				for (int j = i + 1; j < queens.length; j++) {
					// Constraint 1: Cols must be different.
					satisfied = satisfied && queens[j] != queens[i] &&
						// Constraint 2: Descending diagonals must be different.
						queens[i] - queens[j] != i - j &&
						// Constraint 3: Ascending diagonals must be different.
						queens[i] - queens[j] != j - i;
					if (!satisfied) {
						return false;
					}
				}
			} else {
				return false;
			}
		}
		return satisfied;
	}

	public static Boolean findSolutions() {
		if (!modelConstraints(EightQueens.queens)) {
			return false;
		}
		printSolutions(EightQueens.queens);
		System.out.println();
		return true;
	}

	public static void printSolutions(Integer[] queens) {
		System.out.println("Solution:");
		for (int i = 0; i < queens.length; i++) { // Rows.
			for (int j = 0; j < queens.length; j++) { // Cols.
				if (queens[i] == j) {
					System.out.print("X");
				} else {
					System.out.print("_");
				}
			}
			System.out.println(); // Start new row.
		}
	}

	public static void main(String[] args) {
		eightQueens();
	}
}

// Variant 1 [discarded]: Search within method.
// PROBLEM: How to specify variables to label?
// 		a) Deduction from method content
//		b) Explicit (but where?)
// printSolutions(queens);
// @Muli.Search(solutions = Muli.Search.ALL) // Implicit backtracking after finding a solution.

// Variant 2: Search in open context, uses method.
// MERIT: Specification of variables can take place here.
// MERIT: It is not necessary to define a method just to perform a search.
// implemented; see above