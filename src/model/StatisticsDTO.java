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
     * // TODO  javadoc
     * @param totalDiceRolled
     * @param uniformExpectedValue
     * @param uniformDeviation
     * @param arithmeticMean
     * @param sampleDeviation
     * @param confidence
     */
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
