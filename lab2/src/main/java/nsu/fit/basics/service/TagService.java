package nsu.fit.basics.service;

import lombok.RequiredArgsConstructor;
import nsu.fit.basics.dao.TagDao;
import nsu.fit.basics.model.TagModel;

import java.util.List;

@RequiredArgsConstructor
public class TagService {
    private final TagDao tagDao;

    public void addTagsWithSimpleInsert(List<TagModel> tags) {

    }

    public void addTagsWithPreparedStatement(List<TagModel> tags) {

    }

    public void addTagsUsingBatch(List<TagModel> tags) {

    }
}
