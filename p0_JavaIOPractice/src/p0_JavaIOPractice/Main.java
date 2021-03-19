/*
 * Aiden Tepper
 * ajtepper@wisc.edu
 * Lecture 001
 * This project has to do with use of the Scanner object.
 */

package p0_JavaIOPractice;

import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;

public class Main {
	
	// declare + initialize scanner object and title string
	private static final Scanner kb = new Scanner(System.in);
	private static final String title = "Aiden Tepper, ajtepper@wisc.edu, Lecture 001";
	
	public static void main(String[] args) {
		
		System.out.println(title);

		try {
			
			// typed phrase is read and repeated backwards
			System.out.println("\nType any phrase to see it repeated backwards");
			String phrase1 = kb.nextLine();
			System.out.println("\nThe reversed phrase is: " + reverseString(phrase1));

			// after user types 'YES!', the first line of input.txt is read,
			// repeated forwards, then repeated backwards
			System.out.println("\nI will now reverse the phrase found on the file 'input.txt'");
			while(true) {
				System.out.println("Type 'YES!' to continue (without the quotations)");
				if(kb.nextLine().equals("YES!")) {
					break;
				}
			}
	        File file = new File("C:\\Users\\Aiden Tepper\\OneDrive - UW-Madison\\CS 400\\p0_JavaIOPractice\\"
	        		+ "src\\p0_JavaIOPractice\\input.txt");
	        Scanner fileScanner = new Scanner(file);
	        String phrase2 = fileScanner.nextLine();
	        System.out.println("\nThe original phrase is: " + phrase2);
			System.out.println("The reversed phrase is: " + reverseString(phrase2));
			
			// typed phrase is reversed then written to output.txt
			System.out.println("\nType any phrase, I will append the reversed phrase to the end of the output file");
			String phrase3 = kb.nextLine();
	        File outputFile = new File("C:\\Users\\Aiden Tepper\\OneDrive - UW-Madison\\CS 400\\p0_JavaIOPractice\\"
	        		+ "src\\p0_JavaIOPractice\\output.txt");
			PrintWriter writer = new PrintWriter(outputFile);
			writer.write(reverseString(phrase3));
			writer.flush();
			
			System.out.println("\nDone!");
			
		} catch(Exception e) {
			
			System.out.println(e);
			
		}
		
	}
	
	public static String reverseString(String phrase) {
		
		char[] charArray = phrase.toCharArray();
		String reversedPhrase = "";
		
		for(int i = charArray.length - 1; i >= 0; i--) {
			reversedPhrase += charArray[i];
		}
		
		return reversedPhrase;
		
	}

}
