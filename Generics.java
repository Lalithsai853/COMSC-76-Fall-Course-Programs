/*
 * Lalithsai Posam
 * Generic Assignment
 * This program sorts a generic array list.
 */

import java.util.ArrayList;

public class Generics {
	
	public static void main (String[] args) {
		// Declaring and Intializing all array lists
		ArrayList<Integer> integers = new ArrayList<Integer>();
		integers.add(2);
		integers.add(3);
		integers.add(4);
		ArrayList<Double> doubles = new ArrayList<Double>();
		doubles.add(3.4);
		doubles.add(1.2);
		doubles.add(-12.3);
		ArrayList<String> strings = new ArrayList<String>();
		strings.add("Bob");
		strings.add("Alice");
		strings.add("Ted");
		strings.add("Carol");
		
		// Sorting array lists
		sort(integers);
		sort(doubles);
		sort(strings);
		
		// Displaying sorted lists
		System.out.print("Sorted Integer objects: ");    
		printList(integers);    
		System.out.print("Sorted Double objects: ");    
		printList(doubles);    
		System.out.print("Sorted String objects: ");    
		printList(strings);
		
		
	}
	
	public static <E extends Comparable<E>> void sort(ArrayList<E> list) {
		// Setting currentMin and currentMin Index to 0
		E currentMin;
		int currentMinIndex;
		
		// Setting the currentMin to ith element
		for (int i = 0; i < list.size(); i++) {
			currentMin = list.get(i);
			currentMinIndex = i;
			
			// Comparing rest of elements to currentMin
			for (int j = i + 1; j < list.size(); j++) {
				if (currentMin.compareTo(list.get(j)) > 0) {
					currentMin = list.get(j);
					currentMinIndex = j;
				}
			}
			
			// If element less than current element is found, they are
			// swapped.
			if (currentMinIndex != i) {
				list.set(currentMinIndex, list.get(i));
				list.set(i, currentMin);
			}
		}
	}
	
	// Printer method that prints array list
	public static void printList(ArrayList<?> list) {   
		for (int i = 0; i < list.size(); i++) {      
			System.out.print(list.get(i) + " ");
		}    
		System.out.println();  
	}
			
}

