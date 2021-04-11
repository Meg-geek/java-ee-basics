package nsu.fit.basics.service;

import lombok.RequiredArgsConstructor;
import nsu.fit.basics.dao.NodeDao;
import nsu.fit.basics.model.NodeModel;
import nsu.fit.basics.model.TagModel;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class NodeService {
    private static final Logger LOG = LoggerFactory.getLogger(NodeService.class);
    private final TagService tagService;
    private final NodeDao nodeDao;

    public void addNodeWithSimpleInsert(NodeModel node) throws SQLException {
        nodeDao.addNodeWithSimpleInsert(node);
        LOG.info("Added node to database with simple insert");

        tagService.addTagsWithSimpleInsert(node.getTags());
        LOG.info("Added tags to database with simple insert");
    }

    public void addNodeWithPreparedStatement(NodeModel node) throws SQLException {
        nodeDao.addNodeWithPreparedStatement(node);
        LOG.info("Added node to database with prepared statement");

        tagService.addTagsWithPreparedStatement(node.getTags());
        LOG.info("Added tags to database with prepared statement");
    }

    public void addNodeUsingBatch(NodeModel node) throws SQLException {
        nodeDao.addNodeUsingBatch(node);
        LOG.info("Added node using batch");

        tagService.addTagsUsingBatch(node.getTags());
        LOG.info("Added tags using batch");
    }

    public void addNodesUsingBatch(List<NodeModel> nodes) throws SQLException {
        nodeDao.addNodesUsingBatch(nodes);
        LOG.info("Added nodes using batch");

        List<TagModel> tags = nodes.stream()
            .filter(node -> CollectionUtils.isNotEmpty(node.getTags()))
            .flatMap(node -> node.getTags().stream())
            .collect(Collectors.toList());

        tagService.addTagsUsingBatch(tags);
        LOG.info("Added tags using batch");
    }
}
