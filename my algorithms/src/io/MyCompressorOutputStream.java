package io;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;

/**
 * This class is responsible for data compression and assign it in Output stream Object
 * @author MatanA
 *
 */
public class MyCompressorOutputStream extends OutputStream {

	/*private OutputStream out;
	public MyCompressorOutputStream(OutputStream out) 
	{
		this.out = out;
	}
	@Override
	public void write(int b) throws IOException 
	{
		out.write(b);
	}
	@Override
	public void write(byte[]bytes) throws IOException
	{
		out.write(bytes, 0, 9);
		byte last = bytes[9];
		int counter = 1;
		
		for (int i = 10; i < bytes.length; i++) 
		{
			if (bytes[i] == last) 
				counter++;
			else 
			{
				//A case in which there is a sequence of more than 255 identity bytes
				while (counter > 255) 
				{
					out.write(last);
					out.write(255);
					counter -= 255;
				}				
				 
				out.write(last); 
				out.write(counter);
				
				//update the last byte and initialize the counter
				last = bytes[i];
				counter = 1;
			}
		}		
		
		// write the last byte
		out.write(last);
		out.write(counter);
		
	}*/
private OutputStream out;
	
	
	/**
	 * Ctor for the class MyCompressorOutputStream
	 * @param out OutputStream param that we will use
	 */
	public MyCompressorOutputStream(OutputStream out) {
		this.out = out;
	}
	/**
	 * Override for the write method from OutputStream
	 *  @param arg0 
	 *  @throws IOException
	 */
	@Override 
	public void write(int arg0) throws IOException {
		out.write(arg0);

	}

	/**
	 * write method that gets array of bytes
	 * @param byteArr array of bytes
	 * @throws IOException
	 */
	public void write(byte[] byteArr) throws IOException {
		ByteBuffer buf = ByteBuffer.wrap(byteArr,0,byteArr.length);
		//setting the buffer
		int temp;
		int tempZeroOne;
		int cnt;
		for (int i=0; i<9; i++) // get the size of the maze (x,y,z) and the entrance and goal positions and use write(int arg0) method
		{
			byte[] TempArr = new byte[4];
			//building byte array
			TempArr =convertIntToByte(buf.getInt());
			//converting the integer to a array of 4 bytes
			for (int j=0; j<4;j++)
			{
				write(TempArr[j]);
				//writing the new array
			}
			
		}
		
		tempZeroOne= buf.get();
		//getting the byte at the buffer's current position 
		cnt = 1;
		
		while (buf.hasRemaining()) // get the maze and Compress it and than use the write(int arg0) method
		{
			temp = buf.get();
			if (temp==tempZeroOne)
				//comparing between the buffer two positions before and after the check if any elemnts were left
			{
				cnt++;
			}
			else
			{
				write(tempZeroOne);
				write(cnt);
				tempZeroOne=temp;
				cnt=1;
			}
		}
		
		write(tempZeroOne);
		write(cnt);
	}
	
	/**
	 * method that convert an integer to a array of 4 bytes
	 * @param num the number we want to convert
	 * @return array of 4 bytes represent the int
	 */
	public byte[] convertIntToByte(int num)
	{

	    return ByteBuffer.allocate(4).putInt(num).array();
	}

}
