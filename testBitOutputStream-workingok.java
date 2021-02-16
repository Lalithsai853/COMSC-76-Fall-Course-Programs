/*
 * Lalithsai Posam
 * 
 */
import java.io.*;
import java.nio.charset.StandardCharsets;

public class testBitOutputStream {
    public static void main(String[] args) throws Exception {
		BitOutputStream output = new BitOutputStream(new File("testOutput.txt"));
		output.writeBit("010000100100001001101");
		output.close();
		System.out.println("Done");
    }

    public static class BitOutputStream {
        private FileOutputStream output;
        private DataOutputStream outdos;
        private char data;
        private int numBitsValid; 
                     

    // Constructor
    public BitOutputStream(File file) throws IOException {
		output = new FileOutputStream(file);
		outdos = new DataOutputStream(new BufferedOutputStream(output));
		//stream = new PrintStream(output, true, StandardCharsets.UTF_8);
		numBitsValid = 0;
		data = 0;
    }

    public void writeBit(String bitString) throws IOException {
		for (int i = 0; i < bitString.length(); i++)
              writeBit(bitString.charAt(i));
    }

    public void writeBit(char bit) throws IOException {
		if (numBitsValid < 8) {
			char bitVal = 0x0;
			if ((int)bit == '1') {
				bitVal = 0x01;
			}
			data = (char)((data << 1) | bitVal);
			numBitsValid++;
			System.out.println((int)data);
		}
		if (numBitsValid == 8) {
			String str = Character.toString(data);
			output.write(data);
			//outdos.write(str.getBytes());
			System.out.println(str);
			System.out.println((int)data);
			data = 0;
			numBitsValid = 0;
		}
    }

     /** Write the last byte and close the stream. If the last byte is not full, right-shfit with zeros         */
     public void close( ) throws IOException {
         // Program statements for this method
		if (numBitsValid != 0) {
			data = (char)(data << (8 - numBitsValid));
			output.write(data);
			String str = Character.toString(data);
			//outdos.write(str.getBytes());
			System.out.println(str);
			System.out.println((int)data);
			data = 0;
			numBitsValid = 0;
		}
		  //outdos.flush();
		  outdos.close();
          output.close();  // This makes use of the close() method for a FileOutputStream object
       }
   }
}

