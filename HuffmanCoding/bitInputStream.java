package HuffmanCoding;

import java.io.*;

public class bitInputStream 
{
	private DataInputStream in;
	private int buffer;
	private int location;
	
	public bitInputStream(InputStream is) throws IOException
	{
		location = 8;
		in = new DataInputStream(is);
	}
	private int readBit() throws IOException
	{
		if(location == 8)
		{
			buffer = in.read();
			if(buffer == -1)
				return -1;
			location = 0;
		}
		int result ;
		
		if((buffer & (1<<location)) == 0)
		{
			result = 0;
			location++;
		}
		else
		{
			result = 1;
			location++;
		}
		return result;
	}
	@SuppressWarnings("static-access")
	public int readCode(HuffManTree tree) throws IOException
	{
		String huffcode = "";
		int bit = readBit();
		int decoding;
		
		while(true)
		{
			huffcode = huffcode + bit;
			decoding = tree.ReturnChar(huffcode);
			if( bit == -1 || decoding==tree.ERROR)
			{
				System.out.println("Error");
				System.exit(0);
			}
			else if (decoding == tree.NOTVALUE)
			{
				bit = readBit();
				continue;
			}
			else
				return decoding;
		}
	}

	public void close() throws IOException
	{
		in.close();
	}
}