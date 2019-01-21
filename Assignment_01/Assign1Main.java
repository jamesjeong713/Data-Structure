/*********************************************************************************************
 * 
 * Student Name: Seongyeop Jeong (James)
 * Student Number: 040885882 
 * Course:  18W CST8130 - Data Structures
 * Date: 2018/02/03
 * Course name: Data Structures
 * Course section: CST8130_300 
 */

import java.util.Scanner;

public class Assign1Main {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String userInputChoice = "";
		Inventory inventory = new Inventory(100);

		boolean swiches = true;

		while (swiches) {
			System.out.print("\nEnter 1 to add an item to inventory\n" + "2 to display current inventory\n"
					+ "3 buy some of an item \n" + "4 sell some of an item  \n" + "5 to quit\n");

			userInputChoice = input.next(); // first menu choice

			switch (userInputChoice) {

			case "1":
			
				inventory.addItem(input);
				break;

			case "2":
				System.out.print(inventory.toString());
				break;

			case "3":
				inventory.updateQuantity(input, true);
				break;

			case "4":
				inventory.updateQuantity(input, false);
				break;

			case "5":
				swiches = false;
				break;

			default:
				System.err.println("Please enter valid number for menu\n");
			}
		}
	}
}
