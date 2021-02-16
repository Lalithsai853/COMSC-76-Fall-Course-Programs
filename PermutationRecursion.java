/*
 * Lalithsa Posam
 * Recursion Part 2
 * This program prints the permutations of a string using recursion.
 */
import java.io.*;
import java.util.Scanner;
public class PermutationRecursion {
	
	public static void main (String[] args) {
		Scanner input = new Scanner(System.in);
		
		// Takes input string
		System.out.print("Enter a string: ");
		String str = input.nextLine();
		
		// Prints permutations
		System.out.println("Permutatuins: "); 
		displayPermutation(str);
	}
	
	// Helper method - s1 is empty string
	public static void displayPermutation(String s) {
		displayPermutation(" ", s);
	}

    public static void displayPermutation(String s1, String s2) {
		// Base case -- if string 2 is empty it prints out permutation
		if (s2.isEmpty()) {
			System.out.println(s1 + s2);
			return;
		} else {
			// Finds all possible permutations
			for (int i = 0; i < s2.length(); i++) {
				char indexChar = s2.charAt(i);
				// Finds possible permutation
				String restOfString = s2.substring(0,i) + 
				s2.substring(i + 1, s2.length());
				
				// Calls the permutation function again -- first parameter
				// stores result from previous calculation
				displayPermutation(s1 + indexChar, restOfString); 
			}
		}
	}
}

