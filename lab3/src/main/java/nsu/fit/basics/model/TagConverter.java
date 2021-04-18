package nsu.fit.basics.model;

import org.postgresql.util.HStoreConverter;
import org.springframework.util.StringUtils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class TagConverter implements AttributeConverter<TagsModel, String> {
    @Override
    public String convertToDatabaseColumn(TagsModel attribute) {
        return HStoreConverter.toString(attribute.getTags());
    }

    @Override
    public TagsModel convertToEntityAttribute(String dbData) {
        TagsModel tagsModel = new TagsModel();
        if (!StringUtils.hasText(dbData)) {
            return tagsModel;
        }
        tagsModel.setTags(HStoreConverter.fromString(dbData));
        return tagsModel;
    }
}
