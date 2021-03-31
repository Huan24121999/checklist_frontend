package org.example.renderer;
import org.example.model.ChecklistItem;
import org.zkoss.zul.GroupComparator;

import java.io.Serializable;
import java.util.Comparator;

public class ChecklistComparator implements Comparator<ChecklistItem>, GroupComparator<ChecklistItem>, Serializable {

    private static final long serialVersionUID = 1L;

    public int compare(ChecklistItem o1, ChecklistItem o2) {
        return o1.getChecklistGroup().getId().compareTo(o2.getChecklistGroup().getId());
    }

    public int compareGroup(ChecklistItem o1, ChecklistItem o2) {
        if(o1.getChecklistGroup().getId().equals(o2.getChecklistGroup().getId()))
            return 0;
        else
            return 1;
    }
}
