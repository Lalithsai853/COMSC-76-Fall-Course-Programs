// Lalithsai Posam
// SortingAlgorithms.java

import java.util.ArrayList;

public class SortingAlgorithms {
    public static void main(String[] args) {
        System.out.print("|Array size    Selection      Merge         Quick         Heap          Radix     ");

        // Creates array with proper sizes and random data
        for (int arraySize = 50000; arraySize <= 300000; arraySize += 50000) {
            int  width = 12;
    
            int[] list = new int[arraySize];

            for (int k = 0; k < list.length; k++) {
                list[k] = (int)(Math.random() * 1000000);
            }
            System.out.print("\n|");

            for (int r = 0; r < 6; r++) {
                for (int j = 0; j < width; j++) {
                    System.out.print("-");
                }
                System.out.print("|");
            }
            System.out.printf("\n|%" + width + "d|", arraySize);


            // Following blocks performs each sort and displays execution time
            int[] selectionSortList = new int[arraySize];
            System.arraycopy(list, 0, selectionSortList, 0, list.length);
            long startTime = System.currentTimeMillis();  
            selectionSort(selectionSortList);
            long endTime = System.currentTimeMillis();
            long executionTime = endTime - startTime;
            System.out.printf("%" + width + "d|", executionTime);

            int[] mergeSortList = new int[arraySize];
            System.arraycopy(list, 0, mergeSortList, 0, list.length);
            startTime = System.currentTimeMillis();  
            mergeSort(mergeSortList);
            endTime = System.currentTimeMillis();
            executionTime = endTime - startTime;
            System.out.printf("%" + width + "d|", executionTime);

            int[] quickSortList = new int[arraySize];
            System.arraycopy(list, 0, quickSortList, 0, list.length);
            startTime = System.currentTimeMillis();  
            quickSort(quickSortList);
            endTime = System.currentTimeMillis();
            executionTime = endTime - startTime;
            System.out.printf("%" + width + "d|", executionTime);

            int[] heapSortList = new int[arraySize];
            System.arraycopy(list, 0, heapSortList, 0, list.length);
            startTime = System.currentTimeMillis();  
            heapSort(heapSortList);
            endTime = System.currentTimeMillis();
            executionTime = endTime - startTime;
            System.out.printf("%" + width + "d|", executionTime);

            
            int[] radixSortList = new int[arraySize];
            System.arraycopy(list, 0, mergeSortList, 0, list.length);
            startTime = System.currentTimeMillis();  
            selectionSort(radixSortList);
            endTime = System.currentTimeMillis();
            executionTime = endTime - startTime;
            System.out.printf("%" + width + "d|", executionTime);
            
            
        }
    }

    // Replaces current element in array with smallest element
    public static void selectionSort(int[] list) {
        for (int i = 0; i < list.length - 1; i++) {
            int currentMin = list[i];
            int currentMinIndex = i;
      
            for (int j = i + 1; j < list.length; j++) {
                if (currentMin > list[j]) {
                    currentMin = list[j];
                    currentMinIndex = j;
                }
            }
            
            if (currentMinIndex != i) {
                list[currentMinIndex] = list[i];
                list[i] = currentMin;
            }
        }
    }

    public static void mergeSort(int[] list) {
        if (list.length > 1) {
          // Merge sort the first half
          int[] firstHalf = new int[list.length / 2];
          System.arraycopy(list, 0, firstHalf, 0, list.length / 2);
          mergeSort(firstHalf);
    
          // Merge sort the second half
          int secondHalfLength = list.length - list.length / 2;
          int[] secondHalf = new int[secondHalfLength];
          System.arraycopy(list, list.length / 2,
            secondHalf, 0, secondHalfLength);
          mergeSort(secondHalf);
    
          // Merge firstHalf with secondHalf into list
          merge(firstHalf, secondHalf, list);
        }
      }
    
      /** Merge two sorted lists */
    public static void merge(int[] list1, int[] list2, int[] temp) {
        int current1 = 0; // Current index in list1
        int current2 = 0; // Current index in list2
        int current3 = 0; // Current index in temp
    
        while (current1 < list1.length && current2 < list2.length) {
        if (list1[current1] < list2[current2])
            temp[current3++] = list1[current1++];
        else
            temp[current3++] = list2[current2++];
        }
    
        while (current1 < list1.length)
            temp[current3++] = list1[current1++];
    
        while (current2 < list2.length)
            temp[current3++] = list2[current2++];
    }

    // Helper method
    public static void quickSort(int[] list) {
        quickSort(list, 0, list.length - 1);
      }
    
    private static void quickSort(int[] list, int first, int last) {
        if (last > first) {
          int pivotIndex = partition(list, first, last);
          quickSort(list, first, pivotIndex - 1);
          quickSort(list, pivotIndex + 1, last);
        }
    }
    
      /** Partition the array list[first..last] */
    private static int partition(int[] list, int first, int last) {
        int pivot = list[first]; // Choose the first element as the pivot
        int low = first + 1; // Index for forward search
        int high = last; // Index for backward search

        while (high > low) {
            // Search forward from left
            while (low <= high && list[low] <= pivot)
                low++;
                                                                                                
            // Search backward from right
            while (low <= high && list[high] > pivot)
                high--;

            // Swap two elements in the list
            if (high > low) {
                int temp = list[high];
                list[high] = list[low];
                list[low] = temp;
            }
        }

        while (high > first && list[high] >= pivot)
          high--;
    
        // Swap pivot with list[high]
        if (pivot > list[high]) {
          list[first] = list[high];
          list[high] = pivot;
          return high;
        }

        else {
          return first;
        }
    }

    public static void heapSort(int[] list) {
        // Create a Heap of integers
        Heap<Integer> heap = new Heap<Integer>();
    
        // Add elements to the heap
        for (int i = 0; i < list.length; i++)
          heap.add(list[i]);
    
        // Remove elements from the heap
        for (int i = list.length - 1; i >= 0; i--)
          list[i] = heap.remove();
    }
      

    static class Heap<E extends Comparable<E>> {
        private java.util.ArrayList<E> list = new java.util.ArrayList<E>();
      
        /** Create a default heap */
        public Heap() {
        }
      
        /** Create a heap from an array of objects */
        public Heap(E[] objects) {
          for (int i = 0; i < objects.length; i++)
            add(objects[i]);
        }
      
        /** Add a new object into the heap */
        public void add(E newObject) {
          list.add(newObject); // Append to the heap
          int currentIndex = list.size() - 1; // The index of the last node
      
          while (currentIndex > 0) {
            int parentIndex = (currentIndex - 1) / 2;
            // Swap if the current object is greater than its parent
            if (list.get(currentIndex).compareTo(list.get(parentIndex)) > 0) {
              E temp = list.get(currentIndex);
              list.set(currentIndex, list.get(parentIndex));
              list.set(parentIndex, temp);
            }
            else
              break; // the tree is a heap now
      
            currentIndex = parentIndex;
          }
        }
      
        /** Remove the root from the heap */
        public E remove() {
          if (list.size() == 0) return null;
      
          E removedObject = list.get(0);
          list.set(0, list.get(list.size() - 1));
          list.remove(list.size() - 1);
      
          int currentIndex = 0;
          while (currentIndex < list.size()) {
            int leftChildIndex = 2 * currentIndex + 1;
            int rightChildIndex = 2 * currentIndex + 2;
      
            // Find the maximum between two children
            if (leftChildIndex >= list.size()) break; // The tree is a heap
            int maxIndex = leftChildIndex;
            if (rightChildIndex < list.size()) {
              if (list.get(maxIndex).compareTo(
                  list.get(rightChildIndex)) < 0) {
                maxIndex = rightChildIndex;
              }
            }
      
            // Swap if the current node is less than the maximum
            if (list.get(currentIndex).compareTo(list.get(maxIndex)) < 0) {
              E temp = list.get(maxIndex);
              list.set(maxIndex, list.get(currentIndex));
              list.set(currentIndex, temp);
              currentIndex = maxIndex;
            }
            else
              break; // The tree is a heap
          }
      
          return removedObject;
        }
      
        /** Get the number of nodes in the tree */
        public int getSize() {
          return list.size();
        }
    }
      

    public static void radixSort(int[] list, int max) {
        
      // Creates buckets per significant figure
        for (int digit = 1; digit < max; digit *= 10) {
            ArrayList<Integer>[] buckets = new ArrayList[10];

            // Creates arraylist in each bucket
            for (int i = 0; i < buckets.length; i++) {
                buckets[i] = new java.util.ArrayList<>();
            }
            // Inserts element to each bucket according to remainder when divided by 10
            for (int i = 0; i < list.length; i++) {
                buckets[(list[i] / digit) % 10].add(list[i]);
            }
            
            int n = 0;
            // Sends elements in sorted order to original list
            for (int i = 0; i < buckets.length; i++) {
                if (buckets[i] != null) {
                    for (int j = 0; j < buckets[i].size(); j++)
                        list[n++] = buckets[i].get(j);
                }
            }
        }

    }
}

