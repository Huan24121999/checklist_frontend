package org.example.restapi;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.gson.Gson;
import org.example.model.ChecklistItem;
import org.zkoss.json.JSONObject;

import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.*;

public class Test {
    public static void main(String[] args) {
        ChecklistItemApi checklistItemApi= ChecklistItemApi.getInstance();
        Map<String,List<ChecklistItem>> groupItems= checklistItemApi.getGroupItems();
        System.out.println(groupItems.size());
        System.out.println(checklistItemApi.getGroupItems());
//        test2();
        System.out.println("Finish");
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
