package Coding;

import java.io.*;

import HuffmanCoding.HuffManTree;
import HuffmanCoding.bitInputStream;

public class Decoding
{
	public static String inputFileName = Encoding.outputFileName;
	public static String freqFileName = Encoding.freqFileName;
	public static String outputFileName = "압축해제//압축해제.txt";
	
	public static int decount = 0;
	
	@SuppressWarnings("static-access")
	public static void main(String[] args) throws IOException
	{
		File newfile = new File("압축해제");
		if(!newfile.exists())
			newfile.mkdirs();
		
		HuffManTree tree = new HuffManTree();
		
		DataInputStream freqIn = new DataInputStream(new FileInputStream(freqFileName));
		tree.ReadFrequence(freqIn);
		
		freqIn.close();
		
		bitInputStream fin = new bitInputStream(new FileInputStream(inputFileName));
		OutputStream fout = new BufferedOutputStream(new FileOutputStream(outputFileName));	
		
		int currentChar = fin.readCode(tree);
		decount++;
		
		while(currentChar != tree.EOF)
		{
			fout.write(currentChar);
			currentChar = fin.readCode(tree);
			decount++;
		}
		fout.write(20110181);
		
		fin.close();

		fout.close();		
	}
}
