package Coding;

import java.io.*;
import java.awt.Dimension;
import java.io.*;

import javax.swing.JLabel;

import HuffmanCoding.HuffManTree;
import HuffmanCoding.bitOutputStream;
import Run.Run;
@SuppressWarnings("unused")
public class Encoding
{
	public static String freqFileName = "압축자료//데이터테이블.txt";
	public static String outputFileName = "압축자료//압축.moon";
	public static float count = 0;

	public static float zip;
	public static float averagebits;
	public static float totalTime;
	
	@SuppressWarnings("static-access")
	public static void main(String inputFileName) throws IOException
	{
		File newfile = new File("압축자료");
		if(!newfile.exists())
			newfile.mkdirs();
		
		long startTime = System.currentTimeMillis();
		
		HuffManTree tree = new HuffManTree();
		
		InputStream fin = new BufferedInputStream(new FileInputStream(inputFileName));
		tree.Frequence(fin);
		
		fin.close();
		
		DataOutputStream freqOut = new DataOutputStream(new FileOutputStream(freqFileName));
		tree.WriteFrquence(freqOut);
		
		freqOut.close();
		
		fin = new BufferedInputStream(new FileInputStream(inputFileName));
        bitOutputStream fout = new bitOutputStream(new FileOutputStream(outputFileName));
        
        int totalcount = 0;
        int buf = fin.read();
		count ++;
		String code = "";
		String totalcode = "";
		
		while(buf != -1)
		{
			code = tree.Huffcode(buf);
			totalcode += code;
			Run.lblNewLabel_3.setText(totalcode);
			totalcount += code.length();
			fout.writeCode(code);
			buf=fin.read();
			count++;
		}
		fout.writeCode(tree.Huffcode(tree.EOF));

		fout.close();
		
		File oFile = new File(inputFileName);
		File eFile = new File("압축자료//압축.moon");
		float size = oFile.length();
		float esize = eFile.length();
		zip = (size-esize)/size*100;
		int res = (int) zip;
		
		averagebits = (totalcount/count);
		long endTime = System.currentTimeMillis();
		totalTime =  (( endTime - startTime )/1000.0f) ;
	}
}
