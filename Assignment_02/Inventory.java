
/*********************************************************************************************
 * 
 * Student Name: Seongyeop Jeong (James)
 * Student Number: 040885882 
 * Course:  18W CST8130 - Data Structures
 * Date: 2018/03/09
 * Course name: Data Structures
 * Course section: CST8130_300 
 */

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Inventory {
	private ArrayList<Item> inventory;
	// private int numItems;

	public Inventory() {
		inventory = new ArrayList<Item>();
		// numItems = 0; // this variable is for the count of user's items
	}

	public Inventory(int numArray) {
		if (numArray < 0) {
			numArray = 0;
		}
		inventory = new ArrayList<Item>();
		// numItems = 0;
	}

	public boolean addItem(Scanner input) {

		Item tempItems = null;
		String userInput = "";
		System.out.print("Do you wish to add a purchased item(enter P/p) \n");
		System.out.print("or manufactrued (enter anything else)?: ");
		userInput = input.next();

		if (userInput.equals("P") || userInput.equals("p")) {
			tempItems = new PurchasedItem();
		} else {
			tempItems = new ManufacturedItem();
		}

		// check itemCode if there is same one.
		if (tempItems.inputCode(input)) {
			// tempItems.addItem(input);
			callingBinarySearch(tempItems);
		} else {
			return false;
		}
		return false;
	}

	public int alreadyExists(Item item) {
		int index = -1;
		for (int count = 0; count < inventory.size(); count++) {
			// if itemCode is same with another itemCode,
			if (inventory.get(count).isEqual(item)) {
				index = count;
				break;
			}
		}
		// default has to be -1
		return index;
	}

	public boolean callingBinarySearch(Item tempItems) {
		if (alreadyExists(tempItems) == -1) {

			if (binarySearch(tempItems) == -1) {
				inventory.add(tempItems);
			} else {
				inventory.add(binarySearch(tempItems), tempItems);
			}
			return true;
		} else {
			System.out.println("Item already in inventory");
			return false;
		}
	}

	public int binarySearch(Item items) {
		int first = 0;
		int last = inventory.size() - 1;
		int mid = -1;

		while (first <= last) {
			mid = (first + last) / 2;

			if (inventory.get(mid).checkValue(items)) {
				last = mid - 1;
			} else if (!inventory.get(mid).checkValue(items)) {
				first = mid + 1;
			} else {
				return mid;
			}
		}
		return first;
	}

	public boolean updateQuantity(Scanner input, boolean checkType) {
		boolean loopCode = true;
		boolean loopQuantity = true;
		int itemCodeForCheck = 0;
		int updateQuantity = 0;
		int index = 0;
		Item checkItem = null;

		while (loopCode) {
			System.out.print("Enter valid item code: ");

			if (!input.hasNextInt()) {
				input.next();
				System.err.print("Invalid Code...please enter integer greater than 0\n");
			} else {
				// user input for itemCode
				itemCodeForCheck = input.nextInt();
				checkItem = new Item(itemCodeForCheck);
				index = alreadyExists(checkItem);
				if (alreadyExists(checkItem) != -1) {

					System.out.print("Enter valid quantity : ");
					while (loopQuantity) {
						if (!input.hasNextInt()) {
							input.next();
							System.err.print("Invalid quantity...please enter integer greater than 0\n");
						} else {
							updateQuantity = input.nextInt();
							
							// buying interface
							if (checkType == true) {
								// alreadyExists(object) method is int. return value
								// will be (int) matched itemCode.
								inventory.get(index).updateItem(updateQuantity);
							}
							// selling interface
							else if (checkType == false) {
								boolean sellingLoop = true;
								while (sellingLoop) {
									if (updateQuantity < 0) {
										System.err.print("Invalid value... please enter valid value (it has to be greater than 0): ");
										updateQuantity = input.nextInt();
									} else {
										inventory.get(index).updateItem(-updateQuantity);
										sellingLoop = false;
									}
								}
							}
						}
						loopQuantity = false;
					}
				}
				loopCode = false;
			}
		}
		return loopCode;
	}

	public String toString() {
		
		String format = "";
		for (int i = 0; i < inventory.size(); i++) {
			format += inventory.get(i);
		}
		return format;
	}

	public boolean initValuesFromFile(Scanner inFile) {

		Item items = null;
		char type = 0;
		int code = 0, quantityInStock = 0;
		boolean error = false;
		String errorMessage = "";
		String name = "", supplier = "";
		// for itemUsed
		int itemUsedCode = 0;
		int[] itemUsed;
		float price = 0;
		
		System.out.print("Checking feasibility of file...\n");
		while (inFile.hasNext()) {
			// input variables
			if (inFile.hasNext()) {
				type = inFile.next().charAt(0);
			}
			
			if (inFile.hasNextInt()) {
				code = inFile.nextInt();
			} else {
				errorMessage += "\nInvalid Item Code..";
				error = true;
			}

			if (inFile.hasNext()) {
				name = inFile.next();
			} else {
				errorMessage += "\nInvalid Item name..";
				error = true;
			}

			if (inFile.hasNext()) {
				quantityInStock = inFile.nextInt();
			} else {
				errorMessage += "\nInvalid Item quantity..";
				error = true;
			}

			if (inFile.hasNext()) {
				price = inFile.nextFloat();
			} else {
				errorMessage += "\nInvalid Item price..";
				error = true;
			}

			if (error) {
				System.err.print(errorMessage + "\n");
				return error;
			}
			// select type
			if (type == 'p' || type == 'P') {
				
				if (inFile.hasNext()) {
					supplier = inFile.next();
					items = new PurchasedItem(code, name, quantityInStock, price, supplier);
				} else {
					System.err.println("Invalid Item supplier.");
					return false;
				}
			} else if (type == 'm' || type == 'M') {
				
				itemUsed = new int[10];

				for (int count = 0; count < 10; count++) {
					if (inFile.hasNextInt()) {
						itemUsedCode = inFile.nextInt();
						itemUsed[count] = itemUsedCode;
						// When itemUsedCode is '-1', break for loop like userInput -1.
						if (itemUsedCode == -1)
							break;
					} else {
						System.err.println("Invalid Item used code.");
						return false;
					}
				}
				items = new ManufacturedItem(code, name, quantityInStock, price, itemUsed);
			}
			callingBinarySearch(items);
		}
		System.out.print("File load Success..\n\n");
		return true;
	}

	public boolean overWriteToFile(FileWriter outFile) {
		
		String format = "";

		try {
			for (int i = 0; i < inventory.size(); i++) {
				format += inventory.get(i).toFile() + "\r\n";
			}
			outFile.append(format);
			outFile.close();

		} catch (IOException e) {
			System.err.println("Error writing to file");
			return false;
		}
		return true;
	}
}
