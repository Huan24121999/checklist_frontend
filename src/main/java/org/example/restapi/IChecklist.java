package org.example.restapi;

import javax.ws.rs.client.WebTarget;
import java.util.ArrayList;

public interface IChecklist<T> {
    WebTarget webTarget = BaseConfig.getWebTarget();

    ArrayList<T> getData();
}
