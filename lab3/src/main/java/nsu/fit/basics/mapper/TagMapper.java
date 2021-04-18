package nsu.fit.basics.mapper;

import nsu.fit.basics.generated.model.Tag;
import nsu.fit.basics.model.TagsModel;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

public class TagMapper {
    public static TagsModel map(List<Tag> tags) {
        TagsModel tagsModel = new TagsModel();
        if (CollectionUtils.isEmpty(tags)) {
            return tagsModel;
        }

        tags.forEach(tag -> tagsModel.addTag(tag.getK(), tag.getV()));
        return tagsModel;
    }
}
