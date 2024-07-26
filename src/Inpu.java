import java.util.Scanner;

public class Inpu {
    public static void main(String args[]) {
        Scanner input = new Scanner(System.in);
        int row = input.nextInt();
        System.out.println("Enter the roe number? ");
        int colm = input.nextInt();
        System.out.println("Enter the colmn number ?");

        int[][] array = new int[row][colm];

        System.out.println("Enter the Elemenets in array:");
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < row; j++) {
                array[i][j] = input.nextInt();
            }
        }
        System.out.println("Enter the Elemenets of the array:");
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < row; j++) {
                System.out.println(array[i][j] + " ");
            }
            System.out.println();
        }

    }
}
