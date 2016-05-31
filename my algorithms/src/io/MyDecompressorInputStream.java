package io;

import java.io.IOException;
import java.io.InputStream;

public class MyDecompressorInputStream extends InputStream {

	private InputStream in;
	
	public MyDecompressorInputStream(InputStream in) 
	{
		this.in = in;
	}
	
	@Override
	public int read() throws IOException {
		return in.read();
	
	}
	public int read(byte[]bytes) throws IOException
	{
		read(bytes, 0, 9);	
		int last;
		int counter;
		
		int inSize= in.available();
		int k = 9 ;
		
		for(int i = 0 ; i < inSize ; i += 2)
		{	
			last = read();
			counter = read();
			
			for(int j = 0 ; j < counter ; j++)
			{
				bytes[k] = (byte)last;
				k++;
			}
		}
		
		return 0;
		
		
	}

}
