/*

Suppose you have to divide 14 pens among 3 students equally. Write a program to find how many pens each student will get if the pens must be divided equally. Also, find the remaining non-distributed pens.
Hint => 
Use Modulus Operator (%) to find the reminder.
Use Division Operator to find the Quantity of pens
I/P => NONE
O/P => The Pen Per Student is ___ and the remaining pen not distributed is ___

*/



// Program to distribute pens among students
public class PenDistribution {

    // main method
    public static void main(String[] args) {

        // total number of pens
        int havePens = 14;

        // pens given to each student
        int pensDistributed = 14 / 3;

        // pens left after distribution
        int remainingPens = 14 % 3;

        // printing the result
        System.out.println(
            "The Pen Per Student is " + pensDistributed +
            " and the remaining pen not distributed is " + remainingPens
        );
    }
}




		


		