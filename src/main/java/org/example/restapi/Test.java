package org.example.restapi;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.gson.Gson;
import org.example.model.ChecklistHistory;
import org.example.model.ChecklistItem;
import org.example.model.ResultItem;
import org.zkoss.json.JSONObject;

import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.*;

public class Test {
    public static void main(String[] args) {
//        ChecklistItemApi checklistItemApi= ChecklistItemApi.getInstance();
//        List<Integer> ids= new ArrayList<>();
//        ids.add(1);
//        ids.add(2);
//        ids.add(3);
//        System.out.println(checklistItemApi.Execute(ids));
//        ChecklistHistoryApi checklistHistoryApi= ChecklistHistoryApi.getInstance();;
//        System.out.println(checklistHistoryApi.getAll());

        try {
            String check = "[{\"itemId\":1,\"isPassed\":false,\"detail\":\"Error: java.net.ConnectException: Connection timed out: connect\"},{\"itemId\":3,\"isPassed\":false,\"detail\":\"Error: java.net.ConnectException: Connection timed out: connect\"},{\"itemId\":2,\"isPassed\":false,\"detail\":\"Error: java.net.ConnectException: Connection timed out: connect\"}]";
            ResultItem[] resultItems = new Gson().fromJson(check, ResultItem[].class);
            System.out.println(resultItems);
            System.out.println(resultItems.length);
        }catch (Exception ex){
            System.out.println(ex.getCause());
        }

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
