
/*********************************************************************************************
 * 
 * Student Name: Seongyeop Jeong (James)
 * Student Number: 040885882 
 * Course:  18W CST8130 - Data Structures
 * Date: 2018/03/30
 * Purpose: this class is to manage students' grades with hash (i.e blocks) by using linked list
 */

import java.util.*;

public class CourseGrades {

	private LinkedList<Block> courseGrades;
	private String courseName;
	private Iterator<Block> iBlock;

	public CourseGrades(String courseName) {

		this.courseName = courseName;
		courseGrades = new LinkedList<Block>();
		courseGrades.addFirst(new Block());
	}

	public boolean verifyChain() {

		for (int i = 0; i < courseGrades.size() - 1; i++)
			if (!courseGrades.get(i + 1).isEqual(courseGrades.get(i)))
				return false;
		return true;
	} // end of the verifyChain

	public void addBlock(Scanner keyboard, int select) {

		Block newOne = new Block();
		
		System.out.println("Add good block or bad?  (g for good, anything else for bad): ");
		String input = keyboard.next();
		
		if (input.equals("g") || input.equals("G")) {
			newOne.addInfoToBlock(keyboard, courseGrades.getLast().getCurrentHash());
			courseGrades.add(newOne);
		} else
			addBadBlock(keyboard);
	} // end of the addBlock

	public void addBadBlock(Scanner keyboard) {

		Random random = new Random();
		Block newOne = new Block();
		
		if (newOne.addInfoToBlock(keyboard, random.nextFloat())) 
			courseGrades.add(newOne);
	} // end of the addBadBlock

	public void removeBadBlocks() {

		iBlock = courseGrades.iterator();
		
		Block previousBlock = iBlock.next();
		Block currentBlock;
		while (iBlock.hasNext()) {
			currentBlock = iBlock.next();
			if (!currentBlock.isEqual(previousBlock)) {
				iBlock.remove();
				if (iBlock.hasNext()) { // if one we're moving is not last in chain
					currentBlock = iBlock.next();
					currentBlock.updatePreviousHash(previousBlock.getCurrentHash());
					previousBlock = currentBlock;
				} 
			} else
				previousBlock = currentBlock;
		}
	} // end of the removeBadBlocks

	public String getCourseName() {
		return courseName;
	} // end of the getCourseName

	public String toString() {

		int index = 0;
		String format = "";
		iBlock = courseGrades.iterator();
		System.out.println("For course: " + courseName + "\n" + "[");
		while (iBlock.hasNext()) {
			format += iBlock.next();
			index++;
			if (index == courseGrades.size()) {
				format += "]\n";
			} else {
				format += "\n";
			}
		}
		return format + "\n";
	} // end of the toString

}
