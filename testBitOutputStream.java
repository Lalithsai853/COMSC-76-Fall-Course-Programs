/*
 * Lalithsai Posam
 * File I/O Assignment #2
 * This program outputs a sequence of bits to a .dat file in which the
 * characters are displayed.
 */
import java.io.*;

public class testBitOutputStream {
    public static void main(String[] args) throws Exception {
		// creates output stream to file
		BitOutputStream output = new BitOutputStream(new File("testOutput.dat"));
		output.writeBit("010000100100001001101");
		output.close();
		System.out.println("Done. Characters are in testOutput.dat file.");
    }

public static class BitOutputStream {
        private FileOutputStream output;
        private DataOutputStream outdos;
        private char data;
        private int numBitsValid; 
                     

    // Constucts output stream
    public BitOutputStream(File file) throws IOException {
		output = new FileOutputStream(file);
		numBitsValid = 0;
		data = 0;
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
}

