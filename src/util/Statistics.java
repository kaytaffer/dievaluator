package util;

import model.DiceStatisticsDTO;

import java.util.stream.IntStream;

/**
 * TODO
 */
public class Statistics {

    /**
     * //TODO
     * @return A Data Transfer Object containing the resulting mathematical output of a <code>Trial</code>. If the state
     * has recorded nothing thus far this method returns null.
     */
    public static DiceStatisticsDTO assembleStatistics(int[] savedInput) {
        int totalDiceRolled = IntStream.of(savedInput).sum();
        if (totalDiceRolled == 0)
            return null;
        double uniformExpectedValue = calculateUniformExpectedValue(savedInput);
        double uniformDeviation = Math.sqrt(calculateUniformVariance(savedInput));
        double arithmeticMean = calculateArithmeticMean(savedInput, totalDiceRolled);
        double sampleVariance = estimateVariance(savedInput, totalDiceRolled, arithmeticMean);
        double sampleDeviation = Math.sqrt(sampleVariance);
        double offsetExpectedValue = Math.abs(arithmeticMean - uniformExpectedValue);
        Double confidence = evaluateConfidence(totalDiceRolled, offsetExpectedValue, sampleDeviation);

        return new DiceStatisticsDTO(totalDiceRolled, uniformExpectedValue, uniformDeviation, arithmeticMean, sampleDeviation, confidence);
    }

    //returns the expected value of a fair die (assuming uniform distribution)
    private static double calculateUniformExpectedValue(int[] sample) {
        double numOfSides = sample.length;
        return (1.0 + numOfSides) / 2.0;
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

    // Returns a confidence level that the arithmetic mean doesn't deviate from the expected value (offset).
    // CI_mu = x +/- q_((1+c)/2) * sigma/sqrt(n)   -> c = 2 * evaluated_quantile(|mu-x|*sqrt(n)/sigma) -1
    private static Double evaluateConfidence(int totalDiceRolled, double offsetExpectedValue, double standardDeviation){
        Double percentageConfidence;
        double quantileToEvaluate = offsetExpectedValue * Math.sqrt(totalDiceRolled)/standardDeviation;
        if(totalDiceRolled > 100)
            percentageConfidence = ZScores.evaluateQuantile(quantileToEvaluate);
        else
            percentageConfidence = TTable.evaluateQuantile(totalDiceRolled, quantileToEvaluate);
        if (percentageConfidence != null)
            percentageConfidence = percentageConfidence * 2 - 1;
        return percentageConfidence;
    }

    //TODO Hypothesis testing H_0 = mu = mean. Produce alpha (H0 rejected| H0 true) and beta(H0 rejected| H1 true).
}