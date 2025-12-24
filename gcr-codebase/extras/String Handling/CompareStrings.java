/*

Problem:
Write a Java program to compare two strings lexicographically (dictionary order) without
using built-in compare methods.
Example Input:
String 1: "apple"
String 2: "banana"

Expected Output:
"apple" comes before "banana" in lexicographical order

*/


import java.util.Scanner;
public class CompareStrings{
	public static void main(String[] args){

	// Taking input
	Scanner in = new Scanner(System.in);
	System.out.print("Enter the string 1: ");
	String str1 = in.next();
	System.out.print("Enter the string 1: ");
	String str2 = in.next();

	
	char ch1 = str1.charAt(0);
	char ch2 = str2.charAt(0);

	if(ch1>= 'A' && ch1<='Z'){
		ch1 = (char) ( (int) ch1 +32);
	}
	if(ch2>= 'A' && ch2<='Z'){
		ch2 = (char) ( (int) ch2 +32);
	}

	if(ch1<ch2){
		System.out.println(str1 + " comes before "+ str2 + " in lexicographical orderr");
	}
	else{
		System.out.println(str2 + " comes before "+ str1 + " in lexicographical orderr");
	}

	in.close();

	}

}





