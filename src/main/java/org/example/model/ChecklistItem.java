package org.example.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChecklistItem {

    private Integer id;

    private String name;

    private Integer typeCheck;

    private String action;

    private String valuePass;

    private Boolean isCheck;

    private String description;

    private ChecklistGroup checklistGroup;

    private ServerInfo server;


}
