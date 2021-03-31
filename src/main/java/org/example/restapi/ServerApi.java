package org.example.restapi;

import com.google.gson.Gson;
import org.example.model.ServerInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.*;

public class ServerApi {
    private static final Logger logger= LoggerFactory.getLogger(ChecklistGroupApi.class);

    private static ServerApi serverApi= new ServerApi();

    public static ServerApi getInstance(){
        return serverApi;
    }

    public List<ServerInfo> getAll(){
        try {
            WebTarget webTarget = BaseConfig.getWebTarget().path("/server-info/find-all");
            Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
            Response response = invocationBuilder.get();
            ArrayList res = (ArrayList) response.readEntity(Map.class).get("data");
            String jsonString = new Gson().toJson(res, ArrayList.class);
            ServerInfo[] serverInfos = new Gson().fromJson(jsonString, ServerInfo[].class);
            return Arrays.asList(serverInfos);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return null;
        }
    }

    public ServerInfo upload(ServerInfo serverInfo){
        try {
            WebTarget webTarget = BaseConfig.getWebTarget().path("/server-info/upload");
            Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
            Response response = invocationBuilder.post(Entity.entity(serverInfo,MediaType.APPLICATION_JSON));
            HashMap res = (HashMap) response.readEntity(Map.class).get("data");
            String jsonString = new Gson().toJson(res, HashMap.class);
            ServerInfo serverInfoResult = new Gson().fromJson(jsonString, ServerInfo.class);
            return serverInfoResult;
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return null;
        }
    }

    public ServerInfo update(ServerInfo serverInfo){
        try {
            WebTarget webTarget = BaseConfig.getWebTarget().path("/server-info/update");
            Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
            Response response = invocationBuilder.put(Entity.entity(serverInfo,MediaType.APPLICATION_JSON));
            HashMap res = (HashMap) response.readEntity(Map.class).get("data");
            String jsonString = new Gson().toJson(res, HashMap.class);
            ServerInfo serverInfoResult = new Gson().fromJson(jsonString, ServerInfo.class);
            return serverInfoResult;
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return null;
        }
    }

    public Boolean deleted(Integer serverId){
        try {
            WebTarget webTarget = BaseConfig.getWebTarget().path("/server-info/delete").queryParam("id",serverId);
            Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
            Response response = invocationBuilder.delete();
            String res = (String) response.readEntity(Map.class).get("message");
            if(res.equals("deleted"))
                return true;
            return false;
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return false;
        }
    }

}
