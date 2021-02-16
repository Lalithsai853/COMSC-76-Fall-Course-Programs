/*
 * Lalithsai Posam
 * Character Counter Program - Test #1 Program
 */

import java.io.*;
import java.util.Scanner;
public class CharacterCounter {
	
	public static void main (String[] args) {
		Scanner input = new Scanner(System.in);
		
		// Prompts user for string
		System.out.print("Enter a string: ");
		String user_string = input.nextLine();
		
		// Prompts user for character
		System.out.print("Enter a character: ");
		String temp_string = input.nextLine();
		char user_ch = temp_string.charAt(0);
		
		// Variable for number of occurences
		int occurences = charCount(user_string, user_ch);
		
		// Displays number of occurences to user
		System.out.println("The number of times " + user_ch + 
						   " occured is/are " + occurences + " time(s).");
		
	}
	
	// Helper method
	public static int charCount(String str, char ch) {
		int char_count = charCount(str, ch, str.length() - 1);
		return char_count;	
	}
	
	public static int charCount(String str, char ch, int high) {
		int count = 0;
		// Base cases when entire string is searched
		if (high == 0) {
			if (ch == str.charAt(high)) {
				return 1; // base case
			} else {
				return 0; // base case
			}
		// Increments count if match is found
		} else {
			if (ch == str.charAt(high)) {
				count++;
			}
			// Recursive call
			count += charCount(str, ch, high - 1);
		}
		// count is returned
		return count;
	}
}

