package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Group of Checklist items
 *
 * @author huannt14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChecklistGroup {

    private Integer id;

    private String name;

    private String description;

    private Boolean isCheck;
}
