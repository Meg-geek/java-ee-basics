package nsu.fit.basics.service;

import nsu.fit.basics.model.NodeModel;
import nsu.fit.basics.model.TagsModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class NodeServiceTest {
    @Autowired
    private NodeService nodeService;

    @Test
    void testAddNode() {
        NodeModel nodeModel = saveNode(createSimpleNodeModel());
        deleteNode(nodeModel);
    }

    @Test
    void testUpdateNode() {
        NodeModel nodeModel = saveNode(createSimpleNodeModel());
        nodeModel.getTags().addTag("k3", "v3");

        NodeModel updatedNode = saveNode(nodeModel);
        assertThat(updatedNode)
            .usingRecursiveComparison()
            .isEqualTo(nodeModel);

        deleteNode(updatedNode);
    }

    @Test
    void testGetNodeById() {
        NodeModel savedNode = saveNode(createSimpleNodeModel());

        NodeModel nodeById = nodeService.getById(savedNode.getId());
        assertThat(nodeById)
            .usingRecursiveComparison()
            .isEqualTo(savedNode);

        deleteNode(savedNode);
        assertThrows(IllegalStateException.class,
            () -> nodeService.getById(savedNode.getId()));
    }

    @Test
    void testDeleteNode() {
        deleteNode(saveNode(createSimpleNodeModel()));
    }

    private void deleteNode(NodeModel nodeToDelete) {
        BigInteger nodeId = nodeToDelete.getId();
        nodeService.deleteById(nodeId);

        assertThrows(IllegalStateException.class, () -> nodeService.getById(nodeId));
    }

    private NodeModel createSimpleNodeModel() {
        TagsModel tagsModel = new TagsModel()
            .addTag("key1", "value1")
            .addTag("key2", "value2");
        return NodeModel.builder()
            .latitude(2.3)
            .longitude(3.4)
            .user("user")
            .tags(tagsModel)
            .build();
    }

    private NodeModel saveNode(NodeModel nodeModel) {
        NodeModel savedModel = nodeService.addEntity(nodeModel);

        assertNotNull(savedModel);
        assertNotNull(savedModel.getId());

        assertThat(savedModel)
            .usingRecursiveComparison()
            .ignoringFields("id")
            .isEqualTo(nodeModel);

        return savedModel;
    }
}