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

public class PurchasedItem extends Item {
	private String supplierName;

	public PurchasedItem() {
//		super(); // need to test for removing
		supplierName = "";
	}
	
	public PurchasedItem(int code, String name, int quantityInStock, float price, String supplier) {
		super (code, name, quantityInStock, price);
		this.supplierName = supplier;
	}

	@Override
	public boolean addItem(Scanner input) {
		boolean isOk = super.addItem(input); // suer class from item class
		
		if (isOk) {
			System.out.print("Enter the name of the supplier: ");
			supplierName = input.next();
		}
		return isOk;
	}
	
	@Override
	public String toString() {
		return super.toString() + " " + supplierName + "\n";
	}
	
	@Override
	public String toFile() {
		return "p " + super.toFile() + " " + supplierName;
	}
}
