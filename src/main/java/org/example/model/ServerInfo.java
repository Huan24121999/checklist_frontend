package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Information of Server
 *
 * @author huannt14
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServerInfo {

    private Integer id;

    private String name;

    private String ipAddress;

    private String username;

    private String password;
}
