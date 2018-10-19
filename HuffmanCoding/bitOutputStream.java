package HuffmanCoding;

import java.io.*;

public class bitOutputStream 
{
    private DataOutputStream out;
    private int buffer;
    private int location;

	public bitOutputStream(OutputStream os) throws IOException
	{
		buffer = 0;
		location = 0;
		out = new DataOutputStream(os);
	}
	public void flush() throws IOException
	{
		if(location == 0)
			return;
		out.write(buffer);//한 바이트를 파일로 출력한다.
		//다시 한바이트를 만들 준비
		location = 0;
		buffer = 0;
	}
	private void writeBit(char c) throws IOException
	{
		if(c == '1')
		{
			buffer = buffer | (1<<location); //한 비트씩 버퍼에 저장시킨다.. 순서대로 저장시킴
			location++;
		}
		else if( c == '0')
			location++; //location이란 바이트 8자리의 자리위치 ex. 1번째~8번째까지 위치변수
		else if( c != '0')
			return;
		
		if(location == 8)
			flush(); //만약에 8비트 즉, 1바이트가 꽉 찻다면 파일로 출력함.
	}
	public void writeCode(String code) throws IOException
	{
		for(int i=0;i<code.length();i++)
			writeBit(code.charAt(i)); //입력받은 허프만코드의 한자리씩 즉, 한비트씩 데이터를 처리하기위해서 함수 입력인자로 함
	}
	public void close() throws IOException
	{
		flush();
		out.close();
	}
}