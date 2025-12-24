/*

Find the Longest Word in a Sentence
Problem:
Write a Java program that takes a sentence as input and returns the longest word in the
sentence.

*/




import java.util.Scanner; 	
public class LongestWord{
	public static void main(String[] args){
	
	// Taking input
	Scanner in = new Scanner(System.in);
	System.out.print("Enter the sentence: ");
	String sentence = in.nextLine();
	sentence.trim();

	// Printng longest words
	int[] spaceIndex = getSpaceIndex(sentence);
	String[] words = getWords(sentence, spaceIndex);
	String longestWord = getLongestWord(words);

	System.out.println("Longest words : " + longestWord);

	in.close();


	}


	// Method to get space Index
	public static int[] getSpaceIndex(String sentence){

		int countSpace = 0;
		for(int i=0; i<sentence.length(); i++){
			if(sentence.charAt(i) == ' '){
				countSpace++;
			}
		}

		int index = 0;
		int[] spaceIndex = new int[countSpace];
		for(int i=0; i<sentence.length(); i++){
			if(sentence.charAt(i) == ' '){
 			spaceIndex[index++] = i;
			}
		}

		return spaceIndex;

	}


	// Method to get words
	public static String[] getWords(String sentence, int[] spaceIndex){
		int noOfWords = spaceIndex.length+1;
		String[] words = new String[noOfWords];

		int indexSpace = 0;
		int indexWords = 0;
		String temp = "";

		for(int i=0; i<sentence.length(); i++){
			if(indexSpace< spaceIndex.length && i == spaceIndex[indexSpace]){
				words[indexWords++] = temp;
				indexSpace++;
				temp = "";
			}

			else{
				temp += sentence.charAt(i);
			}
		}


		words[indexWords] = temp;

		return words;

	}


	// Method to get Longest words
	public static String getLongestWord(String[] words){
		String longest = words[0];
		
		for(int i=1; i<words.length; i++){
			if(words[i].length()>longest.length()){
				longest = words[i];
			}
		}


		return longest;

	}
		

}


	











