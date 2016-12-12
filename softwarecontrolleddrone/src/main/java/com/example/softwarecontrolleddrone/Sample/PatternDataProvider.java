package com.example.softwarecontrolleddrone.Sample;

import com.example.softwarecontrolleddrone.Model.PatternItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Roy on 2016-12-12.
 */

public class PatternDataProvider {

    public static List<PatternItem> dataItemList;
    public static Map<String, PatternItem> dataItemMap;

    static {
        dataItemList = new ArrayList<>();
        dataItemMap = new HashMap<>();

        addItem(new PatternItem("a", "Pattern A"));

        addItem(new PatternItem("b", "Pattern B"));

        addItem(new PatternItem("c", "Pattern C"));
    }

    private static void addItem(PatternItem item) {
        dataItemList.add(item);
        dataItemMap.put(item.getItemId(), item);
    }
}
