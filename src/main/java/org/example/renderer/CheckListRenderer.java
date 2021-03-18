package org.example.renderer;

import org.example.list_group.ChecklistGroupsModel;
import org.example.model.ChecklistItem;
import org.zkoss.zul.*;

public class CheckListRenderer implements ListitemRenderer<Object> {

    @Override
    public void render(Listitem listitem, Object obj, int index) throws Exception {

        if (listitem instanceof Listgroup) {
            ChecklistGroupsModel.CheckListGroupInfo groupInfo = (ChecklistGroupsModel.CheckListGroupInfo) obj;
            ChecklistItem checklistItem = groupInfo.getFirstChild();
            String groupTxt;
            System.out.println(groupInfo.getGroupIndex());
            System.out.println(groupInfo.getColIndex());
            System.out.println(groupInfo.getFirstChild().toString());
            switch (groupInfo.getColIndex()) {
                case 0:
                    groupTxt = checklistItem.getChecklistGroup().getName();
                    break;
                case 1:
                    groupTxt = checklistItem.getName();
                    break;
                case 2:
                    groupTxt = checklistItem.getDescription();
                    break;
                case 3:
                    groupTxt = checklistItem.getServer().getName();
                    break;
                default:
                    groupTxt = checklistItem.getName();
            }
            listitem.appendChild(new Listcell(groupTxt));
            listitem.setValue(obj);
        } else if (listitem instanceof Listgroupfoot) {
            Listcell cell = new Listcell();
            cell.setSclass("foodFooter");
            cell.setSpan(6);
            cell.appendChild(new Label("Total " + obj + " Items"));
            listitem.appendChild(cell);
        } else {
            ChecklistItem data = (ChecklistItem) obj;
            listitem.appendChild(new Listcell(data.getChecklistGroup().getName()));
            listitem.appendChild(new Listcell(data.getName()));
            listitem.appendChild(new Listcell(data.getDescription()));
            listitem.appendChild(new Listcell(data.getServer().getName()));
            listitem.setValue(data);
        }
    }
}
