/*
 * Lalithsai Posam
 * TestRecursion.java
 * This program.... 
 */

import java.io.*;
import java.util.Scanner;							

public class TestRecursion {
	
	public static void main (String[] args) {
		Scanner input = new Scanner(System.in);
		
		System.out.println("Enter a string: ");
		String str = input.nextLine();
		
		System.out.println("Reversed String: " + reverseDisplay(str));

	}
	
	public static String reverseDisplay(String value) {
		int high = value.length() - 1;
		reverseDisplay(value, high);
	}
	
	public static void reverseDisplay(String value, int high) {
		if (value.length() <= 1) { 
           System.out.println(value); 
        } else { 
            System.out.print(value.charAt(high)); 
            reverseDisplay(value.substring(0,high - 1)); 
        } 
	}
	
	public static void displayPermutation(String s) {
		displayPermutation(" ", s);
	}

    public static void displayPermutation(String s1, String s2) {
		if (s2.isEmpty()) {
			System.out.println(s1 + s2);
			return;
		} else {
			for (int i = 0; i < s2.length(); i++) {
				char indexChar = s2.charAt(i);
				String restOfString = s2.substring(0,i) + 
				s2.substring(i + 1, s2.length());
				
				displayPermutation(s1 + indexChar, restOfString); 
			}
		}
	} 
}

