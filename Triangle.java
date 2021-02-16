/*
 * Lalithsai Posam
 * Triangle.java
 * This program prompts the user to enter the length of three sides and
 * checks if the three sides can form a triangle. If they form a
 * triangle, then the perimeter is calculated and displayed. Otherwise, 
 * a message that the triangle is invalid is displayed.
 */

import java.util.Scanner;

public class Triangle {
	
	public static void main (String[] args) {
		// Input object created.
		Scanner input = new Scanner(System.in);
		
		// Prompts user to enter lengths of three sides
		System.out.print("Enter lengths of sides of the triangle: ");
		
		// Taking in user's input and converts datatype to double.
		double side1 = input.nextDouble();
		double side2 = input.nextDouble();
		double side3 = input.nextDouble();
		
		
		// Checking if the sum of any two pair of sides is greater than
		// the third side.
		if (side1 + side2 > side3 && side2 + side3 > side1 && 
			side1 + side3 > side2) {
				
			double perimeter = side1 + side2 + side3;
			
			// Displays perimeter to user
			System.out.printf("The perimeter of the triangle is %.2f.\n",  
							  perimeter);
		}
		
		else {
			// Displays message if the sides don't form a valid triangle
			System.out.println("Those sides do not specify a valid triangle.");
		}
	}
}

