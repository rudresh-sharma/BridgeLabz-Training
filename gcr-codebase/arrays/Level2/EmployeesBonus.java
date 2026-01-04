/*

Create a program to find the bonus of 10 employees based on their years of service and the total bonus amount the company Zara has to pay, along with the old and new salary.
Hint => 
Zara decides to give a bonus of 5% to employees whose year of service is more than 5 years or 2% if less than 5 years
Define a double array to save salary and years of service for each of the 10 employees
Also define a double array to save the new salary and the bonus amount as well as variables to save the total bonus, total old salary, and new salary
Define a loop to take input from the user. If salary or year of service is an invalid number then ask the use to enter again. Note in this case you will have to decrement the index counter
Define another loop to calculate the bonus of 10 employees based on their years of service. Save the bonus in the array, compute the new salary, and save in the array. Also, the total bonus and total old and new salary can be calculated in the loop
Print the total bonus payout as well as the total old and new salary of all the employees


*/


import java.util.Scanner;
public class EmployeesBonus{
	public static void main(String[] args){

	// Taking inputs
	Scanner in = new Scanner(System.in);
	System.out.println("Enter the salary and year of service of 10 Employees: ");
	double[][] oldSalaryAndService = new double[10][2];
	double[] newSalaryAfterBonus = new double[10];
		for(int i=0; i<10; i++){
			System.out.print("Enter the salary and YOS of employee " + (i+1) +" : ");
			for(int j=0; j<2; j++){
				while(true){
					double num = in.nextDouble();
					if(num>0) {
						oldSalaryAndService[i][j] = num;
					 	break;
					}
					else {
						System.out.print("Enter correct salary and YOS again: ");
					}
				}
			}
		}
	
	

	// Calculating new salary and printing both the money need to pay and new and old salary 
	double totalBonus = 0; 
		for(int i=0; i<10; i++){
			if(oldSalaryAndService[i][1] <5){
				double bonus = ((oldSalaryAndService[i][0]*2)/100);
				newSalaryAfterBonus[i] = oldSalaryAndService[i][0] + bonus;
				totalBonus += bonus;
			}
			else{
				double bonus = ((oldSalaryAndService[i][0]*5)/100);
				newSalaryAfterBonus[i] = oldSalaryAndService[i][0] + bonus;
				totalBonus += bonus;
			}
		}
	System.out.println("Total Bonus Amount = " + totalBonus);
		for(int i=0; i<10; i++){
			System.out.println("Salaries of employee " + (i+1) +" :");
			System.out.println("Old salary = " + oldSalaryAndService[i][0] );
			System.out.println("New salary = " + newSalaryAfterBonus[i]);
		
		}


	in.close();

	}

}







