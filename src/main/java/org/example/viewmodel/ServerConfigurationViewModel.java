package org.example.viewmodel;

import lombok.Data;
import org.example.model.ServerInfo;
import org.example.restapi.ServerApi;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;

import java.util.ArrayList;
import java.util.List;

@Data
public class ServerConfigurationViewModel {
    private static final ServerApi serverApi= ServerApi.getInstance();

    private List<ServerInfo> serverInfos= new ArrayList<>();

    private List<ServerInfo> selectedServers= new ArrayList<>();

    private ServerInfo selectedServer=null;

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
        System.out.println(selectedServer);
    }
    @Command("show")
    public void show(){
        System.out.println(selectedServers);
    }

}
