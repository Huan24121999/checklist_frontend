package org.example.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class HistoryTotal {
    private int total;

    private int passed;

    public HistoryTotal() {
        total=0;
        passed=0;
    }
}
