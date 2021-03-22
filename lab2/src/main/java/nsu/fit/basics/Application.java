package nsu.fit.basics;

import nsu.fit.basics.generated.model.Osm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {
    private static final Logger LOG = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        try {
            Osm osm = new OsmReader().readOsmFile("RU-NVS.osm.bz2");
            System.out.println(osm);
        } catch (Exception ex) {
            LOG.error("error", ex);
        }
    }
}
