package org.example.list_group;

import org.example.data.pojo.Food;
import org.zkoss.zul.*;

public class FoodListGroupRenderer implements ListitemRenderer<Object> {

	@Override
	public void render(Listitem listitem, Object obj, int index) throws Exception {

		if (listitem instanceof Listgroup) {
			FoodGroupsModel.FoodGroupInfo groupInfo = (FoodGroupsModel.FoodGroupInfo) obj;
			Food food = groupInfo.getFirstChild();
			String groupTxt;
			System.out.println(groupInfo.getGroupIndex());
			System.out.println(groupInfo.getColIndex());
			System.out.println(groupInfo.getFirstChild().toString());
			switch (groupInfo.getColIndex()) {
			case 0:
				groupTxt = food.getCategory();
				break;
			case 1:
				groupTxt = food.getName();
				break;
			case 2:
				groupTxt = food.getTopNutrients();
				break;
			case 3:
				groupTxt = food.getDailyPercent().toString();
				break;
			case 4:
				groupTxt = food.getCalories().toString();
				break;
			case 5:
				groupTxt = food.getQuantity();
				break;
			default:
				groupTxt = food.getCategory();
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
			Food data = (Food) obj;
			listitem.appendChild(new Listcell(data.getCategory()));
			listitem.appendChild(new Listcell(data.getName()));
			listitem.appendChild(new Listcell(data.getTopNutrients()));
			listitem.appendChild(new Listcell(data.getDailyPercent() + ""));
			listitem.appendChild(new Listcell(data.getCalories() + ""));
			listitem.appendChild(new Listcell(data.getQuantity()));
			listitem.setValue(data);
		}

	}

}
