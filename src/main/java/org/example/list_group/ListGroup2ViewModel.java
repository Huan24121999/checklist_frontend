package org.example.list_group;

import com.google.gson.Gson;
import org.example.data.FoodData;
import org.example.data.pojo.Food;
import org.example.grouping_model.ChecklistComparator;
import org.example.grouping_model.FoodComparator;
import org.example.model.ChecklistHistory;
import org.example.model.ChecklistItem;
import org.example.model.ResultItem;
import org.example.restapi.ChecklistHistoryApi;
import org.example.restapi.ChecklistItemApi;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Listbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class ListGroup2ViewModel {

    private ChecklistGroupsModel checklistGroupsModel;

    private List<ChecklistHistory> checklistHistories= new ArrayList<>();

    private ChecklistHistoryApi checklistHistoryApi= ChecklistHistoryApi.getInstance();

    private final List<ChecklistItem> checklistItemsChild= new ArrayList<>();

    @Init
    public void init() {
        ChecklistItemApi checklistItemApi = ChecklistItemApi.getInstance();
        List<ChecklistItem> checklistItems= checklistItemApi.getAll();
        if(checklistItems!=null) {
            checklistGroupsModel = new ChecklistGroupsModel(checklistItems.toArray(new ChecklistItem[0]), new ChecklistComparator());
            checklistGroupsModel.setMultiple(true);
            checklistItemsChild.addAll(checklistItems);
        }
        checklistHistories=checklistHistoryApi.getAll();

    }

    public ChecklistGroupsModel getChecklistGroupsModel() {
        return checklistGroupsModel;
    }

    public List<ChecklistHistory> getChecklistHistories() {
        return checklistHistories;
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
            if(object instanceof ChecklistItem){
                ChecklistItem checklistItem=(ChecklistItem) object;
                ids.add(checklistItem.getId());
            }
        }
        ChecklistItemApi checklistItemApi= ChecklistItemApi.getInstance();
        if(ids.size()>0)
            checklistItemApi.Execute(ids);
    }

    @Command("selectHistory")
    public void selectHistory(@BindingParam("data")Object data){
        System.out.println(data);
        if(data instanceof ChecklistHistory){
            try {
                ChecklistHistory checklistHistory = (ChecklistHistory) data;
                System.out.println(checklistHistory.getDetail());
                ResultItem[] resultItems = new Gson().fromJson(checklistHistory.getDetail(), ResultItem[].class);
                System.out.println(resultItems);
                System.out.println(resultItems.length);
                List<ResultItem> resultItemList= Arrays.asList(resultItems);
                System.out.println(checklistGroupsModel);
                checklistGroupsModel.clearSelection();
                for (ResultItem resultItem: resultItems
                     ) {
                    for (ChecklistItem ch: checklistItemsChild
                         ) {
                        if(ch.getId()==resultItem.getItemId()){
                            checklistGroupsModel.addToSelection(ch);
                        }
                    }
                }
                System.out.println(checklistGroupsModel.getSelection());
            }catch (Exception ex){
                System.out.println(ex.getCause());
            }
        }
    }


}
