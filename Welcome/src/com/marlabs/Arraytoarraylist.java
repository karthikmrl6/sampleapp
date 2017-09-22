package com.marlabs;

import java.util.*;

public class Arraytoarraylist {

public static void main(String args[])
{

List<String> List1 = new ArrayList<String>();
List1.add("stock1");
List1.add("stock2");

String Arr1[] = new String[List1.size()];
Arr1 = List1.toArray(Arr1);


for(int i=0;i<Arr1.length;i++)
{
	
System.out.println(Arr1[i]);	
	
}	
	
}
	
}
