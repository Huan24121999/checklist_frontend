package org.example.viewmodel;

import lombok.Data;
import org.example.model.ChecklistGroup;
import org.example.restapi.ChecklistGroupApi;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;

import java.util.ArrayList;
import java.util.List;

@Data
public class ConfigurationViewModel {

    private static final ChecklistGroupApi checklistGroupApi= ChecklistGroupApi.getInstance();

    private List<ChecklistGroup> checklistGroups=new ArrayList<>();

    @Init
    public void init(){
        checklistGroups=checklistGroupApi.getAll();
    }

    @Command("edit")
    public void edit(Object data){
        System.out.println(data);
    }

}
