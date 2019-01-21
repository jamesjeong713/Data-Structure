
/*********************************************************************************************
 * 
 * Student Name: Seongyeop (James) Jeong
 * Student Number: 040885882  
 * Course:  18W CST8130 - Data Structures
 * 
 * This class contains the dynamically allocated array and it's processing
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
public class Numbers {
	
	Scanner input = new Scanner(System.in);
	private Float [] numbers;
	private int size;

	public Numbers() {
		
		size = 10;
		numbers = new Float[size];
		
		for(int i = 0; i < size; i++)
		{
			numbers[i] = new Float(0.0f);
		}
	}
	
	public Numbers (int size) {
		
		this.size = size;
		this.numbers = new Float[size];
		
		for(int i = 0; i < size; i++)
		{
			numbers[i] = new Float(0.0f);
		}
	}
	
	public void fileReader() {
		String fileName = new String();
		String[] lineArray = null;
		
		System.out.print("\nEnter name of file to process: ");

    	fileName = input.next();
    	File file = new File(fileName);

    	try {
    		// hasNext is that iterator will check if there's data or not. there's exceptions. 
    		// therefore, i removed if(file.exists()
//    		if (file.exists()) {
    			Scanner inFile = new Scanner(file);

    			// to check if there's data or not.
    			while (inFile.hasNextLine()) {
//    				line = inFile.nextLine();
//    				lineArray = line.split(" ");
    				// or,
    				lineArray = inFile.nextLine().split(" ");
    				
    				numbers = new Float[lineArray.length];
    				size = lineArray.length;
    				for (int i = 0; i < lineArray.length; i++)
    				{
    					numbers[i] = Float.parseFloat(lineArray[i]);
    				}
//    			}
    		}
    	} catch (FileNotFoundException fnfe) {
    		System.err.println("\nInvalid file name... " + fileName + " doesn't exist.\n");
    	} catch (NumberFormatException nfe) {
			System.err.println("\nInvalid data in file: did not process");
    	}
	}
	
	public void initValuesInArray() {

    	boolean loopCheck = true;
    	
    	while (loopCheck)
    	{
    		try {
            	System.out.print("\nEnter the float numbers as values in the array: \n");
            	for (int i = 0; i < numbers.length; i++)
        		{
        			System.out.print("Enter value: ");
        			String userInput = input.next();
        			float convertFloat = Float.parseFloat(userInput);

        			numbers[i] = convertFloat;
        		}
            	loopCheck = false;
    		} catch (NumberFormatException nfe) {
    			System.err.println("\nInvalid input: input only numbers");
    		} 
    	}
	}
	
	public String toString() {
		String stringFormat = "Values are: \n";
		
		for(int i = 0; i < size ; i++)
		{
			stringFormat += numbers[i] + "\n";
		}
		return stringFormat;
	}
	
	public float calcAverage() {
		float result = 0.0f;
		
		for (int i = 0 ; i < size ; i ++)
		{
			result += numbers[i];
		}
		return result / size;
	}
	

}
