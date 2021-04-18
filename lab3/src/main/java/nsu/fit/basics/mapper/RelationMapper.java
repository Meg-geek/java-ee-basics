package nsu.fit.basics.mapper;

import nsu.fit.basics.generated.model.Relation;
import nsu.fit.basics.model.RelationModel;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class RelationMapper {
    public static List<RelationModel> map(List<Relation> relations) {
        if (CollectionUtils.isEmpty(relations)) {
            return Collections.emptyList();
        }
        return relations.stream()
            .map(RelationMapper::map)
            .collect(Collectors.toList());
    }

    private static RelationModel map(Relation relation) {
        return RelationModel.builder()
            .id(relation.getId())
            .isVisible(relation.isVisible())
            .user(relation.getUser())
            .tags(TagMapper.map(relation.getTag()))
            .build();
    }
}
