package com.marlabs;

import java.util.Scanner;

public class Copyanarrayintoanotherarray {

	public static void main(String args[])
	{
		
		Scanner reader= new Scanner(System.in);
		System.out.println("How many number you want to enter?");
		int num = reader.nextInt();
		int array[] = new int[num];
		int arr2[] = new int[num];
		System.out.println("Enter the " +num+ " numbers now");
		for(int i=0; i<array.length; i++)
		{
		  array[i] = reader.nextInt();
		  arr2[i]= array[i];
		}
		
		System.out.println("All the contents of array is copied into arr2. It's contents are");
		for(int i=0; i<arr2.length; i++)
		{
		  System.out.println(arr2[i]);
		}
		
	}
		
}
