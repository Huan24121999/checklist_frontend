package org.example.grouping_model;

import lombok.Data;

@Data
public class ChecklistFilter {
    private String name;
    private String action;
    private String description;
    private Integer id;
    private Integer typeCheck;
    private String valuePass;
}
