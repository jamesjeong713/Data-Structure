
/*********************************************************************************************
 * 
 * Student Name: Seongyeop Jeong (James)
 * Student Number: 040885882 
 * Course:  18W CST8130 - Data Structures
 * Date: 2018/03/30
 * Purpose: this program is to make program which you can manage students belonged to courses 
 * each information of students is encoded by hashes
 */

import java.util.*;

public class Assign3Main {

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		College college = new College();
		String menuChoice = "a";

		while (menuChoice.charAt(0) != '6') {
			System.out.println("\nEnter 1 to display the courses: ");
			System.out.println("2 to add a new course: ");
			System.out.println("3 to add a add block: ");
			System.out.println("4 to verify chain: ");
			System.out.println("5 to fix a chain:");
			System.out.println("6 to quit: ");
			menuChoice = keyboard.next();

			switch (menuChoice.charAt(0)) {
			case '1':
				college.displayCollege();
				break;
			case '2':
				college.createCourseList(keyboard);
				break;

			case '3':
				college.addBlock(keyboard);
				break;
			case '4':
				college.verifyCollegeChain();
				break;
			case '5':
				college.fixChain(keyboard);
				break;
			case '6':
				System.out.println("Goodbye");
				break;
			default:
				System.out.println("Invalid choice...");
			}
		}
	}
}
