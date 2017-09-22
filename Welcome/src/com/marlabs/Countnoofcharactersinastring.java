package com.marlabs;

import java.util.*;
import java.lang.*;

public class Countnoofcharactersinastring {

	public static void main(String args[])
	{
		int count = 0;
		Scanner reader = new Scanner(System.in);
		System.out.println("Enter a string");
		String input = reader.nextLine();
		int length1 = input.length();
		for(int i=0;i<length1;i++)
		{
			if(Character.isUpperCase(input.charAt(i)))
			{
			   count = count + 1;
			}
		}
		
		System.out.println("The no of uppercase characters = " +count);
		
	}
}
