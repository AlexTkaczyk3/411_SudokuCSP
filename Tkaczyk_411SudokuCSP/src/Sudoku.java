import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

//  Author: Alex Tkaczyk
//  Course: CS 411
//  Instructor: Bing Liu
//  Project: Sudoku-CSP
/*  Class Description: 
 * 		Sudoku Class accepts input from terminal and files
 * 		Creates a 2D ArrayList using the input to depict the original unsolved puzzle
 * 		Runs a BackTrack Search on the unsolved puzzle
 * 		Writes the solved puzzle back out to the designated output device i.e. terminal, file
 * 
 * 		Exit Codes: 1--Input error
 * 					2--File output error
 * 					3--File read error
 * 		 */

public class Sudoku {
	
	static ArrayList<ArrayList<Integer>> originalGrid = new ArrayList<ArrayList<Integer>>(); // 2D ArrayList to store original sudoku grid by row
	static ArrayList<ArrayList<Integer>> solutionGrid = new ArrayList<ArrayList<Integer>>(); // 2D ArrayList to store solution sudoku grid by row
	
	
	// terminalInput()
	// parse through terminal input and populates 2D ArrayList originalGrid
	// Input: void
	// Output: void
	public static void terminalInput() {
		System.out.println("Input puzzle data by row i.e. |8|||9|3||||2|");
		Scanner puzzleScanner = new Scanner(System.in);
		for (int i = 0; i < 9; i++) {
			ArrayList<Integer> newRow = new ArrayList<Integer>();
			String puzzleRow = puzzleScanner.nextLine();
			// Parse through puzzle Row
			for (int j = 0; j < puzzleRow.length() - 1; j++) {
				Boolean flag = Character.isDigit(puzzleRow.charAt(j));
		         if (flag) {
		        	 newRow.add(Character.getNumericValue(puzzleRow.charAt(j)));
		         } else {
		        	 if (!Character.isDigit(puzzleRow.charAt(j + 1))) {
		        		 newRow.add(0);
		        	 }
		         }
			}
			for (int j = 0; j < newRow.size(); j++) {
				System.out.print(newRow.get(j) + " ");
			}
			originalGrid.add(newRow);
			
		}
	}
	
	// fileInput()
	// parse through file input and populates 2D ArrayList originalGrid
	// Input: void
	// Output: void
	public static void fileInput() {
		try {
			System.out.print("Input filename: ");
			Scanner filenameReader = new Scanner(System.in);
			String filename = filenameReader.nextLine();
			//System.out.println("filename is: " + filename + "."); //--- to test for proper filename input (no \n character captured)
			File myFile = new File(filename);
		    Scanner gridReader = new Scanner(myFile);
		    while (gridReader.hasNextLine()) {
		    	ArrayList<Integer> newRow = new ArrayList<Integer>();
				String puzzleRow = gridReader.nextLine();
				// Parse through puzzle Row
				for (int j = 0; j < puzzleRow.length() - 1; j++) {
					Boolean flag = Character.isDigit(puzzleRow.charAt(j));
			         if (flag) {
			        	 newRow.add(Character.getNumericValue(puzzleRow.charAt(j)));
			         } else {
			        	 if (!Character.isDigit(puzzleRow.charAt(j + 1))) {
			        		 newRow.add(0);
			        	 }
			         }
				}
				originalGrid.add(newRow);
		    	}
		    gridReader.close(); // Potentially delete closing of scanner
		    } catch (FileNotFoundException e) {
		      System.out.println("File read error.");
		      e.printStackTrace();
		      System.exit(3);
		    }
	}
	
	// terminalOutput()
	// display contents of solutionGrid to terminal
	// Input: void
	// Output: void
	public static void terminalOutput() {
		for (int i = 0; i < 10; i++) {
			System.out.print("|");
			ArrayList<Integer> solutionRow = solutionGrid.get(i);
			for (Integer digit : solutionRow) {
				System.out.print(digit + "|");
			}
			System.out.println();
		}
	}
	
	// fileOutput(boolean soultionFound)
	// write contents of solutionGrid to solution.txt
	// Input: boolean
	// Output: void
	public static void fileOutput(boolean solutionFound) {
		// Prepare solution file to be written to
		try {
			File myObj = new File("solution.txt");
			if (myObj.createNewFile()) {
				System.out.println("File 'solution.txt' created.");
			} else {
				System.out.println("File 'solution.txt' already exists.");
		    }
		} catch (IOException e) {
			System.out.println("File creation error");
		    e.printStackTrace();
		    System.exit(2);
		}
		// Write to solution file
		try {
			BufferedWriter myWriter = new BufferedWriter(new FileWriter("solution.txt"));
			if (solutionFound == false) {
				myWriter.write("No solution exists");
			} else {
				for (int i = 0; i < 9; i++) {
					myWriter.write("|");
					ArrayList<Integer> solutionRow = solutionGrid.get(i);
					for (Integer digit : solutionRow) {
						myWriter.write(digit + "|");
    	    		}
					myWriter.newLine();
  				}
			}
    	    myWriter.close();
    	    } catch (IOException e) {
    	    	System.out.println("File writing error");
    	    	e.printStackTrace();
    	        System.exit(2);
    	    }
	}
	
	public static void main(String[] args) {
		boolean terminalBool = false;
		boolean fileBool = false;

		// Get user's preferred data input type
		System.out.println("For terminal input, type 'T'; For file input, type 'F'");
		Scanner inputScanner = new Scanner(System.in);
		String inputType = inputScanner.nextLine();
		if (inputType.charAt(0) == 'T') {
			terminalBool = true; 
		} else if (inputType.charAt(0) == 'F') {
			fileBool = true;
		} else {
			System.out.println("Incorrect Input Type -- scannerInput retreival");
			System.out.println("Reacieved scanner input: " + inputType);
			System.exit(1);
		}
		
		// Form original sudoku puzzle based off user input
		if (terminalBool == true) {
			terminalInput();
		} else if (fileBool == true) {
			fileInput();
		} else {
			System.out.println("Input type error -- inputBoolean check: Running Input Func");
			System.exit(1);
		}
		
		// Run BackTrackSearch and find solution puzzle
		solutionGrid = BacktrackSearch.runSearch(originalGrid, 0, 0);
		
		if (terminalBool == true) {  // Printing solutionGrid into terminal
			if (solutionGrid == null ) {
				System.out.println("No Solution Exists");
			} else {
				terminalOutput();
			}
		} else if (fileBool == true) {  // create and output solution into a new file
			if 	(solutionGrid == null ) {
				fileOutput(false);
			} else {
				fileOutput(true);
			}
		} else {
			System.out.println("Input type error -- inputBoolean check: solutionOutput");
			System.exit(1);
		}
	}
}
