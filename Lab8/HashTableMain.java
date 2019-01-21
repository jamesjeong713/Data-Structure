import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class HashTableMain {

	public static void main(String[] args) {

		ArrayList<String> dataItem = new ArrayList<String>(100);

		for (int i = 0; i < 100; i++) {
			dataItem.add(i, "");
		}
		Scanner in = new Scanner(System.in);

		boolean loop = true;
		while (loop) {
			try {
				System.out.println("\nEnter...\n" 
						+ "a to add a string to the array, \n" 
						+ "s to search a string, \n"
						+ "q to quit");

				String choice = in.nextLine().toLowerCase();

				if (choice.equals("a")) {
					System.out.println("Enter string to add: ");
					String userAdd = in.nextLine();
					int index = hash(userAdd);

					if (dataItem.get(index).isEmpty()) {
						dataItem.set(index, userAdd);
						System.out.println("[" + userAdd + "]" + " placed into array at index(" + index + ")");
					} else {
						for (int i = index + 1; i < 100; i++) {
							if (dataItem.get(index) == null) {
								dataItem.set(index, userAdd);
								System.out.printf("[" + userAdd + "]" + " placed into array at index(" + index + ")");
								break;
							}
						}
					}
				} else if (choice.equals("s")) {
					System.out.println("Enter a string to search: ");
					String userSearch = in.nextLine();
					int index = hash(userSearch);

					if (dataItem.get(index).isEmpty()) {
						System.err.println("There is no matched data");
					} else {
						for (int i = index; i < 100; i++) {

							if (dataItem.get(index).equals(userSearch)) {
								System.out.println("[" + userSearch + "]" + " placed into array at index(" + i + ")");
								break;
							}
						}
					}
				} else if (choice.equals("q")) {
					loop = false;
					System.out.println("Goodbye");

				} else {
					System.err.println("Invalid input");
				}

			} catch (InputMismatchException e) {
				System.err.println("Invalid input: MisMatchException");
				System.out.println("Please enter valid input: ");
				in.nextLine();
			}
		}
	}

	public static int hash(String userAdd) {
		int a = (int) (userAdd.charAt(0));
		int b = 0;

		if (userAdd.length() > 1) {
			b = (int) (userAdd.charAt(1));
		}
		return (a + b) % 100;
	}

}