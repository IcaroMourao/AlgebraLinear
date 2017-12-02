package Sistemas;

import Main.App;

public class FatoracaoLU {
	


	private double[][] LU; 
	private int m, n, pivsign;
	private int[] pivo;

	public FatoracaoLU(double[][] M) {

		LU = M;
		m = M.length;
		n = M[0].length;
		pivo = new int[m];
		for (int i = 0; i < m; i++) {
			pivo[i] = i;
		}
		pivsign = 1;
		double[] linhaLU;
		double[] colunaLU = new double[m];

		for (int j = 0; j < n; j++) {

			for (int i = 0; i < m; i++) {
				colunaLU[i] = LU[i][j];
			}

			for (int i = 0; i < m; i++) {
				linhaLU = LU[i];

				int kmax = Math.min(i, j);
				double s = 0.0;
				for (int k = 0; k < kmax; k++) {
					s += linhaLU[k] * colunaLU[k];
				}

				linhaLU[j] = colunaLU[i] -= s;
			}

			int p = j;
			for (int i = j + 1; i < m; i++) {
				if (Math.abs(colunaLU[i]) > Math.abs(colunaLU[p])) {
					p = i;
				}
			}
			if (p != j) {
				for (int k = 0; k < n; k++) {
					double t = LU[p][k];
					LU[p][k] = LU[j][k];
					LU[j][k] = t;
				}
				int k = pivo[p];
				pivo[p] = pivo[j];
				pivo[j] = k;
				pivsign = -pivsign;
			}

			if (j < m & LU[j][j] != 0.0) {
				for (int i = j + 1; i < m; i++) {
					LU[i][j] /= LU[j][j];
				}
			}
		}
	} 
	
	public double[][] getL() {
		double[][] L = new double[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (i > j) {
					L[i][j] = LU[i][j];
				} else if (i == j) {
					L[i][j] = 1.0;
				} else {
					L[i][j] = 0.0;
				}
			}
		}
		return L;
	}

	public double[][] getU() {
		double[][] U = new double[m][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i <= j) {
					U[i][j] = LU[i][j];
				} else {
					U[i][j] = 0.0;
				}
			}
		}
		return U;
	}
	
	public static void main(String[] args) {
		double a[][] = { 
				{ 1, 2, 3 }, 
				{ 4, 5, 6 }, 
				{ 7, 8, 10 }}; 

		FatoracaoLU lu = new FatoracaoLU(a);
		App.printMatrix(lu.getL());
		App.printMatrix(lu.getU());
		
	}
	
}
