package org.example.viewmodel;

import org.example.model.ChecklistItem;
import org.example.restapi.ChecklistItemApi;
import org.zkoss.bind.annotation.Init;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckListViewModel {
    private Map<String, List<ChecklistItem>> groupItems = new HashMap<>();
    private List<Integer> selected = new ArrayList<>();

    @Init
    public void init() {
        ChecklistItemApi checklistItemApi = ChecklistItemApi.getInstance();
        groupItems = checklistItemApi.getGroupItems();
    }

    public CheckListViewModel() {
    }

    public Map<String, List<ChecklistItem>> getGroupItems() {
        return groupItems;
    }

    public void setGroupItems(Map<String, List<ChecklistItem>> groupItems) {
        this.groupItems = groupItems;
    }

    public List<Integer> getSelected() {
        return selected;
    }

    public void setSelected(List<Integer> selected) {
        this.selected = selected;
    }
}
