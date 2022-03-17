import java.io.*;
import java.util.Scanner;  

public class Main {

	public static int[][] file_reading() {

		int matrix_size = 0;


		//Finding the matrix size by reading the data in the txt file.

		try {  

			FileInputStream fis = new FileInputStream("example.txt");       
			Scanner sc = new Scanner(fis);    

			while(sc.hasNextLine()) {  
				String temp = sc.nextLine();

				if (!sc.hasNextLine()) {
					String[] temp_str = temp.split(" ");
					matrix_size = temp_str.length;
				}	
			}  

			sc.close();     
		}  

		catch(IOException e) {e.printStackTrace();}  


		int[][] matrix = new int[matrix_size][matrix_size]; //matrix creation


		//Filling the matrix by reading the txt file.
		try {  

			FileInputStream fis = new FileInputStream("example.txt");       
			Scanner sc = new Scanner(fis);    

			int count = 0;
			while(sc.hasNextLine()) {  
				String temp = sc.nextLine();
				String[] temp_str = temp.split(" ");

				for (int i = 0; i < matrix_size; i++) {

					if (i > temp_str.length - 1) { 

						if (count != matrix_size)     //Filling empty indexes with 0 to create a square matrix.
							matrix[count][i] = 0;	
					}

					else {
						/* Since we will not use prime numbers when calculating the maximum sum, 
						 * 0 is placed in the matrix instead of the prime number inputs.*/
						if (is_prime(Integer.parseInt(temp_str[i]))) 
							matrix[count][i] = 0;

						else			
							matrix[count][i] = Integer.parseInt(temp_str[i]); //Placing the inputs in the matrix.
					}

				}
				count++;
			}  

			sc.close();     
		}  

		catch(IOException e) {e.printStackTrace();}
		return matrix;
	}



	public static boolean is_prime(int n) { //Checks if the number is a prime number.
		if (n <= 1)
			return false;

		for (int i = 2; i < n; i++)
			if (n % i == 0)
				return false;

		return true;
	}



	public static int max_sum (int [][] matrix) {

		int max_sum = 0;


		/* Starting from the lower right corner of the matrix, it compares two numbers side by side, 
		 * selects the larger number and adds it with the number in the upper index. 
		 * The processes continue like this, and in the final stage, the first element of the matrix becomes equal to the maximum sum.*/
		for (int i = matrix.length - 1; i > 0; i--) {
			for (int j = matrix.length - 1; j > 0; j--) {

				if (matrix[i][j] > matrix[i][j - 1]) 
					matrix[i - 1][j - 1] += matrix[i][j];


				else if (matrix[i][j] < matrix[i][j - 1])
					matrix[i - 1][j - 1] += matrix[i][j - 1];
			}
		}

		max_sum = matrix[0][0];

		return max_sum;
	}	



	public static void main(String[] args) {

		System.out.println("\n\tMaximum Sum of The Numbers From Top to Bottom = " + max_sum(file_reading()));

	}
}
