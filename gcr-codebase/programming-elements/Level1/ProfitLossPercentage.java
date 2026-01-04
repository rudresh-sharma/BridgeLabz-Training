/*

Create a program to calculate the profit and loss in number and percentage based on the cost price of INR 129 and the selling price of INR 191. 
Hint => 
Use a single print statement to display multiline text and variables.
Profit = selling price - cost price
Profit Percentage = profit / cost price * 100
I/P => NONE
O/P => 
The Cost Price is INR ___ and Selling Price is INR ___
The Profit is INR ___ and the Profit Percentage is ___


*/

// Program to calculate profit and profit percentage
public class ProfitLossPercentage {

    // main method
    public static void main(String[] args) {

        // cost price of the product
        float costPrice = 129;

        // selling price of the product
        float sellingPrice = 191;

        // finding the profit
        float profit = sellingPrice - costPrice;

        // calculating profit percentage
        float profitPercentage = (profit / costPrice) * 100;

        // printing all the values
        System.out.printf(
            "The Cost Price is INR %.2f and Selling Price is INR %.2f%n" +
            "The Profit is INR %.2f and the Profit Percentage is %.2f%%",
            costPrice, sellingPrice, profit, profitPercentage
        );
    }
}










	