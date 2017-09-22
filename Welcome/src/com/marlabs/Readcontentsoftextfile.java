package com.marlabs;

import java.io.BufferedReader;
import java.io.FileReader;

public class Readcontentsoftextfile {

public static void main (String args[]) throws Exception {
     
		FileReader fr = new FileReader("c:\\question5test1.txt");	               
		BufferedReader br = new BufferedReader (fr);
		String line = br.readLine ();
		while(line != null)
		{
		   
		System.out.println(line);
		line = br.readLine();
		}
}

}
