package AlgebraMatricial;

public class OpwithMatrix {

	public double[][] sumMatrix(double a[][], double b[][]) {
		if (a.length != b.length || a[0].length != b[0].length)
			throw new IllegalArgumentException("Matrizes não podem ser somadas, pois têm tamanhos diferentes!");
		double[][] sum = new double[a.length][a[0].length];
		if (a.length == b.length && a[0].length == b[0].length)
			for (int i = 0; i < a.length; i++)
				for (int j = 0; j < a[0].length; j++)
					sum[i][j] = a[i][j] + b[i][j];
		return sum;
	}

	public double[][] subMatrix(double a[][], double b[][]) {
		if (a.length != b.length || a[0].length != b[0].length)
			throw new IllegalArgumentException("Matrizes não podem ser subtraídas, pois têm tamanhos diferentes!");
		double[][] sub = new double[a.length][a[0].length];
		if (a.length == b.length && a[0].length == b[0].length)
			for (int i = 0; i < a.length; i++)
				for (int j = 0; j < a[0].length; j++)
					sub[i][j] = a[i][j] - b[i][j];
		return sub;
	}

	public double[][] multiMatrix(double a[][], double b[][]) {
		double[][] multi = new double[a.length][b[0].length];
		int m, n;
		if (a[0].length == b.length) {
			for (int i = 0; i < a.length; i++)
				for (int j = 0; j < b[0].length; j++)
					multi[i][j] = 0;
			for (int i = 0; i < a.length; i++)
				for (int j = 0; j < a[0].length; j++) {
					m = 0;
					n = 0;
					for (int k = 0; k < b.length; k++)
						multi[i][j] += a[i][m++] * b[n++][j];
				}
			return multi;
		} else {
			throw new IllegalArgumentException("Essas matrizes não podem ser multiplicadas");
		}
	}

	public double[][] multiMatrixEsc(double matrix[][], int esc) {
		for (int i = 0; i < matrix.length; i++)
			for (int j = 0; j < matrix[0].length; j++)
				matrix[i][j] = matrix[i][j] * esc;
		return matrix;
	}

	public double[][] transposed(double a[][]) {
		double[][] tranposed = new double[a[0].length][a.length];
		for (int i = 0; i < a.length; i++)
			for (int j = 0; j < a[0].length; j++)
				tranposed[j][i] = a[i][j];
		return tranposed;
	}

	public double[][] pow(double matrix[][], int num) {
		double result[][] = matrix;
		for (int i = 1; i < num; i++)
			result = multiMatrix(result, matrix);
		return result;
	}

	public double[][] invert(double matrix[][]) {
		int n = matrix.length;
		double x[][] = new double[n][n];
		double b[][] = new double[n][n];
		int index[] = new int[n];
		for (int i = 0; i < n; ++i)
			b[i][i] = 1;

		gauss(matrix, index);

		for (int i = 0; i < n - 1; ++i)
			for (int j = i + 1; j < n; ++j)
				for (int k = 0; k < n; ++k)
					b[index[j]][k] -= matrix[index[j]][i] * b[index[i]][k];

		for (int i = 0; i < n; ++i) {
			x[n - 1][i] = b[index[n - 1]][i] / matrix[index[n - 1]][n - 1];
			for (int j = n - 2; j >= 0; --j) {
				x[j][i] = b[index[j]][i];
				for (int k = j + 1; k < n; ++k) {
					x[j][i] -= matrix[index[j]][k] * x[k][i];
				}
				x[j][i] /= matrix[index[j]][j];
			}
		}
		return x;
	}

	private void gauss(double a[][], int index[]) {
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
