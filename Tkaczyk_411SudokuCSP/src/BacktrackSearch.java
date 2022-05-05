import java.util.ArrayList;

//  Author: Alex Tkaczyk
//  Course: CS 411
//  Instructor: Bing Liu
//  Project: Sudoku-CSP
/*  Class Description: 
 * 		BacktrackSearch Class checks for the validity of specified number placements
 * 		Runs a recursive backtrack algorithm to solve a Sudoku puzzle
 * 		 */

public class BacktrackSearch {
	
	// checkSafety(ArrayList<ArrayList<Integer>> grid, int row, int column, int newNumber)
	// Checks validity of placing a number in the desired spot i.e. checks rows, columns, 3x3s
	// Input: ArrayList<ArrayList<Integer>>, int, int, int
	// Output:  true if safe to place number in selected location
	//			false if unsafe (conflicting numbers in either row,column, 3x3)
	public static boolean checkSafety(ArrayList<ArrayList<Integer>> grid, int row, int column, int newNumber) {
		
		// Check for row consistency
		for (int i = 0; i < 9; i++) {
			if (grid.get(row).get(i) == newNumber) {
				return false;
			}
		}
		
		// Check for column consistency
		for (int i = 0; i < 9; i++) {
			if (grid.get(i).get(column) == newNumber) {
				return false;
			} 
		}
		
		// Check for 3x3 grid consistency
		int startRow = row - (row % 3);
		int startCol = column - (column % 3);
		for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid.get(i + startRow).get(j + startCol) == newNumber)
                    return false;
            }
		}
		
		return true;
	}

	// ArrayList<ArrayList<Integer>> runSearch(ArrayList<ArrayList<Integer>> originalGrid, int row, int column)
	// Takes in an incomplete Sudoku puzzle and checks all possible combinations to solve said puzzle
	// Input: ArrayList<ArrayList<Integer>>, int, int
	// Output: null if no solution found
	//			solvedGrid as ArrayList<ArrayList<Integer>> if solution exists
	public static ArrayList<ArrayList<Integer>> runSearch(ArrayList<ArrayList<Integer>> originalGrid, int row, int column) {
		ArrayList<ArrayList<Integer>> solvedGrid = originalGrid;
		
		if (row == 8 && column == 9) // Solved Puzzle
            return solvedGrid;
		
		// Once column is reached, move to start of next row
		if (column == 9) {
            row++;
            column = 0;
        }
		// If current grid space is filled, continue to next -- consistency check for empty space
		if (originalGrid.get(row).get(column) != 0)
            return runSearch(solvedGrid, row, column + 1);
		
		// Try potential numbers in empty space
		for (int i = 1; i < 10; i++) {
			if (checkSafety(solvedGrid, row, column, i) == true) {
				solvedGrid.get(row).set(column, i);
				if (runSearch(solvedGrid, row, column + 1) != null)
                    return solvedGrid;
			}
			solvedGrid.get(row).set(column, 0); // resets element to 0, since incorrect
		}
		
		return null;
	}
}
