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
        int sum = IntStream.of(savedInput).sum();
        if (sum == 0)
            return new StatisticsDTO(sum);
        else return new StatisticsDTO(0); //TODO Actually build a useful DTO
    }
}