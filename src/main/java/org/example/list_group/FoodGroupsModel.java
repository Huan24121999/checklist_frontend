package org.example.list_group;


import org.example.data.pojo.Food;
import org.zkoss.zul.GroupsModelArray;

import java.util.Comparator;

public class FoodGroupsModel extends GroupsModelArray<Food, FoodGroupsModel.FoodGroupInfo, Object, Object> {
	private static final long serialVersionUID = 1L;

	public FoodGroupsModel(Food[] data, Comparator<Food> cmpr) {
		super(data, cmpr);
	}

	protected FoodGroupInfo createGroupHead(Food[] groupdata, int index, int col) {
		return new FoodGroupInfo(groupdata[0], index, col);
	}

	protected Object createGroupFoot(Food[] groupdata, int index, int col) {
		// Return the sum number of each group
		return groupdata.length;
	}
	
	public static class FoodGroupInfo {
		private Food firstChild;
		private int groupIndex;
		private int colIndex;
		
		public FoodGroupInfo(Food firstChild, int groupIndex, int colIndex) {
			super();
			this.firstChild = firstChild;
			this.groupIndex = groupIndex;
			this.colIndex = colIndex;
		}
		
		public Food getFirstChild() {
			return firstChild;
		}
		public int getGroupIndex() {
			return groupIndex;
		}
		public int getColIndex() {
			return colIndex;
		}
	}
}
