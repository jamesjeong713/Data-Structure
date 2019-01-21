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

public class ManufacturedItem extends Item {

	private int[] itemsUsed; // to contain codes
	private int numItemsUsed; // to count how many numbers of items are using

	public ManufacturedItem() {
		numItemsUsed = 0;
		itemsUsed = new int[10];
	}

	@Override
	public boolean addItem(Scanner input) {
		super.addItem(input); // call super class
		int temp = 0;
		int i = 0;
		
		while (i<10) {
			System.out.print("Enter up to 10 codes used in this item (-1 to quit): ");
			if (!input.hasNextInt()) {
				input.nextLine();
				System.err.print("Invalid code...please enter integer" + " greater than 0\n");
			} else {
				temp = input.nextInt();
				if (temp == -1) // back to menu
					break;
				else if (temp > 0) {
					itemsUsed[i] = temp;
					numItemsUsed++;
					i++;
				} else {
					System.err.print("Invalid code...please enter integer" + " greater than 0\n");
				}
			}
		}
		return false;
	}

	@Override
	public String toString() {
		String format = "Codes used: ";
		for (int i = 0; i < numItemsUsed; i++) {
			format = format + itemsUsed[i] + ", ";
		}
		return super.toString() + format;
	}
}
