package de.wwu.muli.showcase;
import de.wwu.muli.stub.Muli;
import de.wwu.muli.stub.Solution;
class EightQueens {
	public static void eightQueens(int n) {
		int[] queens = new int[n] free;
		// try:
		boolean[] s = Muli.muli((queens) -> modelConstraints(queens));
		// allSolutions:
		Solution<Integer>[] solutions = Muli.search( (queens, Muli.Search.ALL, Muli.Strategy.IterativeDeepening) -> {
				printSolutions(queens);
				System.out.println(); 
				});
	}
	public static void modelConstraints(int[] queens) {
		// Constraint 0: Rows must be different.
		// By definition; no code required.
		for (int i = 0; i < queens.length; i++) {
			// Constraint 1: Limit domain.
			queens[i] >= 0 && queens[i] < queens.length;
			for (int j = i+1; j < queens.length; j++) {
				// Constraint 1: Cols must be different.
				queens[j] != queens[i];
				// Constraint 2: Descending diagonals must be different.
				queens[i] - queens[j] != i - j;
				// Constraint 3: Ascending diagonals must be different.
				queens[i] - queens[j] != j - i;
			}
		}
	}
	public static void printSolutions(int[] queens) {
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
		eightQueens(8);
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