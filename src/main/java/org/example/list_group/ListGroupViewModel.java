package org.example.list_group;


import org.example.data.FoodData;
import org.example.data.pojo.Food;
import org.example.grouping_model.FoodComparator;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;

public class ListGroupViewModel {

	private FoodGroupsModel groupModel;
	
	@Init
	public void init() {
		groupModel = new FoodGroupsModel(FoodData.getAllFoodsArray(), new FoodComparator());
		groupModel.setMultiple(true);
	}

	public FoodGroupsModel getGroupModel() {
		return groupModel;
	}

	
	@Command("selectGroup")
	public void selectGroup(@BindingParam("data") Object data) {
		if(data instanceof FoodGroupsModel.FoodGroupInfo) {
			FoodGroupsModel.FoodGroupInfo groupInfo = (FoodGroupsModel.FoodGroupInfo)data;
			int groupIndex = groupInfo.getGroupIndex() ;
			int childCount = groupModel.getChildCount(groupIndex);
			boolean added = groupModel.isSelected(groupInfo);
			for(int childIndex = 0; childIndex < childCount; childIndex++) {
				Food food = groupModel.getChild(groupIndex, childIndex);
				if(added) {
					groupModel.addToSelection(food);
				} else {
					groupModel.removeFromSelection(food);
				}
			}
		}
	}
}
