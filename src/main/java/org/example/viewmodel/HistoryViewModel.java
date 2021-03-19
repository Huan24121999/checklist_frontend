package org.example.viewmodel;

import org.example.model.ChecklistHistory;
import org.example.restapi.ChecklistHistoryApi;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;

import java.util.ArrayList;
import java.util.List;

public class HistoryViewModel {
    private List<ChecklistHistory> checklistHistories= new ArrayList<>();

    private ChecklistHistoryApi checklistHistoryApi= ChecklistHistoryApi.getInstance();

    @Init
    public void innit(){
        checklistHistories=checklistHistoryApi.getAll();
    }

    public List<ChecklistHistory> getChecklistHistories() {
        return checklistHistories;
    }

    public void setChecklistHistories(List<ChecklistHistory> checklistHistories) {
        this.checklistHistories = checklistHistories;
    }

    @Command("selectHistory")
    public void selectHistory(@BindingParam("data")Object data){
        System.out.println(data);
        if(data instanceof ChecklistHistory){

        }
    }
}
