package Minheap;

import java.io.*;

@SuppressWarnings("unused")
public class Heap {
	private static final int DEFAULT_CAPACITY = 100;
	@SuppressWarnings("rawtypes")
	private Comparable[] data;
	private int size;

	public Heap()
	{
		data = new Comparable[100];
		size = 0;
	}

	public Heap(@SuppressWarnings("rawtypes") Comparable[] array) // array ������ �ٷ� heap�� ����
	{
		size = array.length; // data�� ������ array���̸�ŭ.
		data = new Comparable[array.length + 1]; // array ���̺��� 1 ũ�� �ʱ�ȭ��.
		for (int i = 0; i < array.length; i++)
			data[i + 1] = array[i]; // data���� ����
		buildHeap(); // heap���� ����
	}

	private void buildHeap() 
	{
		for (int i = size / 2; i > 0; i--)
			compareDown(i);// child�� ���� �ִ� ��� �� ���� �Ʒ� ������ �� �� root ������ �Ʒ��� ���� �� �ִ°����� ����.
	}

	@SuppressWarnings("unchecked")
	private void compareDown(int index) // child�� ���� ������ �� ������ �������� �޽��
	{
		int child = index * 2; // index�� �ι��ϸ� child�� ���� node
		if (child > size) // ���� child�� ������ �׳� return
			return;
		
		if (child != size && data[child].compareTo(data[child + 1]) > 0 ) // ������ child�� �����ϰ�, �� node���� �ަU���� ������ ���� node��� ������ node�� �� ������� ����, �񱳴������ ������ child�� ���� node�� ���ؼ� ���� node�� �������� �� node�� �ٲ�
			child++;											
		
		if (data[index].compareTo(data[child]) > 0) // �� ������� ������ child�� ���� node�� ���ؼ� ���� node�� �� ������
		{
			swap(index, child); // �� node�� �ٱ�
			compareDown(child); // �ٲ� �Ʒ� node�� ���� ���� �۾� �ݺ� (recursive call)
		}
		return;
	}

	@SuppressWarnings("unchecked")
	private void compareUp(int index) // ���� �ݴ�� ������. ���� ����
	{
		int parent = index/2;
		
		if(parent < 1)
			return;
		if(data[index].compareTo(data[parent]) < 0)
		{
			swap(index,parent);
			compareUp(parent);
		}
	}

	@SuppressWarnings("rawtypes")
	private void swap(int index1, int index2) // node �� ���� �ٲ�
	{
		if (index1 > size || index2 > size)
		{
			System.out.println("Error");
			System.exit(0);
		} 
		else
		{
			Comparable temp;
			temp = data[index1];
			data[index1] = data[index2];
			data[index2] = temp;
		}
	}

	@SuppressWarnings("rawtypes")
	public Comparable removeMin() // ���� ���� node�� �����鼭 ����
	{
		if (size < 1)
		{
			System.out.println("Error : remove element with empty heap.");
			System.exit(0);
		}
		Comparable minValue = data[1];
		data[1] = data[size--]; // �� ���� ����, �� �ڿ� ��带 �� ���� �ø�
		compareDown(1); // �� ��带 ���� �� �ִ°����� ����
		return minValue;
	}

	public void insert(@SuppressWarnings("rawtypes") Comparable x)
	{
		if (++size == data.length) // ũ�⸦ 1 �÷��� �ϰ�, �װ� ���� array ���̺��� ��� array�� �ι���.
			doubleArray();
		data[size] = x; // �������� ����Ÿ �߰�
		compareUp(size); // �� ��带 �ø� �� �ִ� ������ �ø�.
	}

	private void doubleArray() // array ũ�⸦ �ι���
	{
		@SuppressWarnings("rawtypes")
		Comparable[] newArray = new Comparable[data.length * 2];
		for (int i = 0; i < data.length; i++)
			newArray[i] = data[i];
		data = newArray;
	}

	public boolean isEmpty() 
	{
		return (size == 0);
	}
}