/*********************************************************************************************
 * 
 * Student Name: Seongyeop Jeong (James)
 * Student Number: 040885882 
 * Course:  18W CST8130 - Data Structures
 * Date: 2018/03/09
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

	public ManufacturedItem (int code, String name, int quantityInStock, float price, int[] itemUsed) {
		super(code, name, quantityInStock, price);
		itemsUsed = new int[10];
		
		for(int i = 0; i < itemUsed.length; i++) { 
			if (itemUsed[i] != 0) {
				if (itemUsed[i] == -1) {
					break;
				}
				this.itemsUsed[i] = itemUsed[i];
				numItemsUsed++;	
			}
		}
	}

	@Override
	public boolean addItem(Scanner input) {
		super.addItem(input); // call super class
		int temp = 0;
		int i = 0;
		
		System.out.print("Enter up to 10 codes used in this item (-1 to quit): ");
		while (i < itemsUsed.length) {

			if (!input.hasNextInt()) {
				input.nextLine();
				System.err.print("Invalid code...please enter integer" + " greater than 0\n");
			} else {
				
				temp = input.nextInt();
				if (temp == -1) // back to menu
					break;
				else if (temp > 0) {
					for (int j = 0; j < itemsUsed.length; j++) {
						while (itemsUsed[j] == temp) {
							System.err.print("Please enter valid code.. this code is already in inventory\n");
							System.out.print("Enter up to 10 codes used in this item (-1 to quit): ");
							temp = input.nextInt();
						} 
					}
					itemsUsed[i] = temp;
					numItemsUsed++;
					i++;
				}
			}
		}
		return false;
	}
	
	@Override
	public String toString() {
		String format = " Codes used: ";
		for (int i = 0; i < numItemsUsed; i++) {
			format = format + itemsUsed[i];
			// check if it should not be "," in end of the line
			if (i != numItemsUsed-1) {
				format = format + ", ";
			}
		}
		return super.toString() + format + "\n";
	}
	
	@Override
	public String toFile() {
		
		String itemsUsedCount = "";

		for (int i = 0; i < numItemsUsed; i++) {
			if (itemsUsed[i] != 0) {
				itemsUsedCount += itemsUsed[i];
				// check if it should not be " " in end of the line
				if (i != numItemsUsed-1) {
					itemsUsedCount += " ";
				}
			} else {
				break;
			}
		}
		return "m " + super.toFile() + " " + itemsUsedCount + " -1";
	}
}
