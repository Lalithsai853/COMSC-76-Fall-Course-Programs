/*
 * Lalithsa Posam
 * Recursion Part 1
 * This program reverses a string using recursion.
 */

import java.io.*;
import java.util.Scanner;
public class ReverseRecursion {
	public static void main (String[] args) {
		Scanner input = new Scanner(System.in);
		
		// Gets string from user
		System.out.print("Enter a string: ");
		String str = input.nextLine();
		
		// Reversed string is displayed
		System.out.println("Reversed String: ");
		reverseDisplay(str);

	}
	// Helper method - calculate high value
	public static void reverseDisplay(String value) {
		int high = value.length() - 1;
		reverseDisplay(value, high);
	}
	
	public static void reverseDisplay(String value, int high) {
		// Base case - if value becomes less than 1, then character is 
		// printed
		if (value.length() <= 1) { 
           System.out.println(value); 
        } else { 
			// Displays last character and then calls function again
            System.out.print(value.charAt(high)); 
            reverseDisplay(value.substring(0,high)); 
 
        } 
	}
}

