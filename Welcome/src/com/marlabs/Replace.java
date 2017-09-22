package com.marlabs;

import java.io.*;

public class Replace 
{
   public static void main(String args[]){
      String Str = "Welcome to Tutorialspoint.com";
      String Str1 = "Welcome to Tutorialspoint.com";

//*  String Str = new String("Welcome to Tutorialspoint.com");

      System.out.print("Return Value :" );
      System.out.println(Str.replace('o', 'T'));

      System.out.print("Return Value :" );
      System.out.println(Str.replace('l', 'D'));
   }
}