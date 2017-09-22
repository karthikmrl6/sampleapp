package com.marlabs;

import java.util.*;

public class Sumofnumbersinintegerarray {

public static void main(String args[])
{
	int sum =0;
	Scanner reader= new Scanner(System.in);
	System.out.println("How many number you want to enter?");
	int num = reader.nextInt();
	int array[] = new int[num];
	System.out.println("Enter the " +num+ " numbers now");
	for(int i=0; i<array.length; i++)
	{
	  array[i] = reader.nextInt();
	  sum = sum + array[i];
	}
	
    System.out.println(array.length);
	System.out.println("The sum of all numbers = " +sum);
}
	
}
