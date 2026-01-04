/*

Toggle Case of Characters
Problem:
Write a Java program to toggle the case of each character in a given string. Convert
uppercase letters to lowercase and vice versa.

*/


import java.util.Scanner;
public class ToggleCharacters{
	public static void main(String[] args){

	// Taking input
	Scanner in = new Scanner(System.in);
	System.out.print("Enter the string: ");
	String str = in.nextLine();
	

	// Toggling characters
	toggleCharacters(str);



	in.close();

	}



	// Method to toggle characters and print
	public static void toggleCharacters(String str){
		
		for(int i=0; i<str.length(); i++){
			char ch = str.charAt(i);
			if(ch>='A' && ch <= 'Z'){
				ch = (char) ((int) ch+32);
				System.out.print(ch);
			}
			else if(ch>='a' && ch <= 'z'){
				ch = (char) ((int) ch-32);	
				System.out.print(ch);
			}
			else{
				System.out.print(ch);
			}
		}
	}

}
		






