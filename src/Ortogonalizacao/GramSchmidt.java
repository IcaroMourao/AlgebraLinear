package Ortogonalizacao;

import java.util.Arrays;

import Main.App;

public class GramSchmidt {

	public static double[][] gramSchmidt(double[][] vects) {
		final double EPS = 1E-9;

		int len = vects.length;
		boolean[] removed = new boolean[len];
		for (int i = 0; i < len; i++) {
			if (removed[i])
				continue;
			MatrixUtil.normalize(vects[i]);
			for (int j = i + 1; j < len; j++) {
				if (removed[j])
					continue;
				double dotp = MatrixUtil.dot(vects[i], vects[j]);
				for (int k = 0; k < vects[j].length; k++) 
					vects[j][k] -= dotp * vects[i][k];
				removed[j] = MatrixUtil.lengthSq(vects[j]) < EPS;
			}
		}
		int loc = 0;
		for (int i = 0; i < len; i++)
			if (!removed[i])
				vects[loc++] = vects[i];
		return Arrays.copyOf(vects, loc);
	}

	public static void main(String[] args) {
		double a[][] = { { 1, 1, 0 }, { 1, 0, 1 }, { 0, 2, 0 } };
		App.printMatrix(gramSchmidt(a));
	}
}
