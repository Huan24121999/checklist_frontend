package org.example.list_group;

import org.example.data.FoodData;
import org.example.data.pojo.Food;
import org.example.grouping_model.ChecklistComparator;
import org.example.grouping_model.FoodComparator;
import org.example.model.ChecklistItem;
import org.example.restapi.ChecklistItemApi;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ListGroup2ViewModel {

    private ChecklistGroupsModel checklistGroupsModel;

    @Init
    public void init() {
        ChecklistItemApi checklistItemApi = ChecklistItemApi.getInstance();
        checklistGroupsModel = new ChecklistGroupsModel(checklistItemApi.getAll().toArray(new ChecklistItem[0]), new ChecklistComparator());
        checklistGroupsModel.setMultiple(true);
    }

    public ChecklistGroupsModel getChecklistGroupsModel() {
        return checklistGroupsModel;
    }

    @Command("selectGroup")
    public void selectGroup(@BindingParam("data") Object data) {
        if (data instanceof ChecklistGroupsModel.CheckListGroupInfo) {
            ChecklistGroupsModel.CheckListGroupInfo groupInfo = (ChecklistGroupsModel.CheckListGroupInfo) data;
            int groupIndex = groupInfo.getGroupIndex();
            int childCount = checklistGroupsModel.getChildCount(groupIndex);
            boolean added = checklistGroupsModel.isSelected(groupInfo);
            for (int childIndex = 0; childIndex < childCount; childIndex++) {
                ChecklistItem checklistItem = checklistGroupsModel.getChild(groupIndex, childIndex);
                if (added) {
                    checklistGroupsModel.addToSelection(checklistItem);
                } else {
                    checklistGroupsModel.removeFromSelection(checklistItem);
                }
            }
        }
    }

    @Command("refresh")
    public void refresh() {
        checklistGroupsModel.clearSelection();
    }

    @Command("closeAll")
    public void closeAll() {
        int length = checklistGroupsModel.getGroupCount();
        for (int i = 0; i < length; i++) {
            checklistGroupsModel.setClose(i,true);
        }
    }

    @Command("expandAll")
    public void expandAll() {
        int length = checklistGroupsModel.getGroupCount();
        for (int i = 0; i < length; i++) {
            checklistGroupsModel.setClose(i,false);
        }
    }

    @Command("execute")
    public void execute(){
        List<Integer> ids=new ArrayList<>();
        Set<Object> checklistItemList= checklistGroupsModel.getSelection();
        for (Object object: checklistItemList
             ) {
            ChecklistItem checklistItem=(ChecklistItem) object;
            ids.add(checklistItem.getId());
        }
        ChecklistItemApi checklistItemApi= ChecklistItemApi.getInstance();
        checklistItemApi.Execute(ids);
    }
}
