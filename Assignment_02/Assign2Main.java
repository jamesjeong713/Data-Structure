
/*********************************************************************************************
 * 
 * Student Name: Seongyeop Jeong (James)
 * Student Number: 040885882 
 * Course:  18W CST8130 - Data Structures
 * Date: 2018/03/09
 * Course name: Data Structures
 * Course section: CST8130_300 
 */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Assign2Main {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String userInputChoice = "";
		Inventory inventory = new Inventory(100);

		boolean swiches = true;

		while (swiches) {
			System.out.print("\nEnter 1 to add an item to inventory\n" + "2 to display current inventory\n"
					+ "3 buy some of an item \n" + "4 sell some of an item  \n" + "5 to load file\n"
					+ "6 to overwrite file\n" + "7 to exit\n");

			userInputChoice = input.next(); // first menu choice

			switch (userInputChoice) {

			case "1":
				inventory.addItem(input);
				break;

			case "2":
				System.out.print(inventory);
				break;

			case "3":
				inventory.updateQuantity(input, true);
				break;

			case "4":
				inventory.updateQuantity(input, false);
				break;

			case "5":
				Scanner inFile = openInputFile(input);
				if (inFile != null)
					inventory.initValuesFromFile(inFile);
				else
					System.err.println("Could not find file");
				break;

			case "6":
				FileWriter outFile = openOutputFile(input);
				if (outFile != null)
					inventory.overWriteToFile(outFile);
				else
					System.err.println("Could not find file");
				break;

			case "7":
				System.out.println("Have a great day");
				swiches = false;
				break;

			default:
				System.err.println("Please enter valid number for menu\n");
			}
		}
	}

	public static Scanner openInputFile(Scanner input) {

		String fileName = new String();
		Scanner inFile = null;

		System.out.print("\nEnter name of file to process: ");
		fileName = input.next();

		File file = new File(fileName);
		try {
			if (file.exists()) {
				inFile = new Scanner(file);
			}
			return inFile;
		} catch (IOException e) {
			System.out.println("Could not open file...." + fileName + "exiting");
			return null;
		}
	}// end openOutputFile method

	public static FileWriter openOutputFile(Scanner input) {

		String fileName = new String();
		FileWriter outFile = null;

		System.out.print("\nEnter name of file to write to: ");
		fileName = input.next();

		try {
			outFile = new FileWriter(fileName);
		} catch (IOException e) {
			System.err.println("Could not open file...." + fileName + "exiting");
		}
		return outFile;
	}// end openFile method
}
