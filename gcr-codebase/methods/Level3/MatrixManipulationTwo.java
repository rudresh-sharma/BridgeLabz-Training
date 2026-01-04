/*

Write a program to perform matrix manipulation operations like finding the transpose, determinant, and inverse of a matrix. The program should take random matrices as input and display the result of the operations.
Hint => 
Write a Method to create a random matrix taking rows and columns as parameters
Write a Method to find the transpose of a matrix


*/


import java.util.Random;

public class MatrixManipulationTwo {

    public static void main(String[] args) {
        int n = 3;

        double[][] matrix = createRandomMatrix(n, n);

        System.out.println("Matrix:");
        printMatrix(matrix);

        double[][] transpose = getTranspose(matrix);
        System.out.println("\nTranspose:");
        printMatrix(transpose);

        double det = getDeterminant(matrix);
        System.out.println("\nDeterminant: " + det);

        if (det != 0) {
            double[][] inverse = getInverse(matrix);
            System.out.println("\nInverse:");
            printMatrix(inverse);
        } else {
            System.out.println("\nInverse does not exist");
        }
    }

    public static double[][] createRandomMatrix(int rows, int cols) {
        Random r = new Random();
        double[][] matrix = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = r.nextInt(9) + 1;
            }
        }
        return matrix;
    }

    public static double[][] getTranspose(double[][] matrix) {
        int r = matrix.length;
        int c = matrix[0].length;
        double[][] t = new double[c][r];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                t[j][i] = matrix[i][j];
            }
        }
        return t;
    }

    public static double getDeterminant(double[][] matrix) {
        int n = matrix.length;
        if (n == 1)
            return matrix[0][0];
        if (n == 2)
            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];

        double det = 0;
        for (int col = 0; col < n; col++) {
            det += Math.pow(-1, col) * matrix[0][col] * getDeterminant(getSubMatrix(matrix, 0, col));
        }
        return det;
    }

    public static double[][] getSubMatrix(double[][] matrix, int row, int col) {
        int n = matrix.length;
        double[][] sub = new double[n - 1][n - 1];
        int r = 0;
        for (int i = 0; i < n; i++) {
            if (i == row) continue;
            int c = 0;
            for (int j = 0; j < n; j++) {
                if (j == col) continue;
                sub[r][c++] = matrix[i][j];
            }
            r++;
        }
        return sub;
    }

    public static double[][] getInverse(double[][] matrix) {
        int n = matrix.length;
        double[][] aug = new double[n][2 * n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                aug[i][j] = matrix[i][j];
            }
            aug[i][i + n] = 1;
        }

        for (int i = 0; i < n; i++) {
            double pivot = aug[i][i];
            for (int j = 0; j < 2 * n; j++) {
                aug[i][j] /= pivot;
            }

            for (int k = 0; k < n; k++) {
                if (k != i) {
                    double factor = aug[k][i];
                    for (int j = 0; j < 2 * n; j++) {
                        aug[k][j] -= factor * aug[i][j];
                    }
                }
            }
        }

        double[][] inv = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                inv[i][j] = aug[i][j + n];
            }
        }
        return inv;
    }

    public static void printMatrix(double[][] matrix) {
        for (double[] row : matrix) {
            for (double val : row) {
                System.out.printf("%8.3f", val);
            }
            System.out.println();
        }
    }
}
