package org.example.restapi;

import com.google.gson.Gson;
import org.example.model.ChecklistHistory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ChecklistHistoryApi {

    private static final Logger logger= LoggerFactory.getLogger(ChecklistHistoryApi.class);

    private static ChecklistHistoryApi checklistItemApi= new ChecklistHistoryApi();

    public static ChecklistHistoryApi getInstance(){
        return checklistItemApi;
    }

    public List<ChecklistHistory> getAll(){
        try {
            WebTarget webTarget = BaseConfig.getWebTarget().path("/checklist-history/find-all");
            Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
            Response response = invocationBuilder.get();
            ArrayList res = (ArrayList) response.readEntity(Map.class).get("data");
            String jsonString = new Gson().toJson(res, ArrayList.class);
            ChecklistHistory[] checklistHistories = new Gson().fromJson(jsonString, ChecklistHistory[].class);
            return Arrays.asList(checklistHistories);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            System.out.println(ex.getMessage());
            return null;
        }
    }
}
