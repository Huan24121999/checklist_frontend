package org.example.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckListData {
    private Map<String, List<ChecklistItem>> groupItems= new HashMap<>();

    public CheckListData() {
        ArrayList<ChecklistItem> checklistItems= new ArrayList<>();
        checklistItems.add(new ChecklistItem());
    }
}
