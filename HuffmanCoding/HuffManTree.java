package HuffmanCoding;

import java.io.*;

import Minheap.Heap;

public class HuffManTree 
{
	@SuppressWarnings("rawtypes")
	private class HuffNode implements Comparable
	{
		public int value;
		public int count;
		public HuffNode parent,left,right;
		
		public HuffNode(int v,int c, HuffNode p, HuffNode l,HuffNode r)
		{
			value = v;
			count = c;
			parent = p;
			left = l;
			right = r;
		}
		public int compareTo(Object receivedArg)
		{
			return count - ((HuffNode)receivedArg).count;
		}
	}
	public static int NOTVALUE = -2;
	public static int ERROR = -3;
	public static int EOF = 255;
	private HuffNode root = null;
	private int[] count;
	private HuffNode[] nodes;
	
	public HuffManTree()
	{
		count = new int[256];
	}
	private void makeHuffTree()
	{
		nodes = new HuffNode[256];
		
		Heap heap = new Heap();
		
		for(int i=0;i<count.length;i++)
			if(count[i]>0)
			{
				nodes[i] = new HuffNode(i,count[i],null,null,null);
				heap.insert(nodes[i]);
			}
		nodes[EOF] = new HuffNode(EOF,1,null,null,null);
		heap.insert(nodes[EOF]);
		
		HuffNode temp1, temp2, p;
		temp1 = (HuffNode)heap.removeMin();	
		
		while(!heap.isEmpty())
		{
			temp2 = (HuffNode)heap.removeMin();
			
			p = new HuffNode(NOTVALUE, temp1.count+temp2.count,null,temp1,temp2);
			temp1.parent = temp2.parent = p;
			heap.insert(p);
			
			temp1 = (HuffNode)heap.removeMin();
		}
		root = temp1;
	}
	public String Huffcode(int key)
	{
		HuffNode current = nodes[key];
		if(current == null)
			return null;
		
		String code = "";
		HuffNode p = current.parent;
		
		while(p != null)
		{
			if(p.left == current)
				code = '0' + code;
			else if(p.right == current)
				code = '1' + code;
			
			current = p;
			p = current.parent;
		}
		return code;
	}
	public int ReturnChar(String huffcode)
	{
		HuffNode temp = root;
		for(int i=0; i<huffcode.length();i++)
		{
			if(temp == null)
				return ERROR;
			if(huffcode.charAt(i)=='0')
				temp = temp.left;
			else
				temp = temp.right;
		}
		return temp.value;
	}
	public void Frequence(InputStream in) throws IOException
	{
		int buf = in.read();
		while(buf != -1)
		{
			count[buf]++;
			buf = in.read();
		}
		
		makeHuffTree();
	}
	public void WriteFrquence(DataOutputStream out) throws IOException
	{
		for(int i=0; i<count.length; i++)
			if(count[i]>0)
			{
				out.writeByte(i);
				out.writeInt(count[i]);
			}
		out.writeByte(0);
		out.writeInt(0);
	}
	public void ReadFrequence(DataInputStream in) throws IOException
	{
		byte key = in.readByte();
		int c = in.readInt();
		while(c != 0)
		{
			count[key] = c;
			key = in.readByte();
			c = in.readInt();
		}
		makeHuffTree();
	}
}
