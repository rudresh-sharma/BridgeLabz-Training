/*

17. Online Quiz Application 🧠
Ask 5 questions (MCQs) from a user.
● Use arrays and for-loop.
● Record score.
● Switch for answer checking. Apply clear indentation and structured layout.

*/


import  java.util.Scanner;
public class OnlineQuizApp{
	public static void main(String[] args){

	// Taking input
	Scanner in = new Scanner(System.in);
	String[] questions = {"What is Take_A_Break", "Who is APJ Abdul Kalam", "Who is sachin", "who is Rudra" , "Who is soumya"};
	
	int score = 0;
	String[] answer = {"group of dhasu log", "The President", "KrishnaFriend", "Notanki", "Bandi kam ki"};

		for(int i=0; i<5; i++){
			System.out.println("\n" + questions[i]);
			printOptions(i);
			char option  = in.next().charAt(0);

				
			switch(option){
				case 'A':{
					if((i==0) || (i==4)){
					score++;
					}break;
					}

				case 'B':{
					if(i==2){
					score++;
					}break;
					}

				case 'C':{
					if(i==0){
					score++;
					}break;
					}

				case 'D':{
					if((i==3) || (i==1)){
					score++;
					}break;
					}
			}
		}
	
	
	System.out.println("Your score = " + score);

	in.close();

	}



	public static void printOptions(int i){
		
		if(i== 0){
			System.out.println("A. group of dhasu log");
			System.out.println("B. Working group");
			System.out.println("C. NeverOpen group");
			System.out.println("D. Notanki group\n");
		}
		else if(i==1){
			System.out.println("A. Prime Minister");
			System.out.println("B. TIT Student");
			System.out.println("C. Rudra Brother");
			System.out.println("D. Former President\n");
		}
		else if(i==2){
			System.out.println("A. 7 star codechef coder");
			System.out.println("B. KrishnaFriend");
			System.out.println("C. Optimistic person");
			System.out.println("D. Prime Minister\n");
		}
		else if(i==3){
			System.out.println("A. Notanki");
			System.out.println("B. 5 years old child");
			System.out.println("C. Jasus");
			System.out.println("D. Sabji wala\n");
		}
		else {
			System.out.println("A. Bandi kam ki");
			System.out.println("B. Singer");
			System.out.println("C. Chef");
			System.out.println("D. Hardworker\n");
		}


	}


}
