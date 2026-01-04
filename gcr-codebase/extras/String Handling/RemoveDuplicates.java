/*

4. Remove Duplicates from a String
Problem:
Write a Java program to remove all duplicate characters from a given string and return
the modified string.

*/


import java.util.Scanner;
public class RemoveDuplicates{
	public static void main(String[] args){
	
	// Taking inputs
	Scanner in = new Scanner(System.in);
	System.out.print("Enter the string: ");
	String str = in.nextLine();


	String rmDuplicates = removeDuplicates(str);

	System.out.println(rmDuplicates);

	in.close();



	}



	// Method to remove duplicates
	public static String removeDuplicates(String str){
		char[] tempArray = str.toCharArray();
		String modify = "";
		boolean notInModify = true;
		for(int i=0; i<tempArray.length; i++){
			char ch = tempArray[i];
			notInModify = true;
			for(int j=0; j<modify.length(); j++){
				if(ch == modify.charAt(j)){
					notInModify = false;
				}
			}
			if(notInModify){
				modify += String.valueOf(ch);
			}
		}


		return modify;
	}


}		












