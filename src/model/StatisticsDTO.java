package model;

/**
 * A Data Transfer Object containing the resulting mathematical output of a <code>Trial</code>.
 */
public class StatisticsDTO {

    private final Integer totalDiceRolled;
    private int[] savedInput;

    /**
     * Creates an instance of an empty <code>StatisticsDTO</code>
     * @param totalDiceRolled should be 0 for this DTO.
     */
    public StatisticsDTO(int totalDiceRolled) {
        this.totalDiceRolled = null;
    }

    public StatisticsDTO(int totalDiceRolled, int[] savedInput) {
        this.totalDiceRolled = totalDiceRolled;
        this.savedInput = savedInput;
    }

    public Integer getTotalDiceRolled() {
        return totalDiceRolled;
    }

    public int[] getSavedInput() {
        return savedInput;
    }
}
