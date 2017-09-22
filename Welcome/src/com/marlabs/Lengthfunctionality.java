package com.marlabs;

import java.util.*;

public class Lengthfunctionality {

public static void main(String args[])
{
	
Scanner reader = new Scanner(System.in);
String str = reader.nextLine();
	String str1 = str + '\0';
    int count = 0;

    for (int i = 0; str1.charAt(i) != '\0'; i++) {
        count++;
    }

    System.out.println(count);
}
}
