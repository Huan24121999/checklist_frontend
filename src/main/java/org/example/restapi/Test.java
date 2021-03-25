package org.example.restapi;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.gson.Gson;
import org.example.model.*;
import org.zkoss.json.JSONObject;

import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.*;

public class Test {
    public static void main(String[] args) {
        ServerApi serverApi=ServerApi.getInstance();
        System.out.println(serverApi.getAll());
        ServerInfo serverInfo= serverApi.getAll().get(0);
        serverInfo.setIpAddress("192.168.0.101");
        System.out.println(serverApi.update(serverInfo));
        serverInfo.setId(null);
        System.out.println(serverApi.upload(serverInfo));
        System.out.println(serverApi.deleted(76));
    }

    public static void test2(){
        try {
            WebTarget webTarget = BaseConfig.getWebTarget().path("/checklist-item/get-all");
            webTarget.queryParam("id",1);
            webTarget = webTarget.queryParam("id",1);
            Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).accept("application/json");
            Response response = invocationBuilder.get();
            System.out.println(response);
            ArrayList res = (ArrayList)response.readEntity(Map.class).get("data");
            String object= res.toString();
            System.out.println(object);
            String jsonString = new Gson().toJson(res, ArrayList.class);
            ChecklistItem[] checklistItems= new Gson().fromJson(jsonString, ChecklistItem[].class);
            for (ChecklistItem checklistItem: checklistItems
                 ) {
                System.out.println(checklistItem);
            }
//            System.out.println(checklistItems);
        }catch (Exception ex){
            System.out.println(ex.getCause());
            System.out.println(ex.getMessage());
        }
    }
}
