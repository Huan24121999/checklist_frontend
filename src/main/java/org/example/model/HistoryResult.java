package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistoryResult implements Comparable{

    private String name;

    private String groupCheck;

    private boolean passed;

    private String requiredResult;

    private String result;

    public void setPassed(boolean passed) {
        this.passed = passed;
    }

    @Override
    public int compareTo(Object o) {
        if(o instanceof HistoryResult){
            HistoryResult other= (HistoryResult) o;
            if(this.groupCheck.equals(other.groupCheck)){
                return this.name.compareTo(other.name);
            }
            return this.groupCheck.compareTo(other.groupCheck);
        }
        return 0;
    }
}
