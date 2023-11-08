package model;

/**
 * A Data Transfer Object containing the resulting mathematical output of a <code>Trial</code>.
 */
public class StatisticsDTO {

    private final Integer totalDiceRolled;
    private double uniformExpectedValue;
    private double arithmeticMean;

    /**
     * Creates an instance of an empty <code>StatisticsDTO</code>
     * @param totalDiceRolled should be 0 for this DTO.
     */
    public StatisticsDTO(int totalDiceRolled) {
        this.totalDiceRolled = null;
    }

    public StatisticsDTO(int totalDiceRolled, double uniformExpectedValue, double arithmeticMean) {
        this.totalDiceRolled = totalDiceRolled;
        this.uniformExpectedValue = uniformExpectedValue;
        this.arithmeticMean = arithmeticMean;
    }

    public Integer getTotalDiceRolled() {
        return totalDiceRolled;
    }

    public double getUniformExpectedValue() {
        return uniformExpectedValue;
    }

    public double getArithmeticMean() {
        return arithmeticMean;
    }
}
