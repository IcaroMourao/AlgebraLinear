package Sistemas;

import java.util.Random;

public class GaussianElimination {
    private static final double EPSILON = 1e-8;

    private final int m;
    private final int n;
    private double[][] a;

    public GaussianElimination(double[][] A, double[] b) {
        m = A.length;
        n = A[0].length;

        if (b.length != m) throw new IllegalArgumentException("Dimensions disagree");

        // build augmented matrix
        a = new double[m][n+1];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                a[i][j] = A[i][j];
        for (int i = 0; i < m; i++)
            a[i][n] = b[i];

        forwardElimination();

        assert certifySolution(A, b);
    }

    // forward elimination
    private void forwardElimination() {
        for (int p = 0; p < Math.min(m, n); p++) {

            // find pivot row using partial pivoting
            int max = p;
            for (int i = p+1; i < m; i++) {
                if (Math.abs(a[i][p]) > Math.abs(a[max][p])) {
                    max = i;
                }
            }

            // swap
            swap(p, max);

            // singular or nearly singular
            if (Math.abs(a[p][p]) <= EPSILON) {
                continue;
            }

            // pivot
            pivot(p);
        }
    }

    // swap row1 and row2
    private void swap(int row1, int row2) {
        double[] temp = a[row1];
        a[row1] = a[row2];
        a[row2] = temp;
    }

    // pivot on a[p][p]
    private void pivot(int p) {
        for (int i = p+1; i < m; i++) {
            double alpha = a[i][p] / a[p][p];
            for (int j = p; j <= n; j++) {
                a[i][j] -= alpha * a[p][j];
            }
        }
    }

    /**
     * Returns a solution to the linear system of equations <em>Ax</em> = <em>b</em>.
     *      
     * @return a solution <em>x</em> to the linear system of equations
     *         <em>Ax</em> = <em>b</em>; {@code null} if no such solution
     */
    public double[] primal() {
        // back substitution
        double[] x = new double[n];
        for (int i = Math.min(n-1, m-1); i >= 0; i--) {
            double sum = 0.0;
            for (int j = i+1; j < n; j++) {
                sum += a[i][j] * x[j];
            }

            if (Math.abs(a[i][i]) > EPSILON)
                x[i] = (a[i][n] - sum) / a[i][i];
            else if (Math.abs(a[i][n] - sum) > EPSILON)
                return null;
        }

        // redundant rows
        for (int i = n; i < m; i++) {
            double sum = 0.0;
            for (int j = 0; j < n; j++) {
                sum += a[i][j] * x[j];
            }
            if (Math.abs(a[i][n] - sum) > EPSILON)
                return null;
        }
        return x;
    }

    /**
     * Returns true if there exists a solution to the linear system of
     * equations <em>Ax</em> = <em>b</em>.
     *      
     * @return {@code true} if there exists a solution to the linear system
     *         of equations <em>Ax</em> = <em>b</em>; {@code false} otherwise
     */
    public boolean isFeasible() {
        return primal() != null;
    }


    // check that Ax = b
    private boolean certifySolution(double[][] A, double[] b) {
        if (!isFeasible()) return true;
        double[] x = primal();
        for (int i = 0; i < m; i++) {
            double sum = 0.0;
            for (int j = 0; j < n; j++) {
                sum += A[i][j] * x[j];
            }
            if (Math.abs(sum - b[i]) > EPSILON) {
                System.out.println("not feasible");
                System.out.println("b[" + i + "] = " + b[i] + ", sum = " + sum);
                return false;
            }
        }
        return true;
    }
    private static void test(String name, double[][] A, double[] b) {
        System.out.println("----------------------------------------------------");
        System.out.println(name);
        System.out.println("----------------------------------------------------");
        GaussianElimination gaussian = new GaussianElimination(A, b);
        double[] x = gaussian.primal();
        if (gaussian.isFeasible()) {
            for (int i = 0; i < x.length; i++) {
            	System.out.printf("%.6f\n", x[i]);
            }
        }
        else {
        	System.out.println("System is infeasible");
        }
        System.out.println();
        System.out.println();
    }


    // 3-by-3 nonsingular system
    private static void test1() {
        double[][] A = {
            { 0, 1,  1 },
            { 2, 4, -2 },
            { 0, 3, 15 }
        };
        double[] b = { 4, 2, 36 };
        test("test 1 (3-by-3 system, nonsingular)", A, b);
    }
    
    private static void test2() {
        double[][] A = {
            {  1, -3,   1 },
            {  2, -8,   8 },
            { -6,  3, -15 }
        };
        double[] b = { 4, -2, 9 };
        test("test 2 (3-by-3 system, nonsingular)", A, b);
    }

    // 5-by-5 singular: no solutions
    private static void test3() {
        double[][] A = {
            {  2, -3, -1,  2,  3 },
            {  4, -4, -1,  4, 11 },
            {  2, -5, -2,  2, -1 },
            {  0,  2,  1,  0,  4 },
            { -4,  6,  0,  0,  7 },
        };
        double[] b = { 4, 4, 9, -6, 5 };
        test("test 3 (5-by-5 system, no solutions)", A, b);
    }

    public static void main(String[] args) {
        test1();
        test2();
        test3();
        //test4();
        //test5();
        //test6();
        //test7();
        //test8();
        //test9();
        
        Random rd = new Random();
        // n-by-n random system
        int n = Integer.parseInt(args[0]);
        double[][] A = new double[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                A[i][j] = rd.nextInt(1000);
        double[] b = new double[n];
        for (int i = 0; i < n; i++)
            b[i] = rd.nextInt(1000);

        test(n + "-by-" + n + " (probably nonsingular)", A, b);
    }
    
}