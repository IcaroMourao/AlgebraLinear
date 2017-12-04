package Ortogonalizacao;

import Jama.Matrix;

public class MatrixUtil {

	public static void normalize(double[] vect) {
		double length = Math.sqrt(lengthSq(vect));
		for (int i = 0; i < vect.length; i++)
			vect[i] /= length;
	}

	public static double lengthSq(double[] vect) {
		double lenSq = 0;
		for (double val : vect)
			lenSq += val * val;
		return lenSq;
	}

	public static double[] proj(double[] v1, double[] v2) {
		double mult = dot(v1, v2) / dot(v2, v2);
		double[] ret = v2.clone();
		for (int i = 0; i < ret.length; i++)
			ret[i] *= mult;
		return ret;
	}

	public static double dot(double[] v1, double[] v2) {
		double ret = 0;
		for (int i = 0; i < v1.length; i++)
			ret += v1[i] * v2[i];
		return ret;
	}
}
