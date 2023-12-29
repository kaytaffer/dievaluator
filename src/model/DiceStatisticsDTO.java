package model;

/**
 * Creates an instance of a Statistics Data Transfer Object.
 * @param totalDiceRolled The sum of the amount of dice rolls.
 * @param uniformExpectedValue The expected value for a roll of a die of this type.
 * @param uniformDeviation The standard deviation for this type of dice.
 * @param arithmeticMean The calculated arithmetic mean based on the recorded samples.
 * @param sampleDeviation The estimated standard deviation based on the recorded sample.
 * @param confidence The confidence level that these dice are completely fair.
 * @param mostCommon The most common rolled result.
 * @param leastCommon The least common rolled result.
 */
public record DiceStatisticsDTO(Integer totalDiceRolled, double uniformExpectedValue, double uniformDeviation,
                                double arithmeticMean, double sampleDeviation, double confidence, int mostCommon,
                                int leastCommon) {

}
