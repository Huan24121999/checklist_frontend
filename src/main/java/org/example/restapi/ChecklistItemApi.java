package org.example.restapi;

import com.google.gson.Gson;
import org.example.model.ChecklistItem;
import org.example.model.ResultItem;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.*;

public class ChecklistItemApi {

    private static ChecklistItemApi checklistItemApi = new ChecklistItemApi();

    public static ChecklistItemApi getInstance() {
        return checklistItemApi;
    }

    public List<ChecklistItem> getAll() {
        try {
            WebTarget webTarget = BaseConfig.getWebTarget().path("/checklist-item/get-all");
            Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
            Response response = invocationBuilder.get();
            ArrayList res = (ArrayList) response.readEntity(Map.class).get("data");;
            String jsonString = new Gson().toJson(res, ArrayList.class);
            System.out.println("==========================");
            System.out.println(jsonString);
            ChecklistItem[] checklistItems = new Gson().fromJson(jsonString, ChecklistItem[].class);
            return Arrays.asList(checklistItems);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public Map<String, List<ChecklistItem>> getGroupItems() {
        Map<String, List<ChecklistItem>> groupItems= new HashMap<>();
        List<ChecklistItem> checklistItems = getAll();
        int length = checklistItems.size();
        int i = 0, j = 0;
        for (i = 0; i < length ; i++) {
            ChecklistItem previousItem= checklistItems.get(i);
            List<ChecklistItem> checklistItemList= new ArrayList<>();
            checklistItemList.add(previousItem);
            for (j = i+1; j < length; j++) {
                ChecklistItem currentItem= checklistItems.get(j);
                if(currentItem.getChecklistGroup().getId() == previousItem.getChecklistGroup().getId()){
                    checklistItemList.add(currentItem);
                }
                else{
                    break;
                }
            }
            groupItems.put(previousItem.getChecklistGroup().getName(),checklistItemList);
            i=j-1;
        }
        return groupItems;
    }

    public List<ResultItem> Execute(List<Integer> ids){
        try {
            WebTarget webTarget = BaseConfig.getWebTarget().path("/execute/");
            Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
            Response response = invocationBuilder.post(Entity.entity(ids, MediaType.APPLICATION_JSON));
            System.out.println(response);
            ArrayList res = (ArrayList) response.readEntity(Map.class).get("data");
            String jsonString = new Gson().toJson(res, ArrayList.class);
            ResultItem[] resultItems = new Gson().fromJson(jsonString, ResultItem[].class);
            System.out.println(resultItems);
            return Arrays.asList(resultItems);
        }catch (Exception ex){
            System.out.println(ex.getCause());
            return null;
        }
    }

}

