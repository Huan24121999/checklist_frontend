package org.example.viewmodel;

import org.example.model.ChecklistItem;
import org.zkoss.zul.GroupsModelArray;

import java.util.Comparator;

public class ChecklistGroupsModel extends GroupsModelArray<ChecklistItem, ChecklistGroupsModel.CheckListGroupInfo, Object, Object> {

    private static final long serialVersionUID = 1L;
    public ChecklistGroupsModel(ChecklistItem[] data, Comparator<ChecklistItem> cmpr) {
        super(data, cmpr);
    }

    protected ChecklistGroupsModel.CheckListGroupInfo createGroupHead(ChecklistItem[] groupdata, int index, int col) {
        return new ChecklistGroupsModel.CheckListGroupInfo(groupdata[0], index, col);
    }

    protected Object createGroupFoot(ChecklistItem[] groupdata, int index, int col) {
        // Return the sum number of each group
        return groupdata.length;
    }


    public static class CheckListGroupInfo {
        private ChecklistItem firstChild;
        private int groupIndex;
        private int colIndex;

        public CheckListGroupInfo(ChecklistItem firstChild, int groupIndex, int colIndex) {
            super();
            this.firstChild = firstChild;
            this.groupIndex = groupIndex;
            this.colIndex = colIndex;
        }

        public ChecklistItem getFirstChild() {
            return firstChild;
        }

        public void setFirstChild(ChecklistItem firstChild) {
            this.firstChild = firstChild;
        }

        public int getGroupIndex() {
            return groupIndex;
        }

        public void setGroupIndex(int groupIndex) {
            this.groupIndex = groupIndex;
        }

        public int getColIndex() {
            return colIndex;
        }

        public void setColIndex(int colIndex) {
            this.colIndex = colIndex;
        }
    }
}
