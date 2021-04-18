package nsu.fit.basics.mapper;

import nsu.fit.basics.generated.model.Node;
import nsu.fit.basics.model.NodeModel;

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
            .tags(TagMapper.map(node.getTag()))
            .build();
    }


}
