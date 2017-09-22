package com.marlabs;

public class Countofnumbers {
	
	public static void main(String args[])
	{
	
	int count = 0;	
	for(int i=0;i<=100;i++)
	{
		if(i%7==0)
		{
		  count = count + 1;
			
		}
	}
	
	System.out.println(count);
	
	}
}

