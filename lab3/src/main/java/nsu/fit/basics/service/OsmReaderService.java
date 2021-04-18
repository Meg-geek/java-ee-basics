package nsu.fit.basics.service;

import lombok.RequiredArgsConstructor;
import nsu.fit.basics.generated.model.Osm;
import nsu.fit.basics.mapper.NodeMapper;
import nsu.fit.basics.mapper.RelationMapper;
import nsu.fit.basics.mapper.WayMapper;
import org.apache.commons.compress.compressors.CompressorException;
import org.eclipse.persistence.oxm.XMLConstants;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.util.StreamReaderDelegate;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
@RequiredArgsConstructor
public class OsmReaderService {
    private final NodeService nodeService;
    private final WayService wayService;
    private final RelationService relationService;

    public void readFileAndSaveEntities(String filePath) {
        Osm osm = readOsmFile(filePath);

        nodeService.saveAll(NodeMapper.map(osm.getNode()));
        wayService.saveAll(WayMapper.map(osm.getWay()));
        relationService.saveAll(RelationMapper.map(osm.getRelation()));
    }

    private Osm readOsmFile(String filePath) {
        try (BufferedReader reader = ArchiveReader.getBufferedReaderForCompressedFile(filePath)) {
            XMLInputFactory xif = XMLInputFactory.newFactory();
            //todo delete
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("test.osm"))));
            XMLStreamReader xsr = xif.createXMLStreamReader(bufferedReader);
            xsr = new OsmTypeReader(xsr);

            JAXBContext jc = JAXBContext.newInstance(Osm.class);
            Unmarshaller unmarshaller = jc.createUnmarshaller();

            return (Osm) unmarshaller.unmarshal(xsr);
        } catch (FileNotFoundException | CompressorException | XMLStreamException | JAXBException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
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
