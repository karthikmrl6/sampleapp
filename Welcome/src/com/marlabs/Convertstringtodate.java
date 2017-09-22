package com.marlabs;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Convertstringtodate {
	
public static void main(String args[])
{

Scanner reader = new Scanner(System.in);
System.out.println("Enter the string in the format Month dd, yyyy");
String input = reader.nextLine();
//*String string = "January 2, 2010";
DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy", Locale.ENGLISH);
LocalDate date = LocalDate.parse(input, formatter);
System.out.println(date);	
	
}

}
