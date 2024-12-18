package util;

import model.DiceStatisticsDTO;

import java.util.stream.IntStream;

/**
 * Performs calculations to produce statistics based on assumed uniform distribution and on the supplied sample.
 */
public class Statistics {

    /**
     * Assembles a Data Transfer Object <code>DiceStatisticsDTO</code> containing statistics based on the supplied sample.
     * @param savedInput array of recorded dice rolls.
     * @return A Data Transfer Object containing the resulting mathematical output of a <code>Trial</code>.
     */
    public static DiceStatisticsDTO assembleStatistics(int[] savedInput) {
        int totalDiceRolled = IntStream.of(savedInput).sum();
        if (totalDiceRolled == 0)
            return null;
        double uniformExpectedValue = calculateUniformExpectedValue(savedInput);
        double uniformDeviation = Math.sqrt(calculateDiscreteUniformVariance(savedInput));
        double arithmeticMean = calculateArithmeticMean(savedInput, totalDiceRolled);
        double sampleVariance = estimateVariance(savedInput, totalDiceRolled, arithmeticMean);
        double sampleDeviation = Math.sqrt(sampleVariance);
        double offsetExpectedValue = Math.abs(arithmeticMean - uniformExpectedValue);
        double confidence = evaluateConfidence(totalDiceRolled, offsetExpectedValue, sampleDeviation);
        int mostCommon = evaluateMostCommon(savedInput) + 1;
        int leastCommon = evaluateLeastCommon(savedInput) + 1;

        return new DiceStatisticsDTO(totalDiceRolled, uniformExpectedValue, uniformDeviation, arithmeticMean,
                sampleDeviation, confidence, mostCommon, leastCommon);
    }

    //returns the expected value of a fair die (assuming uniform distribution)
    private static double calculateUniformExpectedValue(int[] sample) {
        double numOfSides = sample.length;
        return (1.0 + numOfSides) / 2.0;
    }

    //Returns the variance of a uniform distribution.
    private static double calculateDiscreteUniformVariance(int[] sample) {
        return (Math.pow(sample.length, 2) - 1) / 12.0;
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

    // Estimates the variance of the population using the supplied sample.
    private static double estimateVariance(int[] sample, int numberOfElements, double arithmeticMean) {
        double numOfElements = numberOfElements * 1.0;
        double variance = 0.0;
        for(int i = 0; i < sample.length; i++) {
            variance = variance + Math.pow((i+1) - arithmeticMean, 2) * sample[i];
        }
        variance = variance / (numOfElements - 1);
        return variance;
    }

    // Returns a confidence level that the arithmetic mean doesn't deviate from the expected value (offset).
    // CI_mu = x +/- q_((1+c)/2) * sigma/sqrt(n)   -> c = 2 * evaluated_quantile(|mu-x|*sqrt(n)/sigma) -1
    private static double evaluateConfidence(int totalDiceRolled, double offsetExpectedValue, double standardDeviation){
        double percentageConfidence;
        double quantileToEvaluate = offsetExpectedValue * Math.sqrt(totalDiceRolled)/standardDeviation;
        if(totalDiceRolled > 100)
            percentageConfidence = ZScores.evaluateQuantile(quantileToEvaluate);
        else
            percentageConfidence = TTable.evaluateQuantile(totalDiceRolled, quantileToEvaluate);
        return 2 - 2 * percentageConfidence;
    }

    //Finds the index that contains the highest value.
    private static int evaluateMostCommon(int[] savedInput) {
        int highest = Integer.MIN_VALUE;
        int index = 0;
        for(int i = 0; i < savedInput.length; i++) {
            if (highest < savedInput[i]) {
                highest = savedInput[i];
                index = i;
            }
        }
        return index;
    }

    //Finds the index that contains the lowest value.
    private static int evaluateLeastCommon(int[] savedInput) {
        int lowest = Integer.MAX_VALUE;
        int index = 0;
        for(int i = 0; i < savedInput.length; i++) {
            if (lowest > savedInput[i]) {
                lowest = savedInput[i];
                index = i;
            }
        }
        return index;
    }
    //TODO Hypothesis testing H_0 = mu = mean. Produce alpha (H0 rejected| H0 true) and beta(H0 rejected| H1 true).
}