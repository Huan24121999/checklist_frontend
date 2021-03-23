package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultItem {

    private String name;
    
    private String groupCheck;
    
    private Boolean isPassed;

    private String requiredResult;
    
    private String result;
}
