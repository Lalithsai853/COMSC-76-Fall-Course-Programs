/*
 * Lalithsai Posam
 * Sets and Maps Assignment
 * This program counts the number of time a word occurs in a file.
 * 
 */

import java.util.*;
import java.io.*;
public class WordCounter {
	
	public static void main (String[] args) throws Exception {
		// Checks if user enters file correctly
		if (args.length < 1) {
			System.out.println("Error, usage: java ClassName inputfile");
			System.exit(1);
		}
		// Creates Scanner object and TreeMap
		File file = new File(args[0]);
		Scanner input = new Scanner(file);
		Map<String, Integer> map = new TreeMap<>();
		
		while (input.hasNext()) {
			String line = input.nextLine();
			if (line == null) {
				break;
			}
			// Splits lines
			String[] words = line.split("[\\s+\\p{P}]");
			for (int i = 0; i < words.length; i++) {
				// Checks if first character is a letter using regular 
				// expressions
				if (words[i].matches("^[a-zA-Z]*$")) {
					String key = words[i].toLowerCase(); 
					
					// Adds key if not there. If key is there, adds 1 
					// to value
					if (key.length() > 0) {
						if (!map.containsKey(key)) {
							map.put(key, 1);
						}
						else {
							int value = map.get(key);
							value++;
							map.put(key, value);
						}
					}
				}
			}
		}
		// Prints map in value-key format
		map.forEach((k, v) -> System.out.println(v + "\t" + k));		
	}
}

