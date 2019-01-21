
/*********************************************************************************************
 * 
 * Student Name: Seongyeop Jeong (James)
 * Student Number: 040885882 
 * Course:  18W CST8130 - Data Structures
 * Date: 2018/03/30
 * Purpose: this class is to manage courses 
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class College {
	private ArrayList<CourseGrades> college;
	private String collegeName;
	private Iterator<CourseGrades> iBlockChain;

	public College() {
		this.collegeName = "Algonquin";
		college = new ArrayList<CourseGrades>(100);
	}

	public boolean createCourseList(Scanner keyboard) {
		
		CourseGrades chainList;
		boolean checkCourseName = true;
		
		System.out.println("Enter name of course to add: ");
		String courseName = keyboard.next();
		chainList = new CourseGrades(courseName);

		if (college.isEmpty()) {
			college.add(chainList);
		} else {
			for (CourseGrades courseGrade : college) {
				if (courseGrade.getCourseName().equals(courseName)) {
					System.err.println("Invalid course name: there is a same course name");
					checkCourseName = false;
					break;
				}
			}
			if (checkCourseName) {
				college.add(chainList);
				checkCourseName = true;
			}
		}
		return checkCourseName;
	} // end of the createCourseList

	public boolean addBlock(Scanner keyboard) {
		
		if (college.isEmpty()) {
			System.err.println("Empty list: add a course into list");
			return false;
		} else {
			System.out.println("Enter which course to add : ");

			iBlockChain = college.iterator();
			int courseId = 0;
			while (iBlockChain.hasNext()) {
				// use later nextIndex for iterator
				System.out.println(courseId + " " + iBlockChain.next().getCourseName());
				courseId++;
			}
			int select = getIndexCourse(keyboard);
			college.get(select).addBlock(keyboard, select);

			return true;
		}
	}

	public int getIndexCourse(Scanner keyboard) {

		int index = 0;
		boolean loop = true;

		while (loop) {
			if (!keyboard.hasNextInt()) {
				keyboard.next();
				System.err.println("Invalid input: enter only integer value");
			} else {
				index = keyboard.nextInt();
				if (college.size() - 1 < index)
					System.err.println("Invalid entry: please reenter ");
				else
					loop = false;
			}
		}
		return index;
	}

	public boolean displayCollege() {

		System.out.println("For College: " + collegeName + "\n");
		if (college.isEmpty()) {
			return false;
		} else {
			for (CourseGrades courseGrade : college) {
				System.out.print(courseGrade);
			}
			return true;
		}
	}

	public void verifyCollegeChain() {
		
		if (college.isEmpty()) {
			System.err.println("Verify error: list is empty");
		} else {
			for (CourseGrades courseGrade : college) {
				System.out.print("Chain for " + courseGrade.getCourseName());
				if (courseGrade.verifyChain()) {
					System.out.println(" is verified");
				} else {
					System.out.println(" is not verified");
				}
			}
		}
	}

	public void fixChain(Scanner keyboard) {

		iBlockChain = college.iterator();
		int courseId = 0;

		System.out.println("Enter which course to fix: ");
		while (iBlockChain.hasNext()) {
			System.out.println(courseId + " " + iBlockChain.next().getCourseName());
			courseId++;
		}
		int select = getIndexCourse(keyboard);
		college.get(select).removeBadBlocks();

		System.out.println("Chain is fixed");
	}
}
