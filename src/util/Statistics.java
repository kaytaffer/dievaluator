package util;

import model.StatisticsDTO;

import java.util.stream.IntStream;

public class Statistics {

    /**
     * //TODO
     * @return A Data Transfer Object containing the resulting mathematical output of a <code>Trial</code>. If the state
     * has recorded nothing thus far the DTO contains only null values.
     */
    public static StatisticsDTO assembleStatistics(int[] savedInput) {
        int totalDiceRolled = IntStream.of(savedInput).sum();
        if (totalDiceRolled == 0)
            return new StatisticsDTO(totalDiceRolled);
        double uniformExpectedValue = calculateUniformExpectedValue(savedInput);
        double uniformVariance = calculateUniformVariance(savedInput);
        double arithmeticMean = calculateArithmeticMean(savedInput, totalDiceRolled);
        double sampleVariance = estimateVariance(savedInput, totalDiceRolled, arithmeticMean); //TODO

        return new StatisticsDTO(totalDiceRolled, uniformExpectedValue, arithmeticMean ); //TODO Actually build a useful DTO
    }

    //returns the expected value of a fair die (assuming uniform distribution)
    private static double calculateUniformExpectedValue(int[] sample) {
        double numOfSides = sample.length;
        return Math.round((1.0 + numOfSides) / 2.0);
    }

    //Returns the variance of a uniform distribution.
    private static double calculateUniformVariance(int[] sample) {
        return Math.pow((1-sample.length), 2) / 12.0;
    }

    // calculates the weighted arithmetic mean of the supplied sample.
    private static double calculateArithmeticMean(int[] sample, int numberOfElements) {
        int numberOfPossibleOutcomes = sample.length;
        double numOfElements = numberOfElements * 1.0;
        double sum = 0;
        for (int i = 0; i < numberOfPossibleOutcomes; i++){
            sum = sum + (sample[i] * (i+1));
        }
        return sum / numOfElements;
    }

    // Estimates the variance of the population using the supplied sample. If arithmetic mean already known
    // the second of the overloaded methods can be used for faster performance.
    private static double estimateVariance(int[] sample, int numberOfElements) {
        double arithmeticMean = calculateArithmeticMean(sample, numberOfElements);
        return estimateVariance(sample, numberOfElements, arithmeticMean);
    }
    private static double estimateVariance(int[] sample, int numberOfElements, double arithmeticMean) {
        double numOfElements = numberOfElements * 1.0;
        double variance = 0.0;
        for(int i = 0; i < sample.length; i++) {
            variance = variance + Math.pow(i - arithmeticMean, 2) * sample[i];
        }
        variance = variance / (numOfElements - 1);
        return variance;
    }

    //TODO t-table
    //TODO solve for confidence level:          CI_mu = X +/- qT_((1+c)/2)(of (n-1) t-table) * s/sqrt(n),
    //TODO normal dist table for n >= 100
    //TODO solve for confidence level:          CI_mu = X +/- qN_((1+c)/2) * s/sqrt(n)
    //TODO Hypothesis testing H_0 = mu = mean. Produce alpha (H0 rejected| H0 true) and beta(H0 rejected| H1 true).
}