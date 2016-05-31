package io;

import java.io.IOException;
import java.io.OutputStream;
/**
 * This class is responsible for data compression and assign it in Output stream Object
 * @author MatanA
 *
 */
public class MyCompressorOutputStream extends OutputStream {

	private OutputStream out;
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
		int last = (int)bytes[9];
		int counter = 0;
		
		for (int i = 9; i < bytes.length; i++) 
		{
			if ((int)bytes[i] == last) 
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
				last = (int)bytes[i];
				counter = 1;
			}
		}		
		
		// write the last byte
		out.write(last);
		out.write(counter);
		
		
	}
	

}
