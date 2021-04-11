package nsu.fit.basics;

import nsu.fit.basics.dao.NodeDao;
import nsu.fit.basics.dao.TagDao;
import nsu.fit.basics.database.DatabaseUtils;
import nsu.fit.basics.generated.model.Osm;
import nsu.fit.basics.mapper.NodeMapper;
import nsu.fit.basics.service.NodeService;
import nsu.fit.basics.service.TagService;
import org.openjdk.jmh.runner.RunnerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.SQLException;

public class Application {
    private static final Logger LOG = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws SQLException, IOException, RunnerException {
        org.openjdk.jmh.Main.main(args);
        try {
            Osm osm = new OsmReader().readOsmFile("RU-NVS.osm.bz2");
            DatabaseUtils.initDatabase();
            NodeService nodeService = new NodeService(new TagService(new TagDao()), new NodeDao());
            nodeService.addNodesUsingBatch(NodeMapper.map(osm.getNode()));

        } catch (Exception ex) {
            LOG.error("error", ex);
        } finally {
            DatabaseUtils.closeConnection();
        }
    }

}
