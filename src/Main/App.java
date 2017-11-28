package Main;

import Sistemas.GaussianElimination;

public class App {

	public static void main(String[] args) {
		double a[][] = { { 1, 2, 3 }, { 1, 4, 6 }, { 1, 1, 1 } };
		double b[][] = { { 1, 2, 3 }, { 1, 4, 6 }, { 1, 1, 1 } };
		double c[][] = { { 1, 2} , {1,1}};
		double[][] A = {
	            { 0, 1,  1 },
	            { 2, 4, -2 },
	            { 0, 3, 15 }
	        };
	        int[] B = { 4, 2, 36 };
		int vet[] = new int[3];
		printMatrix(a);
		printMatrix(b);
		System.out.println("-- Soma --");
		printMatrix(sumMatrix(a, b));
		System.out.println("-- Subtracao --");
		printMatrix(subMatrix(a, b));
		System.out.println("-- Multiplicacao --");
		printMatrix(multiMatrix(a, b));
		System.out.println("-- Escalar --");
		printMatrix(multiMatrixEsc(a, 3));
		System.out.println("-- Transposta --");
		printMatrix(transposed(b));
		System.out.println("-- Potencia --");
		printMatrix(pow(a, 1));
		System.out.println("-- Inversa --");
		printMatrix(invert(c));
		System.out.println("-- Gauss --");
		gauss(A,B);
		printMatrix(A);
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

	public static double[][] sumMatrix(double a[][], double b[][]) {
		double[][] sum = new double[a.length][a[0].length];
		if (a.length == b.length && a[0].length == b[0].length)
			for (int i = 0; i < a.length; i++)
				for (int j = 0; j < a.length; j++)
					sum[i][j] = a[i][j] + b[i][j];
		return sum;
	}

	public static double[][] subMatrix(double a[][], double b[][]) {
		double[][] sum = new double[a.length][a[0].length];
		if (a.length == b.length && a[0].length == b[0].length)
			for (int i = 0; i < a.length; i++)
				for (int j = 0; j < a.length; j++)
					sum[i][j] = a[i][j] - b[i][j];
		return sum;
	}

	public static double[][] multiMatrix(double a[][], double b[][]) {
		double[][] multi = new double[a.length][b[0].length];
		int m, n;
		if (a[0].length == b.length) {
			for (int i = 0; i < a.length; i++)
				for (int j = 0; j < b[0].length; j++)
					multi[i][j] = 0;
			for (int i = 0; i < a.length; i++)
				for (int j = 0; j < a.length; j++) {
					m = 0;
					n = 0;
					for (int k = 0; k < b.length; k++)
						multi[i][j] += a[i][m++] * b[n++][j];
				}
			return multi;
		} else {
			System.err.println("Matrizes não podem ser multiplicadas!\n");
			return null;
		}
	}

	public static double[][] multiMatrixEsc(double a[][], int esc) {
		double[][] multi = a;
		for (int i = 0; i < a.length; i++)
			for (int j = 0; j < a.length; j++)
				multi[i][j] = multi[i][j] * esc;
		return multi;
	}

	public static double[][] transposed(double a[][]) {
		double[][] tranposed = new double[a[0].length][a.length];
		for (int i = 0; i < a.length; i++)
			for (int j = 0; j < a[0].length; j++)
				tranposed[j][i] = a[i][j];
		return tranposed;
	}

	public static double[][] pow(double a[][], int num) {
		double result[][] = a;
		for (int i = 1; i < num; i++)
			result = multiMatrix(result, a);
		return result;
	}

	public static double[][] invert(double a[][]) {
		int n = a.length;
		double x[][] = new double[n][n];
		double b[][] = new double[n][n];
		int index[] = new int[n];
		for (int i = 0; i < n; ++i)
			b[i][i] = 1;

		gauss(a, index);

		for (int i = 0; i < n - 1; ++i)
			for (int j = i + 1; j < n; ++j)
				for (int k = 0; k < n; ++k)
					b[index[j]][k] -= a[index[j]][i] * b[index[i]][k];

		for (int i = 0; i < n; ++i) {
			x[n - 1][i] = b[index[n - 1]][i] / a[index[n - 1]][n - 1];
			for (int j = n - 2; j >= 0; --j) {
				x[j][i] = b[index[j]][i];
				for (int k = j + 1; k < n; ++k) {
					x[j][i] -= a[index[j]][k] * x[k][i];
				}
				x[j][i] /= a[index[j]][j];
			}
		}
		return x;
	}

	public static void gauss(double a[][], int index[]) {
		int n = index.length;
		double c[] = new double[n];

		for (int i = 0; i < n; ++i)
			index[i] = i;

		for (int i = 0; i < n; ++i) {
			double c1 = 0;
			for (int j = 0; j < n; ++j) {
				double c0 = Math.abs(a[i][j]);
				if (c0 > c1)
					c1 = c0;
			}
			c[i] = c1;
		}

		int k = 0;
		for (int j = 0; j < n - 1; ++j) {
			double pi1 = 0;
			for (int i = j; i < n; ++i) {
				double pi0 = Math.abs(a[index[i]][j]);
				pi0 /= c[index[i]];
				if (pi0 > pi1) {
					pi1 = pi0;
					k = i;
				}
			}

			int itmp = index[j];
			index[j] = index[k];
			index[k] = itmp;
			for (int i = j + 1; i < n; ++i) {
				double pj = a[index[i]][j] / a[index[j]][j];

				a[index[i]][j] = pj;

				for (int l = j + 1; l < n; ++l)
					a[index[i]][l] -= pj * a[index[j]][l];
			}
		}
	}
}
