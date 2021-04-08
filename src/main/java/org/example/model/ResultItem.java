package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Result of execution, using to store each record of output's each checklist item
 * after execution
 *
 * @author huannt14
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultItem implements Comparable{

    private String name;

    private String groupCheck;

    private boolean isPassed;

    private String requiredResult;

    private String result;

    public void setIsPassed(boolean passed) {
        this.isPassed = passed;
    }

    public boolean getIsPassed() {
        return isPassed;
    }


    @Override
    public int compareTo(Object o) {
        if(o instanceof ResultItem){
            ResultItem other= (ResultItem) o;
            if(this.groupCheck.equals(other.groupCheck)){
                return this.name.compareTo(other.name);
            }
            return this.groupCheck.compareTo(other.groupCheck);
        }
        return 0;
    }
}
