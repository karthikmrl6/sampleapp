package com.marlabs;

import java.util.*;

public class Stringtouppercase {

	public static void main(String args[])
	{
		Scanner reader = new Scanner(System.in);
		System.out.println("Enter the string");
		String input = reader.nextLine();
		StringBuilder sb = new StringBuilder(input);
		int length1 = sb.length();
		for(int i =0;i<length1;i++)
		{
			if(Character.isLowerCase(sb.charAt(i)))
			{
			   sb.setCharAt(i, (char)(sb.charAt(i)- 32));
			}
		}
		System.out.println("The modified string is " +sb);
	}
}
