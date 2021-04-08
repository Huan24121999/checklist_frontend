package org.example.renderer;

import lombok.Data;

/**
 * to fill information of checklist item to each row on table
 */
@Data
public class ChecklistFilter {
    private String name;
    private String action;
    private String description;
    private Integer id;
    private Integer typeCheck;
    private String valuePass;
}
