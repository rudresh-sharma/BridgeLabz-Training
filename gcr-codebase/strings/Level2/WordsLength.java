/*

Write a program to split the text into words and find the shortest and longest strings in a given text
Hint => 
Take user input using the Scanner nextLine() method 
Create a Method to split the text into words using the charAt() method without using the String built-in split() method and return the words.
Create a method to find and return a string's length without using the length() method. 
Create a method to take the word array and return a 2D String array of the word and its corresponding length. Use String built-in function String.valueOf() to generate the String value for the number
Create a Method that takes the 2D array of word and corresponding length as parameters, find the shortest and longest string and return them in an 1D int array. 
The main function calls the user-defined methods and displays the result. 


*/



import java.util.Scanner;
public class  WordsLength{	
	public static void main(String[] args){

	// Taking input
	Scanner in = new Scanner(System.in);	
	System.out.print("Enter the string: ");
	String strOriginal = in.nextLine();
	String str = strOriginal.trim();
	int length = getLength(str); 
	int[] spacesIndex = getSpaceIndex(str, length);
	String[] manualWords = getWords(spacesIndex,str, length);
 	String[][] wordsWithLength = getWordsWithLength(manualWords);


	// Display words in given text with length and prinnting shortest and longest word with length and word
	int[] minMaxLength = MaxandMinLengthWords(wordsWithLength);

	displayWordsWithLength(wordsWithLength);
	System.out.println();
 	System.out.println();
	System.out.println("Shortest word length : " + minMaxLength[0]);
	System.out.println("Longest word length  : " + minMaxLength[1]);


	in.close();



	}



	// Method to get length using manual way
	public static int getLength(String str){
		int count = 0;
		int asciiSum =0;
		
		try{
			int i=0;
			while(true){
				asciiSum += (int) str.charAt(i);
				i++;
				count++;
			}
		}
		catch(Exception e){
			return count;
		}
	}



	// Method to get Space indexes 
	public static int[] getSpaceIndex(String text, int length){

		int spaceCount = 0;
			for (int i = 0; i < text.length(); i++) {
				if (text.charAt(i) == ' ') {
        				spaceCount++;
    				}
			}

		int[] spaceIndex = new int[spaceCount];
		int index = 0;
			for(int i=0; i<length; i++){
				if(text.charAt(i) == ' '){
					spaceIndex[index++] = i;
					
				}
			}

		return spaceIndex;

	}


	// Method for getting words manually
	public static String[] getWords(int[] spaceIndex, String str, int length) {

		int noOfWords = spaceIndex.length + 1;
		String[] words = new String[noOfWords];
		int indexSpace = 0;
		int indexWords = 0;
		String temp = "";

			for (int i = 0; i < length; i++) {
				if (indexSpace < spaceIndex.length && i == spaceIndex[indexSpace]) {
					words[indexWords++] = temp;
					temp = "";
					indexSpace++;
        			} else {
				temp += str.charAt(i);
        			}
    			}

    			// store last word
    			words[indexWords] = temp;

    			return words;
	}




	 // Method to give length to words
	public static String[][] getWordsWithLength(String[] manualWords){
		int size = manualWords.length;
		String[][] wordsWLength = new String[size][2];


			for(int i=0; i<size; i++){
				wordsWLength[i][0] = manualWords[i];
				wordsWLength[i][1] = String.valueOf(getLength(manualWords[i]));
			}

		return wordsWLength;

	}



		// Method to get the maximum length words
	public static int getMaxWordLength(String[][] data) {
		int max = 0;
		for (int i = 0; i < data.length; i++) {
        		if (data[i][0].length() > max) {
				max = data[i][0].length();
        		}
    		}
    		return max;
	}

	
	// Displaying words with length in tabular form
	public static void displayWordsWithLength(String[][] data) {
		int maxLen = getMaxWordLength(data);
		int columnWidth = maxLen + 3; // extra spacing

 		System.out.printf("%-" + columnWidth + "s%s%n", "Word", "Length");

 
		for (int i = 0; i < columnWidth + 6; i++) {
			System.out.print("-");
		}
		System.out.println();

 		for (int i = 0; i < data.length; i++) {
        		int len = Integer.parseInt(data[i][1]);
        		System.out.printf("%-" + columnWidth + "s%d%n", data[i][0], len);
    		}
	}


	// Method for getting max and min length in words
	public static int[] MaxandMinLengthWords(String[][] wordsWithLength){
		int min = Integer.parseInt(wordsWithLength[0][1]);
		int max = Integer.parseInt(wordsWithLength[0][1]);
		int[] minMaxLength = new int[2];

			for(int i=1; i<wordsWithLength.length; i++){
				if(Integer.parseInt(wordsWithLength[i][1])<min){
					min = Integer.parseInt(wordsWithLength[i][1]);
				}
				if(Integer.parseInt(wordsWithLength[i][1])>max){
					max = Integer.parseInt(wordsWithLength[i][1]);
				}
			}
		
		minMaxLength[0] = min;
		minMaxLength[1] = max;

		return minMaxLength;

	}

}




