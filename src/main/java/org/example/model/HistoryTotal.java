package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * to store number of chosen items and the number of passed items
 */
@Data
@AllArgsConstructor
public class HistoryTotal {
    private int total;

    private int passed;

    public HistoryTotal() {
        total=0;
        passed=0;
    }
}
