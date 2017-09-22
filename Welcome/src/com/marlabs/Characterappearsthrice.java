package com.marlabs;

import java.util.*;

public class Characterappearsthrice {

public static void main(String args[])
{
	String str = "internationalize";
	int[] counts = new int[(int) Character.MAX_VALUE];
	
	for (int i = 0; i < str.length(); i++) 
	{
	    char charAt = str.charAt(i);
	    counts[(int) charAt]++;
	}

	for (int i = 0; i < counts.length; i++) {
	    if (counts[i] == 3)
	    System.out.println("Number of " + (char) i + ": " + counts[i]);
	}
}
}
