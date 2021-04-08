package org.example.renderer;

import org.example.viewmodel.ChecklistGroupsModel;
import org.example.model.CheckType;
import org.example.model.ChecklistItem;
import org.zkoss.zul.*;

public class CheckListRenderer implements ListitemRenderer<Object> {

    @Override
    public void render(Listitem listitem, Object obj, int index) throws Exception {
        if (listitem instanceof Listgroup) {
            ChecklistGroupsModel.CheckListGroupInfo groupInfo = (ChecklistGroupsModel.CheckListGroupInfo) obj;
            ChecklistItem checklistItem = groupInfo.getFirstChild();
            String groupTxt;
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
            cell.setSclass("checklistFooter");
            cell.setSpan(4);
            cell.appendChild(new Label("Total " + obj + " Items"));
            listitem.appendChild(cell);
        } else {
            ChecklistItem data = (ChecklistItem) obj;
            String typeCheckString="";
            int typeCheck = data.getTypeCheck();

            if(typeCheck== CheckType.SERVER_CHECK)
                typeCheckString="Server Check";
            else if(typeCheck==CheckType.API_BOOLEAN_CHECK)
                typeCheckString="RestApi: Boolean";
            else if(typeCheck==CheckType.API_DATA_CHECK)
                typeCheckString="RestApi: Comparison";

            String valuePass=data.getValuePass();
            String operator= data.getOperator();
            if(operator==null || operator.equals("==") || operator.equals("=")){
                operator="";
            }

            listitem.appendChild(new Listcell(data.getChecklistGroup()!= null?data.getChecklistGroup().getName():""));
            listitem.appendChild(new Listcell(data.getName()));
            listitem.appendChild(new Listcell(typeCheckString));
            listitem.appendChild(new Listcell(operator+valuePass));
            listitem.appendChild(new Listcell(data.getDescription()));
            listitem.appendChild(new Listcell(data.getServer()!=null? data.getServer().getName():""));
            listitem.setValue(data);
        }
    }
}
