package com.marlabs;

import java.util.*;
import java.lang.*;

public class Repeatastring {

public static void main(String args[])		
{

Scanner reader = new Scanner(System.in);
System.out.println("Enter a string");
String input = reader.nextLine();
System.out.println("How many times you want to repeat the string?");
int n = reader.nextInt();
String input1 = input;
for(int i =0;i<n-1;i++)
{
input1+= input;	
}
System.out.println(input1);
}
}
