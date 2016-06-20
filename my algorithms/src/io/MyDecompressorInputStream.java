package io;

import java.io.IOException;
import java.io.InputStream;
/**
 * This class is responsible for maze data decompression and extract it from Input stream Object
 * @author Matan Ashkenazi and Noee Cohen
 * @version - 1.0
 */
public class MyDecompressorInputStream extends InputStream {

	private InputStream in;
	/**
	 * C'tor
	 * @param in - any InputStream to wrapped required to decompress
	 */
	public MyDecompressorInputStream(InputStream in) 
	{
		this.in = in;
	}
	
	@Override
	public int read() throws IOException {
		return in.read();
	
	}
	/**
	 * This method read 9 bytes represents the meta data of the Maze3D and after that read and decompress the maze data from the bytes array
	 * @param bytes - bytes array represent the maze data
	 * @return - return 0 if the method run succeed
	 * @exception - IOException
	 */
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
