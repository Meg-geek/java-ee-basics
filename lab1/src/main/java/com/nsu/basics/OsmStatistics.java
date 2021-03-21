package com.nsu.basics;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class OsmStatistics {
    private static final int START_AMOUNT = 1;
    private Map<String, Integer> userEditAmount = new HashMap<>();
    private Map<String, Integer> tagKeyNodeAmount = new HashMap<>();

    void addUser(String userName) {
        addElementToAmountMap(userEditAmount, userName);
    }

    void addTagKey(String tagKey) {
        addElementToAmountMap(tagKeyNodeAmount, tagKey);
    }

    private void addElementToAmountMap(Map<String, Integer> map, String key) {
        map.merge(key, START_AMOUNT, Integer::sum);
    }
}
