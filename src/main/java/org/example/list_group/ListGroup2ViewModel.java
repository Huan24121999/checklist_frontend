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

public class ListGroup2ViewModel {

    private ChecklistGroupsModel checklistGroupsModel;

    @Init
    public void init() {
        ChecklistItemApi checklistItemApi= ChecklistItemApi.getInstance();
        checklistGroupsModel = new ChecklistGroupsModel(checklistItemApi.getAll().toArray(new ChecklistItem[0]), new ChecklistComparator());
        checklistGroupsModel.setMultiple(true);
    }

    public ChecklistGroupsModel getChecklistGroupsModel() {
        return checklistGroupsModel;
    }

    @Command("selectGroup")
    public void selectGroup(@BindingParam("data") Object data) {
        if(data instanceof ChecklistGroupsModel.CheckListGroupInfo) {
            ChecklistGroupsModel.CheckListGroupInfo groupInfo = (ChecklistGroupsModel.CheckListGroupInfo)data;
            int groupIndex = groupInfo.getGroupIndex() ;
            int childCount = checklistGroupsModel.getChildCount(groupIndex);
            boolean added = checklistGroupsModel.isSelected(groupInfo);
            for(int childIndex = 0; childIndex < childCount; childIndex++) {
                ChecklistItem checklistItem = checklistGroupsModel.getChild(groupIndex, childIndex);
                if(added) {
                    checklistGroupsModel.addToSelection(checklistItem);
                } else {
                    checklistGroupsModel.removeFromSelection(checklistItem);
                }
            }
        }
    }

    @Command("closeAll")
    public void closeAll(){
        System.out.println("CLOSE ALL");
        checklistGroupsModel.clearSelection();
    }
}
