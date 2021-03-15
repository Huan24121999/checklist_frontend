package org.example.grouping_model;

import org.example.data.pojo.Food;
import org.zkoss.zul.GroupComparator;

import java.io.Serializable;
import java.util.Comparator;

public class FoodComparator implements Comparator<Food>, GroupComparator<Food>, Serializable {
	private static final long serialVersionUID = 1L;

	public int compare(Food o1, Food o2) {
		return o1.getCategory().compareTo(o2.getCategory().toString());
	}

	public int compareGroup(Food o1, Food o2) {
		if(o1.getCategory().equals(o2.getCategory()))
			return 0;
		else
			return 1;
	}
}
