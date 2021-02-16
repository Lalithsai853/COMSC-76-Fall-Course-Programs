/*
 * Lalithsai Posam
 * List, Stacks, and Queues Assignment
 * This program creates an array with 100 points and sorts them by
 * increasing x-coordinates and increasing y-coordinates.
 */

import java.io.*; 
import java.util.*;
public class TestPoint {
	public static void main (String[] args) {
		// Creates array of 100 points
		Point[] array_points = new Point[100];
		for (int i = 0; i < array_points.length; i++) {
				array_points[i] = new Point();
				array_points[i].x = Math.random() * 100;
				array_points[i].y = Math.random() * 100;
		}
		
		// Sorts and displays array according to x-coordinate using 
		// compareTo method
		Arrays.sort(array_points);
		for (int i = 0; i < array_points.length; i++) {
			System.out.printf("[ %.2f, %.2f ]\n", array_points[i].x,
			array_points[i].y);
		}
		
		// Division between increasing x and increasing y
		System.out.println(" ");
		System.out.println("----------------------");
		System.out.println(" ");
		
		// Sorts and displays array according to x-coordinate using 
		// CompareY compare method
		Arrays.sort(array_points, new CompareY());
		for (int i = 0; i < array_points.length; i++) {
			System.out.printf("[ %.2f, %.2f ]\n", array_points[i].x,
			array_points[i].y);
		}
	}
}

class Point implements Comparable<Point> {
	public double x;
	public double y;
	
	@Override
	// Compares x coordinates. If equal, compares y coordinates
	public int compareTo (Point rand_point) {
		if (x > rand_point.x) 
			return 1;
		
		else if (x < rand_point.x) 
			return -1;
		
		else if (y > rand_point.y) 
			return 1;
		
		else if (y < rand_point.y)
			return -1;
			
		else
			return 0;
	}
	
}


class CompareY implements Comparator<Point> {
	@Override
	// Compares y coordinates. If equal, compares x coordinates
	public int compare(Point point1, Point point2) {
		if (point1.y > point2.y) 
			return 1;
		
		else if (point1.y < point2.y) 
			return -1;
		
		else if (point1.x > point2.x) 
			return 1;
		
		else if (point1.x < point2.x) 
			return -1;
		else 
			return 0;
	}
}
