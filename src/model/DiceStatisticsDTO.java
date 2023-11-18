package model;

/**
 * Creates an instance of a Statistics Data Transfer Object.
 * @param totalDiceRolled The sum of the amount of dice rolls
 * @param uniformExpectedValue The expected value for a roll of a die of this type
 * @param uniformDeviation the standard deviation for this type of dice
 * @param arithmeticMean the calculated arithmetic mean based on the recorded samples
 * @param sampleDeviation the estimated standard deviation based on the recorded sample
 * @param confidence the confidence level that these dice are completely fair
 */
public record DiceStatisticsDTO(Integer totalDiceRolled, double uniformDeviation, double uniformExpectedValue,
                                double arithmeticMean, double sampleDeviation, Double confidence) {

}
