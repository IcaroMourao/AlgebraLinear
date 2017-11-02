package Main;

public class App {

	public static void main(String[] args) {
		Integer a[][] = { { 1, 2, 3 }, { 1, 4, 6 }, { 1, 1, 1 } };
		Integer b[][] = { { 1, 2, 3 }, { 1, 4, 6 }, { 1, 1, 1 } };
		Integer c[][] = { { 1, 2, 3 }};
		
		printMatrix(a);
		printMatrix(b);
		printMatrix(sumMatrix(a, b));
		printMatrix(multiMatrix(a, b));
		printMatrix(multiMatrixEsc(a, 3));
		printMatrix(transposed(b));
	}

	private static void printMatrix(Integer[][] matrix) {
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

	public static Integer[][] sumMatrix(Integer a[][], Integer b[][]) {
		Integer[][] sum = new Integer[a.length][a[0].length];
		if (a.length == b.length && a[0].length == b[0].length)
			for (int i = 0; i < a.length; i++)
				for (int j = 0; j < a.length; j++)
					sum[i][j] = a[i][j] + b[i][j];
		return sum;
	}
	
	public static Integer[][] subMatrix(Integer a[][], Integer b[][]) {
		Integer[][] sum = new Integer[a.length][a[0].length];
		if (a.length == b.length && a[0].length == b[0].length)
			for (int i = 0; i < a.length; i++)
				for (int j = 0; j < a.length; j++)
					sum[i][j] = a[i][j] + b[i][j];
		return sum;
	}
	
	public static Integer[][] multiMatrix(Integer a[][], Integer b[][]) {
		Integer[][] multi = new Integer[a.length][b[0].length];
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
	
	public static Integer[][] multiMatrixEsc(Integer a[][], int esc) {
		Integer[][] multi = a;
		for (int i = 0; i < a.length; i++)
			for (int j = 0; j < a.length; j++)
				multi[i][j] = multi[i][j] * esc;
		return multi;
	}

	public static Integer[][] transposed(Integer a[][]) {
		Integer[][] tranposed = new Integer[a[0].length][a.length];
		for (int i = 0; i < a.length; i++)
			for (int j = 0; j < a[0].length; j++)
					tranposed[j][i] = a[i][j];
		return tranposed;
	}

}
