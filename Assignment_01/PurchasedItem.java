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

public class PurchasedItem extends Item {
	private String supplierName;

	public PurchasedItem() {
		super();
		supplierName = "";
	}

	@Override
	public boolean addItem(Scanner input) {
		super.addItem(input); // suer class from item class
		System.out.print("Enter the name of the supplier: ");
		supplierName = input.next();

		return false;
	}

	@Override
	public String toString() {
		String format = "Supplier: " + supplierName;
		return super.toString() + format;
	}
}
