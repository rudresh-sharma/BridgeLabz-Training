
/*

Create a program to find the bonus of 10 employees based on their years of service as well as the total bonus amount the 10-year-old company Zara has to pay as a bonus, along with the old and new salary.
Hint => 
Zara decides to give a bonus of 5% to employees whose year of service is more than 5 years or 2% if less than 5 years
Create a Method to determine the Salary and years of service and return the same. Use the Math.random() method to determine the 5-digit salary for each employee and also use the random method to determine the years of service. Define 2D Array to save the salary and years of service.
Write a Method to calculate the new salary and bonus based on the logic defined above and return the new 2D Array of the latest salary and bonus amount 
Write a Method to Calculate the sum of the Old Salary, the Sum of the New Salary, and the Total Bonus Amount and display it in a Tabular Format

*/

public class EmployeesBonus {

    public static void main(String[] args) {

        int employees = 10;

        double[][] oldData = generateSalaryAndService(employees);
        double[][] newData = calculateBonusAndNewSalary(oldData);

        displayReport(oldData, newData);
    }

    // Method to generate salary and years of service
    public static double[][] generateSalaryAndService(int n) {

        double[][] data = new double[n][2];

        for (int i = 0; i < n; i++) {
            data[i][0] = (int)(Math.random() * 90000) + 10000; // 5-digit salary
            data[i][1] = (int)(Math.random() * 10) + 1;       // years of service
        }
        return data;
    }

    // Method to calculate bonus and new salary
    public static double[][] calculateBonusAndNewSalary(double[][] oldData) {

        int n = oldData.length;
        double[][] result = new double[n][2];

        for (int i = 0; i < n; i++) {
            double salary = oldData[i][0];
            double years = oldData[i][1];

            double bonus;
            if (years > 5) {
                bonus = salary * 0.05;
            } else {
                bonus = salary * 0.02;
            }

            double newSalary = salary + bonus;

            result[i][0] = bonus;
            result[i][1] = newSalary;
        }
        return result;
    }

    // Method to display report and totals
    public static void displayReport(double[][] oldData, double[][] newData) {

    double totalOld = 0, totalBonus = 0, totalNew = 0;

    System.out.printf("%-4s %-12s %-6s %-12s %-12s%n",
            "ID", "Salary", "Years", "Bonus", "New Salary");

    for (int i = 0; i < oldData.length; i++) {

        totalOld += oldData[i][0];
        totalBonus += newData[i][0];
        totalNew += newData[i][1];

        System.out.printf("%-4d %-12.2f %-6.0f %-12.2f %-12.2f%n",
                (i + 1),
                oldData[i][0],
                oldData[i][1],
                newData[i][0],
                newData[i][1]);
    }

    System.out.println();
    System.out.printf("%-22s %.2f%n", "Total Old Salary:", totalOld);
    System.out.printf("%-22s %.2f%n", "Total Bonus Paid:", totalBonus);
    System.out.printf("%-22s %.2f%n", "Total New Salary:", totalNew);
}

}
