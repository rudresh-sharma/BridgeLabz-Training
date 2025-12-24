
import java.util.Scanner;
public class AnagramString{
	public static void main(String[] args){
		
	// Taking input
	Scanner in = new Scanner(System.in);
	System.out.print("Enter the string 1: ");
	String str1 = in.nextLine();
	System.out.print("Enter the string 2: ");
	String str2 = in.nextLine();

	
	// Checking strings are anagram or not
	boolean isAnagram = isAnagram(str1, str2);

	if(isAnagram){
		System.out.println("Yes anagram");
	}
	else{
		System.out.println("Not anagram");
	}



	in.close();

	}



	// Method to check anagram
	public static boolean isAnagram(String str1,String str2){

	int[] freq1 = new int[256];
	int[] freq2 = new int[256];
		
		if(str1.length() != str2.length()){
			return false;
		}
		

		for(int i=0; i<str1.length(); i++){
			int asciiValue = (int) str1.charAt(i);
			freq1[asciiValue]++;
		}


		for(int i=0; i<str2.length(); i++){
			int asciiValue = (int) str2.charAt(i);
			freq2[asciiValue]++;
		}
	
		for(int i=0; i<256; i++){
			if(freq1[i] !=  freq2[i]){
				return false;
			}
		}

		return true;

	}

}

