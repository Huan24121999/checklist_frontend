package org.example.restapi;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

public class BaseConfig {
    private static final String PATH="http://localhost:8686/api/v1/private";
    private static Client client = ClientBuilder.newClient();
    public static WebTarget getWebTarget(){
        return client.target(PATH);
    }
}
