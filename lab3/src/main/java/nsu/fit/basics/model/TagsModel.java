package nsu.fit.basics.model;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class TagsModel {
    private Map<String, String> tags = new HashMap<>();

    public TagsModel addTag(String key, String value) {
        tags.put(key, value);
        return this;
    }
}
