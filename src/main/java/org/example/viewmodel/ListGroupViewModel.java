package org.example.viewmodel;

import com.google.gson.Gson;
import lombok.Data;
import org.example.model.ChecklistHistory;
import org.example.model.ChecklistItem;
import org.example.model.ResultItem;
import org.example.model.HistoryTotal;
import org.example.renderer.ChecklistComparator;
import org.example.restapi.ChecklistHistoryApi;
import org.example.restapi.ChecklistItemApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkmax.ui.util.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * ViewModel of Checklist
 *
 * @author huannt14
 */
@Data
public class ListGroupViewModel {

    private static final Logger logger = LoggerFactory.getLogger(ListGroupViewModel.class);

    // api to connect checklist history
    private ChecklistHistoryApi checklistHistoryApi = ChecklistHistoryApi.getInstance();

    // api to connect checklist item
    private ChecklistItemApi checklistItemApi = ChecklistItemApi.getInstance();

    // list groups on checklist table
    private ChecklistGroupsModel checklistGroupsModel;

    // list contains all checklist histories
    private List<ChecklistHistory> checklistHistories = new ArrayList<>();

    // list executed items which is converted from detail field of checklist history
    private List<ResultItem> historyResults = new ArrayList<>();

    private HistoryTotal currentHistory = new HistoryTotal();

    // list contains all checklist items
    private List<ChecklistItem> checklistItems = new ArrayList<>();

    // show history table
    private boolean visibleHistory = false;

    // show checklist table
    private boolean visibleChecklist = true;

    // expand all group in checklist item table
    private boolean isCloseGroup = false;

    /**
     * init value
     */
    @Init
    public void init() {
        // find all checklist items from server
        List<ChecklistItem> checklistItems = checklistItemApi.getAll();
        // find all checklist history from server
        List<ChecklistHistory> checklistHistoriesInit = checklistHistoryApi.getAll();
        if (checklistItems != null) {
            checklistGroupsModel = new ChecklistGroupsModel(checklistItems.toArray(new ChecklistItem[0]), new ChecklistComparator());
            checklistGroupsModel.setMultiple(true);
            this.checklistItems.addAll(checklistItems);
        } else {
            Toast.show("None checklist item!", "warning", null, 2000, true);
        }
        if (checklistHistoriesInit != null) {
            checklistHistories.addAll(checklistHistoriesInit);
        } else {
            Toast.show("None checklist history!", "warning", null, 2000, true);
        }

    }

    /**
     * select checklist group on checklist items table
     *
     * @param data
     */
    @Command("selectGroup")
    public void selectGroup(@BindingParam("data") Object data) {
        if (data instanceof ChecklistGroupsModel.CheckListGroupInfo) {
            ChecklistGroupsModel.CheckListGroupInfo groupInfo = (ChecklistGroupsModel.CheckListGroupInfo) data;
            int groupIndex = groupInfo.getGroupIndex();
            int childCount = checklistGroupsModel.getChildCount(groupIndex);
            boolean added = checklistGroupsModel.isSelected(groupInfo);
            for (int childIndex = 0; childIndex < childCount; childIndex++) {
                ChecklistItem checklistItem = checklistGroupsModel.getChild(groupIndex, childIndex);
                if (added) {
                    checklistGroupsModel.addToSelection(checklistItem);
                } else {
                    checklistGroupsModel.removeFromSelection(checklistItem);
                }
            }
        }
    }

    /**
     * clear all chosen items on checklist items table
     */
    @Command("refresh")
    public void refresh() {
        checklistGroupsModel.clearSelection();
    }

    /**
     * open/ close the group items on checklist item table
     */
    @Command("toggleGroup")
    public void closeAll() {
        if (isCloseGroup) {
            isCloseGroup = false;
            int length = checklistGroupsModel.getGroupCount();
            for (int i = 0; i < length; i++) {
                checklistGroupsModel.setClose(i, false);
            }
        } else {
            isCloseGroup = true;
            int length = checklistGroupsModel.getGroupCount();
            for (int i = 0; i < length; i++) {
                checklistGroupsModel.setClose(i, true);
            }
        }

    }

    /**
     * click to select group on checklist item table
     *
     * @param object Group
     */
    @Command("selectGroupName")
    @NotifyChange("visibleChecklist")
    public void selectGroupName(@BindingParam("data") Object object) {
        if (object instanceof String) {
            checklistGroupsModel.clearSelection();
            for (ChecklistItem ch : checklistItems
            ) {
                Clients.scrollTo(0, 0);
                visibleChecklist = true;
                if (ch.getChecklistGroup().getName().equals(object)) {
                    checklistGroupsModel.addToSelection(ch);
                }
            }
        }
    }

    /**
     * select item on checklist history table
     *
     * @param object
     */
    @Command("selectItem")
    @NotifyChange("visibleChecklist")
    public void selectItem(@BindingParam("data") Object object) {
        if (object instanceof String) {
            for (ChecklistItem ch : checklistItems
            ) {
                Clients.scrollTo(0, 0);
                visibleChecklist = true;
                if (ch.getName().equals(object)) {
                    checklistGroupsModel.clearSelection();
                    checklistGroupsModel.addToSelection(ch);
                    break;
                }
            }
        }
    }

    /**
     * click to execute chosen item on checklist item table
     */
    @Command("execute")
    @NotifyChange({"checklistHistories", "historyResults", "currentHistory", "visibleHistory", "visibleChecklist"})
    public void execute() {
        List<Integer> ids = new ArrayList<>();
        Set<Object> checklistItemList = checklistGroupsModel.getSelection();
        for (Object object : checklistItemList
        ) {
            if (object instanceof ChecklistItem) {
                ChecklistItem checklistItem = (ChecklistItem) object;
                ids.add(checklistItem.getId());
            }
        }
        if (ids.size() > 0) {
            try {
                ChecklistHistory checklistHistory = checklistItemApi.Execute(ids);
                checklistHistories.add(0, checklistHistory);
                convertHistory(checklistHistory.getId());
                Toast.show("Executed. Please view the result.", null, null, 2000, true);
            } catch (Exception ex) {
                logger.error(ex.getMessage());
            }
        } else {
            Toast.show("Please chose at least 1 item", "warning", null, 2000, true);
        }

    }

    /**
     * click to reExecute all times on checklist history
     */
    @Command("reExecute")
    @NotifyChange({"checklistHistories", "historyResults", "currentHistory", "visibleHistory", "visibleChecklist"})
    public void reExecute() {
        List<Integer> ids = new ArrayList<>();
        for (ResultItem hr : historyResults
        ) {
            for (ChecklistItem ch : checklistItems
            ) {
                if (hr.getName().equals(ch.getName())) {
                    ids.add(ch.getId());
                }
            }
        }
        if (ids.size() == 0) {
            Toast.show("None items is selected, Because of the id's item was modify", null, null, 2000, true);
        } else {
            try {
                ChecklistHistory checklistHistory = checklistItemApi.Execute(ids);
                checklistHistories.add(0, checklistHistory);
                convertHistory(checklistHistory.getId());
                Toast.show("Executed. Please view the result.", null, null, 2000, true);
            } catch (Exception ex) {
                logger.error(ex.getMessage());
            }
        }
    }


    /**
     * click to show all executed result of chosen checklist history
     * @param data chosen checklist history
     */
    @Command("showHistory")
    @NotifyChange({"historyResults", "currentHistory", "visibleHistory", "visibleChecklist"})
    public void convertHistory(@BindingParam("data") Object data) {
        if (data instanceof Integer) {
            Clients.scrollTo(0, 0);
            visibleHistory = true;
            visibleChecklist = false;
            int historyId = ((Integer) data).intValue();
            for (ChecklistHistory history : checklistHistories
            ) {
                if (history.getId() == historyId) {
                    String result = history.getResult();
                    int[] convertedResult = convertResultToNumber(result);
                    currentHistory.setPassed(convertedResult[0]);
                    currentHistory.setTotal(convertedResult[1]);
                    String detail = history.getDetail();
                    try {
                        ResultItem[] results = new Gson().fromJson(detail, ResultItem[].class);
                        historyResults = Arrays.asList(results);
                    } catch (Exception ex) {
                        logger.error(ex.getMessage());
                    }
                    break;
                }
            }
        }
    }

    /**
     * disable the executed detail table
     */
    @Command("closeDetailResult")
    @NotifyChange("visibleHistory")
    public void closeDetailResult() {
        visibleHistory = false;
    }

    /**
     * convert the result of checklist history to the number of total checklist items
     * and passed checklist items
     * @param result result field in checklist history
     * @return
     */
    public int[] convertResultToNumber(String result) {
        String[] numbers = result.split("/");
        int[] arrays = new int[2];
        arrays[0] = Integer.parseInt(numbers[0]);
        arrays[1] = Integer.parseInt(numbers[1]);
        return arrays;
    }
}
