package org.example.restapi;

import com.google.gson.Gson;
import org.example.model.ChecklistGroup;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.*;

public class ChecklistGroupApi {
//    private static final Logger logger= LoggerFactory.getLogger(ChecklistGroupApi.class);
    private static ChecklistGroupApi checklistGroupApi= new ChecklistGroupApi();

    public static ChecklistGroupApi getInstance(){
        return checklistGroupApi;
    }

    public List<ChecklistGroup> getAll(){
        try {
            WebTarget webTarget = BaseConfig.getWebTarget().path("/checklist-group/find-all");
            Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
            Response response = invocationBuilder.get();
            ArrayList res = (ArrayList) response.readEntity(Map.class).get("data");
            String jsonString = new Gson().toJson(res, ArrayList.class);
            ChecklistGroup[] checklistGroups = new Gson().fromJson(jsonString, ChecklistGroup[].class);
            return Arrays.asList(checklistGroups);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
//            logger.error(ex.getMessage());
            return null;
        }
    }

    public ChecklistGroup upload(ChecklistGroup checklistGroup){
        try {
            WebTarget webTarget = BaseConfig.getWebTarget().path("/checklist-group/upload");
            Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
            Response response = invocationBuilder.post(Entity.entity(checklistGroup,MediaType.APPLICATION_JSON));
            HashMap res = (HashMap) response.readEntity(Map.class).get("data");
            String jsonString = new Gson().toJson(res, HashMap.class);
            ChecklistGroup checklistGroupResult = new Gson().fromJson(jsonString, ChecklistGroup.class);
            return checklistGroupResult;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
//            logger.error(ex.getMessage());
            return null;
        }
    }

    public ChecklistGroup update(ChecklistGroup checklistGroup){
        try {
            WebTarget webTarget = BaseConfig.getWebTarget().path("/checklist-group/update");
            Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
            Response response = invocationBuilder.put(Entity.entity(checklistGroup,MediaType.APPLICATION_JSON));
            HashMap res = (HashMap) response.readEntity(Map.class).get("data");
            String jsonString = new Gson().toJson(res, HashMap.class);
            ChecklistGroup checklistGroupResult = new Gson().fromJson(jsonString, ChecklistGroup.class);
            return checklistGroupResult;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
//            logger.error(ex.getMessage());
            return null;
        }
    }

    public Boolean deleted(Integer groupId){
        try {
            WebTarget webTarget = BaseConfig.getWebTarget().path("/checklist-group/delete").queryParam("id",groupId);
            Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
            Response response = invocationBuilder.delete();
            String res = (String) response.readEntity(Map.class).get("message");
            if(res.equals("deleted"))
                return true;
            return false;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
//            logger.error(ex.getMessage());
            return false;
        }
    }
}
