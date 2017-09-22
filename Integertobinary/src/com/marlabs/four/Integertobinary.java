package com.marlabs.four;

import java.util.*;

public class Integertobinary {

public void convertBinary(int num)
{
	
	int binary[] = new int[40];
    int index = 0;
    int maxLength = 0;
    int tempLength = 0;
    while(num > 0)
    {
      binary[index++] = num%2;
      num = num/2;
    }
    
    System.out.println("The binary number is");
    
    for(int i = index-1;i >= 0;i--)
    {
      System.out.print(binary[i]);
    }	
    
    
    for(int i = 0; i < index; i++)
    {
    	if (binary[i] == 0) {
            tempLength++;
        } 
    	else {
            tempLength = 0;
        }

        if (tempLength > maxLength) {
            maxLength = tempLength;
        }
    }
    System.out.println("     ");
    System.out.println("Maximum number of consecutive zeros : " + maxLength);
	
}


public static void main(String a[])
{
    Integertobinary obj = new Integertobinary();
    int num1;
    System.out.println("Enter the integer that has to be converted into binary: ");
    Scanner in = new Scanner(System.in);
    num1 = in.nextInt();
    obj.convertBinary(num1);
    
 }

}