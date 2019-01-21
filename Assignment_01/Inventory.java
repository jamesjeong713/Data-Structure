
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

public class Inventory {
	private Item[] inventory;
	private int numItems;

	public Inventory() {
		numItems = 0; // this variable is for the count of user's items
	}

	public Inventory(int numArray) {
		inventory = new Item[numArray];
	}

	public boolean addItem(Scanner input) {

		String userInput = "";
		System.out.print("Do you wish to add a purchased item(enter P/p) \n");
		System.out.print("or manufactrued (enter anything else)?: ");
		userInput = input.next();

		try {
			// when user has a lot of items than inventory,
			// throw to the array index out of bounds
			// to show the warning message.
			if (userInput.equals("P") || userInput.equals("p")) {
				inventory[numItems] = new PurchasedItem();
			} else {
				inventory[numItems] = new ManufacturedItem();
			}
		} catch (ArrayIndexOutOfBoundsException arrayIndex) {
			System.err.println("Error: your inventory is full");
			return false;
		}
		// user input part (itemCode)
		inventory[numItems].inputCode(input);
		// user input part (name, quantity, price and supplier name)
		inventory[numItems].addItem(input);
		// count user's item.
		numItems++;

		return true;
	}

	public String toString() {

		String format = "";
		for (int i = 0; i < numItems; i++) {
			format = format + inventory[i].toString() + "\n";
		}
		return format;
	}

	public int alreadyExists(Item i) {
		for (int count = 0; count < numItems; count++) {
			// isEqual method is boolean. if isEqual method return true,
			// inventory[count].isEqual(i) is true.
			// thus, it is working and return count(inventory number which
			// itemCode is same)
			if (inventory[count].isEqual(i)) {
				return count;
			}
		}
		// if there's no any item in inventory, return -1.
		if (numItems == 0)
			return -1;

		// default has to be -1
		return -1;
	}

	public boolean updateQuantity(Scanner input, boolean checkType) {
		boolean loop = true;
		int itemCodeForCheck = 0;
		int updateQuantity = 0;
		Item checkItem = null;

		while (loop) {
			System.out.print("Enter valid item code: ");

			if (!input.hasNextInt()) {
				input.next();
				System.err.print("Invalid Code...please enter float greater than 0\n");
			} else {
				// user input for itemCode
				itemCodeForCheck = input.nextInt();
				checkItem = new Item(itemCodeForCheck);

				if (alreadyExists(checkItem) != -1) {

					System.out.print("Enter valid quantity : ");
					updateQuantity = input.nextInt();

					// buying interface
					if (checkType == true) {
						// alreadyExists(object) method is int. return value
						// will be (int) matched itemCode.
						inventory[alreadyExists(checkItem)].updateItem(updateQuantity);
					}
					// selling interface
					else if (checkType == false) {
						inventory[alreadyExists(checkItem)].updateItem(-updateQuantity);
					}
				}
				loop = false;
			}
		}
		return loop;
	}
}
