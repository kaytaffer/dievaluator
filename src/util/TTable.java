package util;

/**
 * Contains static methods to evaluate probabilities for quantiles for t-distributions for an amount
 * of different sample sizes.
 */
public class TTable {
    private final static int COLUMNS = 11;
    private final static double[][] TABLE = {
        {0 , 0.5 , 0.75 , 0.80 , 0.85 , 0.9 , 0.95 , 0.975 , 0.99 , 0.995 , 0.999 , 0.9995, 0.9999, 0.99995, 0.99999},
        {1 , 0.0 , 1.00 , 1.38 , 1.96 , 3.08 , 6.31 , 12.7 , 31.8 , 63.7 , 318 , 637 , 3183 , 6366 , 31831} ,
        {2 , 0.0 , 0.82 , 1.06 , 1.39 , 1.89 , 2.92 , 4.30 , 6.96 , 9.92 , 22.3 , 31.6 , 70.7 , 100 , 224} ,
        {3 , 0.0 , 0.77 , 0.98 , 1.25 , 1.64 , 2.35 , 3.18 , 4.54 , 5.84 , 10.2 , 12.9 , 22.2 , 28.0 , 47.9} ,
        {4 , 0.0 , 0.74 , 0.94 , 1.19 , 1.53 , 2.13 , 2.78 , 3.75 , 4.60 , 7.17 , 8.61 , 13.0 , 15.5 , 23.3} ,
        {5 , 0.0 , 0.73 , 0.92 , 1.16 , 1.48 , 2.02 , 2.57 , 3.36 , 4.03 , 5.89 , 6.87 , 9.68 , 11.2 , 15.6} ,
        {6 , 0.0 , 0.72 , 0.91 , 1.16 , 1.44 , 1.94 , 2.45 , 3.14 , 3.71 , 5.21 , 5.96 , 8.02 , 9.08 , 12.0} ,
        {7 , 0.0 , 0.71 , 0.90 , 1.12 , 1.41 , 1.89 , 2.36 , 3.00 , 3.50 , 4.79 , 5.41 , 7.06 , 7.88 , 10.1} ,
        {8 , 0.0 , 0.71 , 0.89 , 1.11 , 1.40 , 1.86 , 2.31 , 2.90 , 3.36 , 4.50 , 5.04 , 6.44 , 7.12 , 8.91} ,
        {9 , 0.0 , 0.70 , 0.88 , 1.10 , 1.38 , 1.83 , 2.26 , 2.82 , 3.25 , 4.30 , 4.78 , 6.01 , 6.59 , 8.10} ,
        {10, 0.0 , 0.70 , 0.88 , 1.09 , 1.37 , 1.81 , 2.23 , 2.76 , 3.17 , 4.14 , 4.59 , 5.69 , 6.21 , 7.53} ,
        {11, 0.0 , 0.70 , 0.88 , 1.09 , 1.36 , 1.80 , 2.20 , 2.72 , 3.11 , 4.02 , 4.44 , 5.45 , 5.92 , 7.10} ,
        {12, 0.0 , 0.70 , 0.87 , 1.08 , 1.36 , 1.78 , 2.18 , 2.68 , 3.05 , 3.93 , 4.32 , 5.26 , 5.69 , 6.77} ,
        {13, 0.0 , 0.69 , 0.87 , 1.08 , 1.35 , 1.77 , 2.16 , 2.65 , 3.01 , 3.85 , 4.22 , 5.11 , 5.51 , 6.50} ,
        {14, 0.0 , 0.69 , 0.87 , 1.08 , 1.35 , 1.76 , 2.14 , 2.62 , 2.98 , 3.79 , 4.14 , 4.99 , 5.36 , 6.29} ,
        {15, 0.0 , 0.69 , 0.87 , 1.07 , 1.34 , 1.75 , 2.13 , 2.60 , 2.95 , 3.73 , 4.07 , 4.88 , 5.24 , 6.11} ,
        {16, 0.0 , 0.69 , 0.87 , 1.07 , 1.34 , 1.75 , 2.12 , 2.58 , 2.92 , 3.69 , 4.01 , 4.79 , 5.13 , 5.96} ,
        {17, 0.0 , 0.69 , 0.86 , 1.07 , 1.33 , 1.74 , 2.11 , 2.57 , 2.90 , 3.65 , 3.97 , 4.71 , 5.04 , 5.83} ,
        {18, 0.0 , 0.69 , 0.86 , 1.07 , 1.33 , 1.73 , 2.10 , 2.55 , 2.88 , 3.61 , 3.92 , 4.65 , 4.97 , 5.72} ,
        {19, 0.0 , 0.69 , 0.86 , 1.07 , 1.33 , 1.73 , 2.09 , 2.54 , 2.86 , 3.58 , 3.88 , 4.59 , 4.90 , 5.63} ,
        {20, 0.0 , 0.69 , 0.86 , 1.06 , 1.33 , 1.72 , 2.09 , 2.53 , 2.85 , 3.55 , 3.85 , 4.54 , 4.84 , 5.54} ,
        {21, 0.0 , 0.69 , 0.86 , 1.06 , 1.32 , 1.72 , 2.08 , 2.52 , 2.83 , 3.53 , 3.82 , 4.49 , 4.78 , 5.47} ,
        {22, 0.0 , 0.69 , 0.86 , 1.06 , 1.32 , 1.72 , 2.07 , 2.51 , 2.82 , 3.50 , 3.79 , 4.45 , 4.74 , 5.40} ,
        {23, 0.0 , 0.69 , 0.86 , 1.06 , 1.32 , 1.71 , 2.07 , 2.50 , 2.81 , 3.48 , 3.77 , 4.42 , 4.69 , 5.34} ,
        {24, 0.0 , 0.69 , 0.86 , 1.06 , 1.32 , 1.71 , 2.06 , 2.49 , 2.80 , 3.47 , 3.75 , 4.38 , 4.65 , 5.29} ,
        {25, 0.0 , 0.68 , 0.86 , 1.06 , 1.32 , 1.71 , 2.06 , 2.49 , 2.79 , 3.45 , 3.73 , 4.35 , 4.62 , 5.24} ,
        {26, 0.0 , 0.68 , 0.86 , 1.06 , 1.31 , 1.71 , 2.06 , 2.48 , 2.78 , 3.43 , 3.71 , 4.32 , 4.59 , 5.20} ,
        {27, 0.0 , 0.68 , 0.86 , 1.06 , 1.31 , 1.70 , 2.05 , 2.47 , 2.77 , 3.42 , 3.69 , 4.30 , 4.56 , 5.16} ,
        {28, 0.0 , 0.68 , 0.86 , 1.06 , 1.31 , 1.70 , 2.05 , 2.47 , 2.76 , 3.41 , 3.67 , 4.28 , 4.53 , 5.12} ,
        {29, 0.0 , 0.68 , 0.85 , 1.06 , 1.31 , 1.70 , 2.05 , 2.46 , 2.76 , 3.40 , 3.66 , 4.25 , 4.51 , 5.09} ,
        {30, 0.0 , 0.68 , 0.85 , 1.06 , 1.31 , 1.70 , 2.04 , 2.46 , 2.75 , 3.39 , 3.65 , 4.23 , 4.48 , 5.05} ,
        {39, 0.0 , 0.68 , 0.85 , 1.05 , 1.30 , 1.68 , 2.02 , 2.43 , 2.71 , 3.31 , 3.56 , 4.10 , 4.33 , 4.85} ,
        {49, 0.0 , 0.68 , 0.85 , 1.05 , 1.30 , 1.68 , 2.01 , 2.40 , 2.68 , 3.27 , 3.50 , 4.02 , 4.24 , 4.72} ,
        {99, 0.0 , 0.68 , 0.84 , 1.04 , 1.29 , 1.66 , 1.98 , 2.36 , 2.63 , 3.17 , 3.39 , 3.86 , 4.06 , 4.48} ,
        {101, 0.0 , 0.68 , 0.84 , 1.04 , 1.28 , 1.64 , 1.96 , 2.33 , 2.58 , 3.09 , 3.29 , 3.72 , 3.89 , 4.26} ,
    };


    /**
     * Evaluates an approximate cumulative probability for a quantile given a certain degree of freedom.
     * @param degreesOfFreedomIndex The number of samples minus 1.
     * @param quantile The one-sided quantile in the t-distribution.
     * @return A confidence level ranging from 0.9 to 0.99999. If the confidence level is less than 90%,
     * will return null.
     */
    static Double evaluateQuantile(int degreesOfFreedomIndex, double quantile){
        if (degreesOfFreedomIndex > 100)
            degreesOfFreedomIndex = 34;
        else if (degreesOfFreedomIndex >= 99)
            degreesOfFreedomIndex = 33;
        else if (degreesOfFreedomIndex >=49)
            degreesOfFreedomIndex = 32;
        else if (degreesOfFreedomIndex >= 39)
            degreesOfFreedomIndex = 31;
        else if(degreesOfFreedomIndex > 30)
            degreesOfFreedomIndex = 30;

        Double probability = null;
        for(int i = 1; i < COLUMNS; i++) {
            if (quantile >= TABLE[degreesOfFreedomIndex][i])
                probability = TABLE[0][i];
        }
        return probability;
    }

}
