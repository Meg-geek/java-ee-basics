package nsu.fit.basics;

import nsu.fit.basics.generated.model.Osm;
import org.apache.commons.compress.compressors.CompressorException;
import org.eclipse.persistence.oxm.XMLConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.util.StreamReaderDelegate;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class OsmReader {
    private static final Logger LOG = LoggerFactory.getLogger(OsmReader.class);

    Osm readOsmFile(String filePath) {
        try (BufferedReader reader = ArchiveReader.getBufferedReaderForCompressedFile(filePath)) {
            LOG.debug("Start reading osm file");
            XMLInputFactory xif = XMLInputFactory.newFactory();
            XMLStreamReader xsr = xif.createXMLStreamReader(reader);
            xsr = new OsmTypeReader(xsr);

            JAXBContext jc = JAXBContext.newInstance(Osm.class);
            Unmarshaller unmarshaller = jc.createUnmarshaller();

            Osm osm = (Osm) unmarshaller.unmarshal(xsr);
            LOG.debug("Osm successfully parsed");
            return osm;
        } catch (FileNotFoundException | CompressorException | XMLStreamException | JAXBException ex) {
            LOG.error("Error while reading osm file", ex);
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            LOG.error("Error closing osm file", ex);
            throw new RuntimeException(ex);
        }
    }

    private static class OsmTypeReader extends StreamReaderDelegate {

        public OsmTypeReader(XMLStreamReader reader) {
            super(reader);
        }

        @Override
        public String getAttributeNamespace(int arg0) {
            if ("type".equals(getAttributeLocalName(arg0))) {
                return XMLConstants.SCHEMA_INSTANCE_URL;
            }
            return super.getAttributeNamespace(arg0);
        }
    }
}
