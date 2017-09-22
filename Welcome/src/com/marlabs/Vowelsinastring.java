package com.marlabs;

import java.util.*;

public class Vowelsinastring {

	public static void main(String args[])
	{
	Scanner reader = new Scanner(System.in);
	System.out.println("Enter a string");
	String input = reader.nextLine();
	String input1 = input.toLowerCase();
	int vowelCount = 0;
	for (int i = 0; i < input1.length(); ++i) {
	    switch(input1.charAt(i)) {
	        case 'a':
	        case 'e':
	        case 'i':
	        case 'o':
	        case 'u':
	            vowelCount++;
	            break;
	        default:
	            //don't do anything
	    }
	}
	
	System.out.println("Total no of vowels = " +vowelCount);
	}
}
