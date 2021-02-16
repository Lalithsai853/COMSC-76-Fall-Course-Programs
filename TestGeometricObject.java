/*
 * Lalithsai Posam
 * Abstract Classes and Interfaces - Assignment #1
 * I am extending the functionability of the GeometricObject class so
 * that it can include the Triangle class as well. This program will 
 * display information about three geometric objects.
 */


//import statement to access Scanner method 
import java.util.Scanner;

public class TestGeometricObject {
	
	public static void main (String[] args) {
		
		// Input object created.
		Scanner input = new Scanner(System.in);
		
		// Prompts user to enter lengths of three sides
		System.out.print("Enter lengths of sides of the triangle: ");
		
		// Taking in user's input and converts datatype to double.
		double side1 = input.nextDouble();
		double side2 = input.nextDouble();
		double side3 = input.nextDouble();
		
		// Checking if sides fulfill a triangle
		if (side1 + side2 > side3 && side2 + side3 > side1 && 
			side1 + side3 > side2) {
			
			// Prompts user for color
			System.out.print("Enter the color of the triangle: ");	
			
			// Discards whitespace from input
			String color1 = input.nextLine();
			
			String color = input.nextLine();
			
			System.out.print("Is the triangle filled? (True of False) ");
			boolean filled = input.nextBoolean(); 			 

			// Creates triangle with user's information
			GeometricObject geoObject1 = new Triangle(side1, side2, side3);
			geoObject1.setColor(color);
			geoObject1.setFilled(filled);
			System.out.println(geoObject1.toString());
		}
		
		else {
			System.out.println("Those sides do not specify a valid triangle.");
		}
		
		// Construct Rectangle object for testing
		GeometricObject geoObject2 = new Rectangle(5,6);
		geoObject2.setColor("Green");
		geoObject2.setFilled(true);
		System.out.println(geoObject2.toString());
		
		// Construct Circle object for testing
		GeometricObject geoObject3 = new Circle(4);
		geoObject3.setColor("Orange");
		geoObject3.setFilled(false);
		System.out.println(geoObject3.toString());
	}
}

abstract class GeometricObject {
	private String color = "white";
	private boolean filled;
	private java.util.Date dateCreated;

	// Construct a default geometric object 
	protected GeometricObject() {
		dateCreated = new java.util.Date();
	}

	// Construct a geometric object with color and filled value 
	protected GeometricObject(String color, boolean filled) {
		dateCreated = new java.util.Date();
		this.color = color;
		this.filled = filled;
	}

	// Return color 
	public String getColor() {
		return color;
	}

	// Set a new color 
	public void setColor(String color) {
		this.color = color;
	}

	// Return boolean value filled 
	public boolean isFilled() {
		return filled;
	}

	// Set a new boolean value filled 
	public void setFilled(boolean filled) {
		this.filled = filled;
	}

	// Get dateCreated 
	public java.util.Date getDateCreated() {
		return dateCreated;
	}

	// Return a string representation of this object 
	public String toString() {
		return " created on " + dateCreated + "\nColor: " + color +
			   " and filled: " + filled + ".";
	}

	// Abstract method getArea 
	public abstract double getArea();

	// Abstract method getPerimeter 														
	public abstract double getPerimeter();


}

class Triangle extends GeometricObject {
	private double side1 = 0.0;
	private double side2 = 0.0;
	private double side3 = 0.0;

	// Constructs default triangle object
	public Triangle() {
	}

	// Creates triangle object with given sides
	public Triangle(double side1, double side2, double side3) {
		this.side1 = side1;
		this.side2 = side2;
		this.side3 = side3;
	}

	// Returns side1
	public double getSide1() {
		return side1;
	}

	// Returns side2
	public double getSide2() {
		return side2;
	}

	// Returns side3
	public double getSide3() {
		return side3;
	}

	// Sets side1
	public void setSide1(double side1) {
		this.side1 = side1;
	}

	// Sets side2
	public void setSide2(double side2) {
		this.side2 = side2;
	}

	// Sets side3
	public void setSide3(double side3) {
		this.side3 = side3;
	}

	// Returns area
	@Override 
	public double getArea() {
		double semiPerimeter = ((side1 + side2 + side3) / 2);

		return Math.sqrt((semiPerimeter) * (semiPerimeter - side1) 
					  * (semiPerimeter - side2) * (semiPerimeter - side3));  
	}

	// Returns perimeter
	@Override 
	public double getPerimeter() {
		return side1 + side2 + side3;
	}

	@Override
	public String toString() {
		return ("The triangle was" + super.toString() +
						"\nPerimeter: " + this.getPerimeter() + 
						"\nArea: " + this.getArea() + "\n");
  }
}


class Circle extends GeometricObject {
	private double radius;
	
	// default circle constructor
	public Circle() {
	}
	
	// circle constructor with radius as argument
	public Circle(double radius) {
		this.radius = radius;
	}

	// Return radius 
	public double getRadius() {
		return radius;
	}

	// Set a new radius 
	public void setRadius(double radius) {
		this.radius = radius;
	}

	@Override // Return area
	public double getArea() {
		return radius * radius * Math.PI;
	}

	// Return diameter 
	public double getDiameter() {
		return 2 * radius;
	}

	@Override // Return perimeter 
	public double getPerimeter() {
		return 2 * radius * Math.PI;
	}
	
	@Override
	public String toString() {
		return ("The circle was" + super.toString() +
						"\nRadius: " + radius +  
						"\nCircumference: " + this.getPerimeter() + 
						"\nArea: " + this.getArea() + "\n");
	}
}


class Rectangle extends GeometricObject {
	private double width;
	private double height;
	
	// default rectangle object
	public Rectangle() {
	}
	// rectangle object with width and height
	public Rectangle(double width, double height) {
		this.width = width;
		this.height = height;
	}

	// Return width 
	public double getWidth() {
		return width;
	}

	// Set a new width 
	public void setWidth(double width) {
		this.width = width;
	}

	// Return height 
	public double getHeight() {
		return height;
	}

	// Set a new height 
	public void setHeight(double height) {
		this.height = height;
	}

	@Override // Return area 
	public double getArea() {
		return width * height;
	}

	@Override // Return perimeter 
	public double getPerimeter() {
		return 2 * (width + height);
	}
	
	@Override
	public String toString() {
		return ("The rectangle was" + super.toString() +
				"\nPermieter: " + this.getPerimeter() + 
						"\nArea: " + this.getArea() + "\n");
	}
}
