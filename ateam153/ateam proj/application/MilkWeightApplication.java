/////////////////////////////////////////////////////////////////////////////////
//
//Title: Milk Weights
//Course: 400, Spring 20
//
//Author: Juan Rodriguez, Aiden Tepper
//Email: jcrodriguez3@wisc.edu , ajtepper@wisc.edu
//Lecturer's Name: Prof. Deppeler
//
////////////////////////////////////////////////////////////////////////////////

package application;

import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;

public class MilkWeightApplication {
		
    private MSHashTable<String, String> hashTable = new MSHashTable<String, String>();
    
    // method that parses through a given csv file and populates the hash table accordingly
	public void readFromFile(File file) throws Exception {	
			
			ArrayList<ArrayList<String>> records = new ArrayList<>();
			Scanner fileScanner = new Scanner(file);
			
			// iterates through csv file and adds an array list representation
			// of each line to a parent array list
			while(fileScanner.hasNextLine()) {
				records.add(getRecordFromLine(fileScanner.nextLine()));
			}
			
			records.remove(0);
			
			// iterates through each node of the parent array list and adds entry to hash table
			for(ArrayList<String> line : records) {
				String date = line.get(0);
				String farmID = line.get(1);
				int weight = Integer.parseInt(line.get(2));
				hashTable.insert(date, farmID, weight);
			}
			
	}
	
	// helper method that turns a line of the csv file into a small array list
	private ArrayList<String> getRecordFromLine(String line) {
		
		ArrayList<String> values = new ArrayList<>();
		Scanner rowScanner = new Scanner(line);
		rowScanner.useDelimiter(",");
		
		while(rowScanner.hasNext()) {
			values.add(rowScanner.next());
		}
		
		return values;
		
	}
	
	// simple method that re initializes the hash table to clear it
	public void clearData() {
		hashTable = new MSHashTable();
	}
	
}