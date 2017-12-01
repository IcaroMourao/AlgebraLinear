package Main;

import AlgebraMatricial.OpwithMatrix;

public class App {

	public static void main(String[] args) {
		OpwithMatrix op = new OpwithMatrix();
		
		double a[][] = { 
				{ 1, 2, 3 },
				{ 1, 2, 3 },
				{ 1, 2, 3 }};
	       
	}

	private static void printMatrix(double[][] matrix) {
		for (int i = 0; i < matrix[0].length; i++)
			System.out.print("--");
		System.out.println("");

		for (int l = 0; l < matrix.length; l++) {
			for (int c = 0; c < matrix[0].length; c++) {
				System.out.print(matrix[l][c] + " ");
			}
			System.out.println("");
		}

		for (int i = 0; i < matrix[0].length; i++)
			System.out.print("--");
		System.out.println("");
	}
}
