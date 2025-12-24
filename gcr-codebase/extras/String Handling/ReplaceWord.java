/*


*/Write a replace method in Java that replaces a given word with another word in a
sentence:

*/



import java.util.Scanner;
public class ReplaceWordInSentence {
    public static void main(String[] args) {


	// Taking inputs
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the sentence: ");
        String sentence = in.nextLine();
        System.out.print("Enter the word to replace: ");
        String oldWord = in.nextLine();
        System.out.print("Enter the new word: ");
        String newWord = in.nextLine();


	// Replacing word 
        String result = replaceWord(sentence, oldWord, newWord);

        System.out.println("Modified Sentence: " + result);

        in.close();
    }

    // Custom replace method
    public static String replaceWord(String sentence, String oldWord, String newWord) {

        String result = "";
        int i = 0;

        while (i <= sentence.length() - oldWord.length()) {

            boolean match = true;

            // Check word match
            for (int j = 0; j < oldWord.length(); j++) {
                if (sentence.charAt(i + j) != oldWord.charAt(j)) {
                    match = false;
                    break;
                }
            }

            if (match) {
                result = result + newWord;
                i = i + oldWord.length(); // skip old word
            } else {
                result = result + sentence.charAt(i);
                i++;
            }
        }

        // Append remaining characters
        while (i < sentence.length()) {
            result = result + sentence.charAt(i);
            i++;
        }

        return result;
    }
}
