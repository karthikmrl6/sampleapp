package com.marlabs;

import java.util.*;
import java.io.*;
import java.lang.*;

public class Arraylisttoarray {

public static void main(String args[])throws IOException

{

//* Element[] array = {new Element(1), new Element(2), new Element(3)};
BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

System.out.println("Enter the no of elements of an array");

int n =  Integer.parseInt(reader.readLine());

String arr[] = new String[n];

System.out.println("Enter the elements of the array "+n);
//arr[0]= reader.nextLine();	

for(int i=0;i<n+1;i++)
{
arr[i]= reader.readLine();	
System.out.println(i);

}
	
List<String> list = new ArrayList(Arrays.asList(arr));
/*for(int i=0;i<n;i++)
{

//*System.out.println(arr[i]);
	
}
*/
System.out.println(list);

}
	
}
