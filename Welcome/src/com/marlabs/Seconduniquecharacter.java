package com.marlabs;

import java.util.*;

public class Seconduniquecharacter {
	
public static void main(String args[])
{
Scanner reader = new Scanner(System.in);
System.out.println("Enter a string");
String input = reader.nextLine();
Set<Character> repeating = new HashSet<>();
List<Character> nonRepeating = new ArrayList<>();
for (int i = 0; i < input.length(); i++) {
char letter = input.charAt(i);
if (repeating.contains(letter))
{
continue;
}
if (nonRepeating.contains(letter)) {
nonRepeating.remove((Character) letter);
repeating.add(letter);
} else 
{
nonRepeating.add(letter);
}
}

System.out.println("Second unique character is = ");
System.out.println(nonRepeating.get(0));
}

}
