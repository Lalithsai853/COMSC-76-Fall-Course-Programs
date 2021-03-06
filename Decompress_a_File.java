// Lalithsai Posam
// EVC Final Programming Project
// This program decompresses and then writes the decompressed content into another output file. 

import java.io.File;
import java.io.PrintWriter;
import java.io.ObjectInputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.EOFException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.DataOutputStream;

public class Decompress_a_File {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        
      // Gets both files and stores them into variables
        File source = new File(args[0]);
        File target = new File(args[1]);

        // Creates array and stringbuilder data structure for data from source file
        String[] huffman_codes = new String[0];
        StringBuilder receiver = new StringBuilder();
        int length = -1;

        // Checks if source file exists
        if (!source.exists()) {
          System.out.println(source.getName() + " does not exist");
          System.exit(1);
        }

        // Opens the stream and receives input 
        try(
          ObjectInputStream input = new ObjectInputStream(new BufferedInputStream( new FileInputStream(source)));
        ) {
          // Gets the codes from the file
          huffman_codes = (String[])input.readObject();
          
          // Gets the length of the file
          length = input.readInt();

          // Gets bytes and converts them to bits and stores them into a stringbuilder data structure
          while (true) {
              receiver.append(getBitsfromInt(input.readByte()));
          }
      }  catch (EOFException ex) {

      } 
      
      receiver.delete(length, receiver.length());

      StringBuilder text = new StringBuilder();
      StringBuilder text_codes = new StringBuilder();

      // Appends all characters to a temporary stringbuilder data structure
      for (int i = 0; i < receiver.length(); i++) {
          text_codes.append(receiver.charAt(i) + "");
          for (int j = 0; j < huffman_codes.length; j++) {

            // Stores translated character into text variable which is the stringbuilder data structure
              if (text_codes.toString().equals(huffman_codes[j])) {
                  text.append((char)j);

                  // Clears the stringbuilder
                  text_codes = new StringBuilder();
              }

          }
      }

      // Opens the stream and writes the output to the target file and then closes the stream
      try (
        PrintWriter newOutput = new PrintWriter(target);
      ) {
        newOutput.write(text.toString());
        newOutput.close();
      }
    }

    // Methods that is used to get the bits from the data received
    public static String getBitsfromInt(int number) {
        number = number % 256;
        String finalInt = "";
        int temp = 0;
        for (int i = 0; i < 8; i++) {
            temp = number & (1 << i);
            if (temp != 0) {
                finalInt = "1" + finalInt;
            }
            else {
                finalInt = "0" + finalInt;
            }
        }
        return finalInt;
    }
}



class BitOutputStream implements AutoCloseable{
    private ObjectOutputStream output;
    private DataOutputStream outdos;
    private char data;
    private int numBitsValid;
                 

    // Constucts output stream
    public BitOutputStream(File file) throws IOException {
        output = new ObjectOutputStream(new FileOutputStream(file));
        numBitsValid = 0;
        data = 0;
    }

    public void writeObject(Object object) throws IOException {
      output.writeObject(object);
    }

    // Calls writeBit method with char signature for more than one character
    public void writeBit(String bitString) throws IOException {
        for (int i = 0; i < bitString.length(); i++)
            writeBit(bitString.charAt(i));
    }

    // Writes bits to buffer 
    public void writeBit(char bit) throws IOException {
        if (numBitsValid < 8) {
            char bitVal = 0x0;
            if ((int)bit == '1') {
                bitVal = 0x01;
            }
            // Stores in 8-bit quantity
            data = (char)((data << 1) | bitVal);
            numBitsValid++;
        }
        // Writing occurs here
        if (numBitsValid == 8) {
            String str = Character.toString(data);
            output.write(data);
            data = 0;
            numBitsValid = 0;
        }
    }

    // Write the last byte and close the stream. 
    public void close( ) throws IOException {
        // Right-shifts with 8 - number of bits
        if (numBitsValid != 0) {
            data = (char)(data << (8 - numBitsValid));
            output.write(data);
            String str = Character.toString(data);
            data = 0;
            numBitsValid = 0;
        }
        // This makes use of the close() method for a FileOutputStream object
        output.close(); 
    }
}


class Heap<E extends Comparable<E>> {
    private java.util.ArrayList<E> list = new java.util.ArrayList<>();
  
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
        if (list.get(currentIndex).compareTo(
            list.get(parentIndex)) > 0) {
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
        if (list.get(currentIndex).compareTo(
            list.get(maxIndex)) < 0) {
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
  
class HuffmanCode {
    /** Get Huffman codes for the characters 
     * This method is called once after a Huffman tree is built
     */
    public static String[] getCode(Tree.Node root) {
      if (root == null) return null;    
      String[] codes = new String[2 * 128];
      assignCode(root, codes);
      return codes;
    }
    
    /* Recursively get codes to the leaf node */
    private static void assignCode(Tree.Node root, String[] codes) {
      if (root.left != null) {
        root.left.code = root.code + "0";
        assignCode(root.left, codes);
        
        root.right.code = root.code + "1";
        assignCode(root.right, codes);
      }
      else {
        codes[(int)root.element] = root.code;
      }
    }
    
    /** Get a Huffman tree from the codes */  
    public static Tree getHuffmanTree(int[] counts) {
      // Create a heap to hold trees
      Heap<Tree> heap = new Heap<>(); // Defined in Listing 24.10
      for (int i = 0; i < counts.length; i++) {
        if (counts[i] > 0)
          heap.add(new Tree(counts[i], (char)i)); // A leaf node tree
      }
      
      while (heap.getSize() > 1) { 
        Tree t1 = heap.remove(); // Remove the smallest weight tree
        Tree t2 = heap.remove(); // Remove the next smallest weight 
        heap.add(new Tree(t1, t2)); // Combine two trees
      }
  
      return heap.remove(); // The final tree
    }
    
    /** Get the frequency of the characters */
    public static int[] getCharacterFrequency(String text) {
      int[] counts = new int[256]; // 256 ASCII characters
      
      for (int i = 0; i < text.length(); i++)
        counts[(int)text.charAt(i)]++; // Count the character in text
      
      return counts;
    }
    
    /** Define a Huffman coding tree */
    public static class Tree implements Comparable<Tree> {
      Node root; // The root of the tree
  
      /** Create a tree with two subtrees */
      public Tree(Tree t1, Tree t2) {
        root = new Node();
        root.left = t1.root;
        root.right = t2.root;
        root.weight = t1.root.weight + t2.root.weight;
      }
      
      /** Create a tree containing a leaf node */
      public Tree(int weight, char element) {
        root = new Node(weight, element);
      }
      
      @Override /** Compare trees based on their weights */
      public int compareTo(Tree t) {
        if (root.weight < t.root.weight) // Purposely reverse the order
          return 1;
        else if (root.weight == t.root.weight)
          return 0;
        else
          return -1;
      }
  
      public class Node {
        char element; // Stores the character for a leaf node
        int weight; // weight of the subtree rooted at this node
        Node left; // Reference to the left subtree
        Node right; // Reference to the right subtree
        String code = ""; // The code of this node from the root
  
        /** Create an empty node */
        public Node() {
        }
        
        /** Create a node with the specified weight and character */
        public Node(int weight, char element) {
          this.weight = weight;
          this.element = element;
        }
      }
    }  
  }
