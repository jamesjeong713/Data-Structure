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

public class Item {

	private int itemCode;
	private String itemName;
	private int itemQuantityInStock;
	private float itemPrice;

	public Item() {
		this.itemCode = 0;
		this.itemName = "";
		this.itemQuantityInStock = 0;
		this.itemPrice = 0;
	}

	public Item(int itemCode) {
		// it should be declare as pass by value
		// to access itemCode through object in every class
		this.itemCode = itemCode; 
	}

	public Item(int code, String name, int quantityInStock, float price) {
		this.itemCode = code;
		this.itemName = name;
		this.itemQuantityInStock = quantityInStock;
		this.itemPrice = price;
	}
	
	public boolean addItem(Scanner input) {
		// Whenever you fail to input your values as loopPrice and quantity
		// it should be required to user in same part which user was failed
		boolean loopPrice = true;
		boolean loopQuantity = true;
		// part of the input name
		System.out.print("Enter the name for the item: ");
		itemName = input.next();
		// part of the input quantity
		while (loopQuantity) {
			System.out.print("Enter the quantity for the item: ");	
			// this scanner class manage error part if it is invalid values for quantity
			if (!input.hasNextInt()) {
				input.next();
				System.err.print("Invalid quantity...please enter integer greater than 0\n");
			} else {
				itemQuantityInStock = input.nextInt();			
				// if the quantity value is less than 0, it should be warning message. 
				if (itemQuantityInStock < 0) {
					System.err.print("Invalid quantity...please enter integer" + " greater than 0\n");
				} else { loopQuantity = false; }
			}
		}
		// part of the input price
		// it is similar to the part of quantity while loop
		while (loopPrice) {
			System.out.print("Enter the price for the item: ");
			if (!input.hasNextFloat()) {
				input.next();
				System.err.print("\nInvalid price...please enter float greater than 0\n");
			} else {
				itemPrice = input.nextFloat();
				if (itemPrice < 0) {
					System.err.print("Invalid price...please enter integer" + " greater than 0\n");
				} else { loopPrice = false; }
			}
		}
		return true;
	}
	// this method is to merge your amount of quantity from buying interface.
	// this method is from inventory class's updateQuantity method 
	// (which checks itemCode before doing it.)
	public boolean updateItem(int amount) {
	
		if (amount == 0) {
			System.err.print("Error... amount value has to be more than 0\n");
			return false;
		} 
		if (itemQuantityInStock >= 0) {
			itemQuantityInStock += amount;
			// after selling, check if inventory has minus value. 
			// if so, return back.
			if (itemQuantityInStock < 0) {
				System.err.print("Error... could not sell item\n");
				itemQuantityInStock -= amount;
			}
			return true;
		} else if (itemQuantityInStock <= 0) {
			// selling part in inventory
			// when quantity is less than 0, it can't be sold.
			System.err.print("Error... could not sell item\n");
			// return back value
			itemQuantityInStock -= amount;
			return false;
		}
		return true;
	}
	// this method is to check itemCode in each object[] if it is same 
	public boolean isEqual(Item items) {
		return (this.itemCode == items.itemCode);
    }

    public boolean checkValue(Item items) {
    	if ((this.itemCode > items.itemCode)) {
    		return true;
    	}
    	else if ((this.itemCode < items.itemCode)) {
    		return false;
    	}
		return false;
    }
	// it is to prompt user input itemCode
	public boolean inputCode(Scanner input) {
		boolean loopCode = true;
		int temp;
		
		System.out.print("Enter the code for the item: ");
		while (loopCode) {
			
			if (!input.hasNextInt()) {
				input.next();
				System.err.print("Invalid code...please enter integer greater than 0\n");
			} else {
				temp=input.nextInt();
				// if (temp is greater than 0, itemCode = temp finally.
				// then boolean loopCode = false to stop looping
				if(temp > 0) {
					itemCode = temp;
					loopCode = false;
				} else {
					System.err.print("Invalid code...please enter integer greater than 0\n");
				}
			}
		}
		addItem(input);
		
		return true;
	}

	public String toString() {
		
		String format = "Item: " + itemCode + " " + itemName + " " + itemQuantityInStock + " price: " + itemPrice;
		return format;
	}
	
	public String toFile() {
		
		String format = itemCode + " " + itemName + " " + itemQuantityInStock + " " + itemPrice;
		return format;
	}
}
