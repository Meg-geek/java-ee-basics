package nsu.fit.basics.mapper;

import nsu.fit.basics.generated.model.Way;
import nsu.fit.basics.model.WayModel;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class WayMapper {
    public static List<WayModel> map(List<Way> ways) {
        if (CollectionUtils.isEmpty(ways)) {
            return Collections.emptyList();
        }
        return ways.stream()
            .map(WayMapper::map)
            .collect(Collectors.toList());
    }

    private static WayModel map(Way way) {
        return WayModel.builder()
            .id(way.getId())
            .isVisible(way.isVisible())
            .user(way.getUser())
            .tags(TagMapper.map(way.getTag()))
            .build();
    }
}
