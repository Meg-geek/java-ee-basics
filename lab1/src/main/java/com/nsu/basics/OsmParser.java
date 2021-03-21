package com.nsu.basics;

import org.apache.commons.compress.compressors.CompressorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.BufferedReader;
import java.io.IOException;

public class OsmParser {
    private static final Logger LOG = LoggerFactory.getLogger(OsmParser.class);

    private static final String NODE_ELEMENT_NAME = "node";
    private static final String TAG_ELEMENT_NAME = "tag";

    private static final QName USER_ATTRIBUTE_NAME = QName.valueOf("user");
    private static final QName KEY_ATTRIBUTE_NAME = QName.valueOf("k");

    OsmStatistics getOsmStatisticsFromCompressedFile(String filePath) throws CompressorException {
        LOG.info("Started osm file parsing");
        OsmStatistics statistics = new OsmStatistics();

        XMLInputFactory xmlInputFactory = XMLInputFactory.newFactory();

        try (BufferedReader reader = ArchiveReader.getBufferedReaderForCompressedFile(filePath)) {
            XMLEventReader xmlEventReader = xmlInputFactory.createXMLEventReader(reader);
            while (xmlEventReader.hasNext()) {
                processEvent(xmlEventReader, statistics);
            }
        } catch (IOException | XMLStreamException ex) {
            LOG.error("Error while parsing", ex);
        }

        LOG.info("Osm file parsing ended");
        return statistics;
    }

    private void processEvent(XMLEventReader eventReader, OsmStatistics statistics) throws XMLStreamException {
        XMLEvent event = eventReader.nextEvent();
        if (!event.isStartElement()) {
            return;
        }

        StartElement startElement = event.asStartElement();

        processIfNodeElement(startElement, statistics);
        processIfTagElement(startElement, statistics);
    }

    private void processIfNodeElement(StartElement nodeElement, OsmStatistics statistics) {
        if (!NODE_ELEMENT_NAME.equals(nodeElement.getName().getLocalPart())) {
            return;
        }

        Attribute userAttr = nodeElement.getAttributeByName(USER_ATTRIBUTE_NAME);
        if (userAttr != null) {
            statistics.addUser(userAttr.getValue());
        }
    }

    private void processIfTagElement(StartElement tagElement, OsmStatistics statistics) {
        if (!TAG_ELEMENT_NAME.equals(tagElement.getName().getLocalPart())) {
            return;
        }

        Attribute keyAttr = tagElement.getAttributeByName(KEY_ATTRIBUTE_NAME);
        if (keyAttr != null) {
            statistics.addTagKey(keyAttr.getValue());
        }
    }
}
