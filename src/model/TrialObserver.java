package model;

/**
 * Observer interface that informs classes that implements it that the {@code BlipLocationModel} has
 * updated its map of blip objects.
 */
public interface TrialObserver {
	
	/**
     * Called by the observed {@code Trial} when it has updated its dice statistics.
     * @param A Data Transfer Object containing statistics of the dice under trial.
     */
	void notifyObserversStatisticsAreUpdated(DiceStatisticsDTO statistics);
	
}
