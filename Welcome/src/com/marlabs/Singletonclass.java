package com.marlabs;

public class Singletonclass {


	   private static Singletonclass singleton = new Singletonclass( );
	   
	   private Singletonclass(){ }
	   
	   
	   protected static void demoMethod( )
	   {
	      System.out.println("demoMethod for singleton"); 
	   }
	   
}