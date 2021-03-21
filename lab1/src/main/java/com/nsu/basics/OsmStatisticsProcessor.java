package com.nsu.basics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class OsmStatisticsProcessor {
    private static final Logger LOG = LoggerFactory.getLogger(OsmStatisticsProcessor.class);

    void printOsmSortedStatistics(OsmStatistics statistics) {
        LOG.debug("Printing user statistics");
        statistics.getUserEditAmount().entrySet().stream()
            .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
            .forEach(userAmount ->
                System.out.println("User " + userAmount.getKey() + " editsAmount " + userAmount.getValue()));

        LOG.debug("Printing tags statistics");
        statistics.getTagKeyNodeAmount().entrySet().stream()
            .sorted(Map.Entry.comparingByValue())
            .forEach(tagKeyAmount ->
                System.out.println("Tag " + tagKeyAmount.getKey() + " amount " + tagKeyAmount.getValue()));
    }
}
