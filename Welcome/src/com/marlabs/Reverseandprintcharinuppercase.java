package com.marlabs;

import java.util.*;

public class Reverseandprintcharinuppercase {

public static void main(String[] args)
{

Scanner reader = new Scanner(System.in);
String hello = reader.nextLine();
char c[] = hello.toCharArray();
int i = 0, j = c.length - 1;
while (i < j) {
char tmp = c[i];
c[i] = c[j];
c[j] = tmp;
i++;
j--;
}
System.out.println(new String(c));
for (int k=0;k<hello.length();k++)
{
if (Character.isUpperCase(hello.charAt(k))==true)
{
	
System.out.print(hello.charAt(k));
        
}
}  
}
}

