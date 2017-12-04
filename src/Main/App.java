package Main;

import AlgebraMatricial.OpwithMatrix;

public class App {

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		OpwithMatrix op = new OpwithMatrix();
		
		@SuppressWarnings("unused")
		double a[][] = { 
				{ 1, 2, 3 },
				{ 1, 2, 3 },
				{ 1, 2, 3 }};
	}

	public static void printMatrix(double[][] matrix) {
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
	
	public static void printVector(double[] v) {
		for(int i =0; i< v.length; i++)
			System.out.println(v[i]+", ");
	}
	
}