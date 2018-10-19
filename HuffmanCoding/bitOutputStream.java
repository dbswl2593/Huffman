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
		out.write(buffer);//�� ����Ʈ�� ���Ϸ� ����Ѵ�.
		//�ٽ� �ѹ���Ʈ�� ���� �غ�
		location = 0;
		buffer = 0;
	}
	private void writeBit(char c) throws IOException
	{
		if(c == '1')
		{
			buffer = buffer | (1<<location); //�� ��Ʈ�� ���ۿ� �����Ų��.. ������� �����Ŵ
			location++;
		}
		else if( c == '0')
			location++; //location�̶� ����Ʈ 8�ڸ��� �ڸ���ġ ex. 1��°~8��°���� ��ġ����
		else if( c != '0')
			return;
		
		if(location == 8)
			flush(); //���࿡ 8��Ʈ ��, 1����Ʈ�� �� ���ٸ� ���Ϸ� �����.
	}
	public void writeCode(String code) throws IOException
	{
		for(int i=0;i<code.length();i++)
			writeBit(code.charAt(i)); //�Է¹��� �������ڵ��� ���ڸ��� ��, �Ѻ�Ʈ�� �����͸� ó���ϱ����ؼ� �Լ� �Է����ڷ� ��
	}
	public void close() throws IOException
	{
		flush();
		out.close();
	}
}