# 411_SudokuCSP
Author: Alex Tkaczyk
Course: CS 411
Instructor: Bing Liu
Project: Sudoku-CSP

												Sudoku README
--------------------------------------------------------------------------------------------------------------------------------------------------

(i) what each function is for
BacktrackSearch Class:
	checkSafety(ArrayList<ArrayList<Integer>> grid, int row, int column, int newNumber)
		Given the unsolved grid, the potential inputted number, and the position, 
		-the function checks the validity of the move i.e. row, column, 3x3
	runSearch(ArrayList<ArrayList<Integer>> originalGrid, int row, int column)
		Given the unsolved grid, and the starting position
		-(the backtrack search function)
		-checks whether a space is a 0, if so move on
		-recursive runs through the grid inputting potential numbers until a solution is found or determines no solution exists
		
Sudoku Class:
	terminalInput()
		parses through terminal input and populates 2D ArrayList originalGrid
	fileInput()
		parses through file input and populates 2D ArrayList originalGrid
	terminalOutput()
		display contents of solutionGrid (solution) to terminal
	fileOutput(boolean solutionFound)
		write contents of solutionGrid to solution.txt
	main(String[] args)
		overall function that handles overall logisitics i.e. I/O method, function calls
--
(ii) which functions are for consistency check
	checkSafety(ArrayList<ArrayList<Integer>> grid, int row, int column, int newNumber) ---- (check for valid moves)
	runSearch(ArrayList<ArrayList<Integer>> originalGrid, int row, int column) ---- (portion that checks for empty space)
--
(iii) which functions are for backtrack search
	runSearch(ArrayList<ArrayList<Integer>> originalGrid, int row, int column)

--------------------------------------------------------------------------------------------------------------------------------------------------
 Instructions on how program was ran on machine
 1. Open terminal
 2. Enter path where files are present
	2.1 Used: cd Desktop/Tkaczyk_411SudokuCSP/src
 3. Compile and link Sudoku and BacktrackSearch classes -- using: javac Sudoku.java BacktrackSearch.java
 4. Run program -- using: java Sudoku
 5. Follow terminal instructions (program output for proper program function)
 	5.1 Type preferred I/O type
 	5.2 Enter grid for terminal line by line or filename for file I/O
 6. Open solution.txt to view output solution
