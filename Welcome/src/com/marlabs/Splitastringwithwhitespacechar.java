package com.marlabs;

import java.util.*;

import java.lang.*;

public class Splitastringwithwhitespacechar {

public static void main(String args[])
{
	Scanner reader = new Scanner(System.in);
	System.out.println("Enter a string with white space characters");
	String input = reader.nextLine();
	String input1 = "";
	String array[] = input.split("\\s+");
	for(int i=0;i<array.length;i++)
	{
	input1 = input1.concat(array[i]);
	}
	System.out.println(input1);
}	
	
}
