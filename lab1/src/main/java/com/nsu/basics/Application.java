package com.nsu.basics;

import org.apache.commons.compress.compressors.CompressorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {
    private static final Logger LOG = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        LOG.info("Application started");
        OsmParser osmParser = new OsmParser();
        OsmStatisticsProcessor statisticsProcessor = new OsmStatisticsProcessor();

        try {
            OsmStatistics statistics = osmParser.getOsmStatisticsFromCompressedFile("RU-NVS.osm.bz2");
            LOG.info("Got statistics");
            statisticsProcessor.printOsmSortedStatistics(statistics);
        } catch (CompressorException ex) {
            LOG.error("Exception while processing file ", ex);
        }
    }
}
