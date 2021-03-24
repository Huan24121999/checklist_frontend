package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChecklistHistory implements Comparable{

    private Integer id;

    private String result;

    private Timestamp startTime;

    private Timestamp endTime;

    private String detail;

    private String username;

    @Override
    public int compareTo(Object o) {
        if(o instanceof ChecklistHistory){
            ChecklistHistory other=(ChecklistHistory) o;
            return this.result.compareTo(other.result);
        }
        return 0;
    }
}
