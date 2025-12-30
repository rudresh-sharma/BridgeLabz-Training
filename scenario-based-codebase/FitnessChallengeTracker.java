/*

13. Sandeep’s Fitness Challenge Tracker 🏋️
♂Each day Sandeep completes a number of push-ups.
● Store counts for a week.
● Use for-each to calculate total and average.
● Use continue to skip rest days.

*/




import java.util.Scanner;
public class FitnessChallengeTracker{
	public static void main(String[] args){

	// taking inputs
	Scanner in = new Scanner(System.in);
	int[] pushUps = new int[7];
	int restDay = 0;
	int total = 0, average = 0, countdaysPushUpDid = 0;
		for(int i=0; i<7; i++){
			System.out.print("Enter Number of push ups you done in day " + (i+1)+ ": ");
			pushUps[i] = in.nextInt();
	
		}

		for(int day : pushUps){
			if(day == 0){
				continue;
			}
			else{
				total += day;
				countdaysPushUpDid++;
			}
		}



	average = total/countdaysPushUpDid;
	System.out.println("Total push ups did = " + total);
	System.out.println("Average push ups did = " + average);


	in.close();

	}


}

			



