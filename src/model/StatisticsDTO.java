package model;

/**
 * A Data Transfer Object containing the resulting mathematical output of a <code>Trial</code>.
 */
public class StatisticsDTO {

    private final Integer totalDiceRolled;
    private double uniformDeviation;
    private double uniformExpectedValue;
    private double arithmeticMean;
    private double sampleDeviation;
    private Double confidence;

    /**
     * Creates an instance of an empty <code>StatisticsDTO</code>
     * @param totalDiceRolled should be 0 for this DTO.
     */
    public StatisticsDTO(int totalDiceRolled) {
        this.totalDiceRolled = null;
    }

    public StatisticsDTO(int totalDiceRolled, double uniformExpectedValue, double uniformDeviation,
                         double arithmeticMean, double sampleDeviation, Double confidence) {
        this.totalDiceRolled = totalDiceRolled;
        this.uniformExpectedValue = uniformExpectedValue;
        this.uniformDeviation = uniformDeviation;
        this.arithmeticMean = arithmeticMean;
        this.sampleDeviation = sampleDeviation;
        this.confidence = confidence;
    }

    public Integer getTotalDiceRolled() {
        return totalDiceRolled;
    }

    public double getUniformExpectedValue() {
        return uniformExpectedValue;
    }

    public double getUniformDeviation() {
        return uniformDeviation;
    }

    public double getArithmeticMean() {
        return arithmeticMean;
    }
    public double getSampleDeviation() {
        return sampleDeviation;
    }

    public Double getConfidence() {
        return confidence;
    }
}
