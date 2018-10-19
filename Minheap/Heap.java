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

	public Heap(@SuppressWarnings("rawtypes") Comparable[] array) // array 내용을 바로 heap에 넣음
	{
		size = array.length; // data의 갯수는 array길이만큼.
		data = new Comparable[array.length + 1]; // array 길이보다 1 크게 초기화함.
		for (int i = 0; i < array.length; i++)
			data[i + 1] = array[i]; // data들을 넣음
		buildHeap(); // heap으로 만듬
	}

	private void buildHeap() 
	{
		for (int i = size / 2; i > 0; i--)
			compareDown(i);// child를 갖고 있는 노드 중 가장 아래 노드부터 맨 위 root 노드까지 아래로 내릴 수 있는곳까지 내림.
	}

	@SuppressWarnings("unchecked")
	private void compareDown(int index) // child와 비교해 내려갈 수 있으면 내려가는 메쏘드
	{
		int child = index * 2; // index를 두배하면 child중 왼쪽 node
		if (child > size) // 만약 child가 없으면 그냥 return
			return;
		
		if (child != size && data[child].compareTo(data[child + 1]) > 0 ) // 오른쪽 child가 존재하고, 그 node값이 왼쪾보다 작으면 왼쪽 node대신 오른쪽 node를 비교 대상으로 정함, 비교대상으로 정해진 child와 현재 node를 비교해서 현재 node가 더작으면 두 node를 바꿈
			child++;											
		
		if (data[index].compareTo(data[child]) > 0) // 비교 대상으로 정해진 child와 현재 node를 비교해서 현재 node가 더 작으면
		{
			swap(index, child); // 두 node를 바굼
			compareDown(child); // 바꾼 아래 node에 대해 같은 작업 반복 (recursive call)
		}
		return;
	}

	@SuppressWarnings("unchecked")
	private void compareUp(int index) // 위와 반대로 수행함. 같은 원리
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
	private void swap(int index1, int index2) // node 두 개를 바꿈
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
	public Comparable removeMin() // 가장 작은 node를 뱉으면서 삭제
	{
		if (size < 1)
		{
			System.out.println("Error : remove element with empty heap.");
			System.exit(0);
		}
		Comparable minValue = data[1];
		data[1] = data[size--]; // 맨 위를 빼고, 맨 뒤에 노드를 맨 위로 올림
		compareDown(1); // 그 노드를 내릴 수 있는곳까지 내림
		return minValue;
	}

	public void insert(@SuppressWarnings("rawtypes") Comparable x)
	{
		if (++size == data.length) // 크기를 1 플러스 하고, 그게 현재 array 길이보다 길면 array를 두배함.
			doubleArray();
		data[size] = x; // 마지막에 데이타 추가
		compareUp(size); // 그 노드를 올릴 수 있는 곳까지 올림.
	}

	private void doubleArray() // array 크기를 두배함
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