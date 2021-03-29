package org.example.viewmodel;

import lombok.Data;
import org.example.model.ServerInfo;
import org.example.restapi.ChecklistItemApi;
import org.example.restapi.ServerApi;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Window;

import java.util.ArrayList;
import java.util.List;

@Data
public class ServerConfigurationViewModel {
    private static final ServerApi serverApi= ServerApi.getInstance();

    private static final ChecklistItemApi checklistItemApi = ChecklistItemApi.getInstance();

    private List<ServerInfo> serverInfos= new ArrayList<>();

    private List<ServerInfo> selectedServers= new ArrayList<>();


    @Init
    public void init(){
        serverInfos= serverApi.getAll();
    }

    @Command("edit")
    public void edit(Object data){
        System.out.println(data);
        if(data instanceof Integer){
            System.out.println(data);
        }
        else if(data instanceof String){
            System.out.println(data.toString());
        }
        System.out.println(data.toString());
    }
    @Command("show")
    public void show(){
        System.out.println(selectedServers);
    }

    @Command("delete")
    public void delete(Object data){
        Window window = (Window) Executions.createComponents(
                "/widgets/menu/navbar/pages/modal/delete_server.zul", null, null);
        window.doModal();
    }
}
