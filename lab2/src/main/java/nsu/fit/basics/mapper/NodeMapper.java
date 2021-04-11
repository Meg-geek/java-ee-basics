package nsu.fit.basics.mapper;

import nsu.fit.basics.generated.model.Node;
import nsu.fit.basics.generated.model.Tag;
import nsu.fit.basics.model.NodeModel;
import nsu.fit.basics.model.TagModel;
import org.apache.commons.collections4.CollectionUtils;

import java.math.BigInteger;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class NodeMapper {
    public static List<NodeModel> map(List<Node> nodes) {
        return nodes.stream()
            .map(NodeMapper::map)
            .collect(Collectors.toList());
    }

    public static NodeModel map(Node node) {
        return NodeModel.builder()
            .id(node.getId())
            .latitude(node.getLat())
            .longitude(node.getLon())
            .user(node.getUser())
            .tags(map(node.getTag(), node.getId()))
            .build();
    }

    private static List<TagModel> map(List<Tag> tags, BigInteger nodeId) {
        if (CollectionUtils.isEmpty(tags)) {
            return Collections.emptyList();
        }

        return tags.stream()
            .map(tag -> new TagModel(tag.getK(), tag.getV(), nodeId))
            .collect(Collectors.toList());
    }
}
