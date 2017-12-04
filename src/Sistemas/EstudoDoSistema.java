package Sistemas;

public class EstudoDoSistema {

	public int postoMatriz(double[][] matriz) {
		int n = matriz.length, m = matriz[0].length;
		int posto = 0;
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++)
				if (matriz[i][j] != 0) {
					posto++;
					break;
				}
		return posto;
	}

	public boolean eHomogeneo(double[] termosIndependentes) {

		for (int i = 0; i < termosIndependentes.length; i++) {
			if (termosIndependentes[i] != 0)
				return false;
		}
		return true;
	}

	public String verificarSolucaoSistema(double[][] matriz, double[][] matrizAmpliada, double[] termosIndp,
			int num_incognitas) {
		int posto = postoMatriz(matriz);
		int postoAmp = postoMatriz(matrizAmpliada);

		if (eHomogeneo(termosIndp)) {
			if (posto == num_incognitas)
				return "Sistema Homogênio" + "\n" + "SPD - Sistema Possível e Determinado" + "\n"
						+ "Posto(A) = Nº de Incógnitas";
			else if (posto < num_incognitas)
				return "Sistema Homogênio" + "\n" + "Sistema Possível e Indeterminado" + "\n"
						+ "Posto(A) < Nº de Incógnitas";
			else
				return "ERRO!";
		} else {
			if (posto == postoAmp && postoAmp == num_incognitas)
				return "SPD - Sistema Possível e Determinado" + "\n" + "Posto(A) = Posto(A*) = Nº de Incógnitas";
			else if (posto == postoAmp && postoAmp < num_incognitas)
				return "SPI - Sistema Possível e Indeterminado" + "\n" + "Posto(A) = Posto(A*) < Nº de Incógnitas";
			else if (posto != postoAmp)
				return "SI - Sistema Impossível" + "\n" + "Posto(A) != Posto(A*)";
			else
				return "ERRO!";
		}
	}
}
