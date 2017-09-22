package com.marlabs;

import java.util.Scanner;

public class Multiplicationtable {

public static void main(String args[])
{
	int c,fact;
	
	Scanner reader = new Scanner(System.in);
	
	System.out.println("Enter a number");
	
	fact = reader.nextInt();
	
	int temp = fact;
	
	for(c=1;c<=10;c++)
	{
		fact = temp;
		fact = fact*c;
		System.out.println("the fact of " +(temp)+ "*" +c+" = " +fact);
	}
	
}
	
}
