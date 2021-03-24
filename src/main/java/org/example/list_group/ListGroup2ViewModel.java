package org.example.list_group;

import com.google.gson.Gson;
import lombok.Data;
import org.example.grouping_model.ChecklistComparator;
import org.example.model.*;
import org.example.restapi.ChecklistHistoryApi;
import org.example.restapi.ChecklistItemApi;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.util.Notification;
import org.zkoss.zkmax.ui.util.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Data
public class ListGroup2ViewModel {

    private ChecklistGroupsModel checklistGroupsModel;

    private List<ChecklistHistory> checklistHistories= new ArrayList<>();

    private ChecklistHistoryApi checklistHistoryApi= ChecklistHistoryApi.getInstance();

    private List<HistoryResult> historyResults=new ArrayList<>();

    private HistoryTotal currentHistory= new HistoryTotal();

    private List<ChecklistItem> checklistItemsChild= new ArrayList<>();
    
    private boolean visibleHistory =false;

    @Init
    public void init() {
        ChecklistItemApi checklistItemApi = ChecklistItemApi.getInstance();
        List<ChecklistItem> checklistItems= checklistItemApi.getAll();
        if(checklistItems!=null) {
            checklistGroupsModel = new ChecklistGroupsModel(checklistItems.toArray(new ChecklistItem[0]), new ChecklistComparator());
            checklistGroupsModel.setMultiple(true);
            checklistItemsChild.addAll(checklistItems);
        }
        checklistHistories.addAll(checklistHistoryApi.getAll());

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
    @NotifyChange({"checklistHistories","historyResults","currentHistory","visibleHistory"})
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
        System.out.println("kkkkkkkkkkkkkkkkk");
        if(ids.size()>0) {
            try {
                System.out.println("LON HƠN 0");
                ChecklistHistory checklistHistory = checklistItemApi.Execute(ids);
                System.out.println(checklistHistory);
                checklistHistories.add(0,checklistHistory);
                convertHistory(checklistHistory.getId());
                Toast.show("Executed. Please view the result.",null,null,2000,true);
            }catch(Exception  ex){
                System.out.println("LOI ROI");
                System.out.println(ex.getMessage());
            }
        }
        else{
            Toast.show("Please chose at least 1 item","warning",null,2000,true);
        }

    }


    @Command("convertHistory")
    @NotifyChange({"historyResults","currentHistory","visibleHistory"})
    public void convertHistory(@BindingParam("data") Object data){
        System.out.println(data);
        if(data instanceof Integer){
            visibleHistory =true;
            int historyId = ((Integer)data).intValue();
            for (ChecklistHistory history:checklistHistories
                 ) {
                if(history.getId()==historyId){
                    String result= history.getResult();
                    int[] convertedResult= convertResultToNumber(result);
                    currentHistory.setPassed(convertedResult[0]);
                    currentHistory.setTotal(convertedResult[1]);
                    String detail= history.getDetail();
                    try {
                        HistoryResult[] results = new Gson().fromJson(detail, HistoryResult[].class);
                        historyResults = Arrays.asList(results);
                    }catch (Exception ex){
                        System.out.println(ex.getMessage());
                    }
                    break;
                }
            }
        }
    }

    @Command("closeDetailResult")
    @NotifyChange("visibleHistory")
    public void closeDetailResult(){
        visibleHistory=false;
    }

    public int[] convertResultToNumber(String result){
        String[] numbers= result.split("/");
        int[] arrays=new int[2];
        arrays[0]= Integer.parseInt(numbers[0]);
        arrays[1] = Integer.parseInt(numbers[1]);
        return arrays;
    }
}
