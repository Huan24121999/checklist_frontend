package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChecklistHistory {

    private Integer id;

    private String result;

    private Timestamp startTime;

    private Timestamp endTime;

    private String detail;

    private String username;

}
