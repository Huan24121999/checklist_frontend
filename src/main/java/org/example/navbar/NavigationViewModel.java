package org.example.navbar;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;

import java.util.LinkedHashMap;
import java.util.Map;

public class NavigationViewModel {
	NavigationPage currentPage;
	private Map<String, Map<String, NavigationPage>> pageMap;
	
	@Init
	public void init() {
		initPageMap();
		currentPage = pageMap.get("ZK's Pizza").get("About Us");
	}

	@Command
	public void navigatePage(@BindingParam("target") NavigationPage targetPage) {
		BindUtils.postNotifyChange(null, null, currentPage, "selected");
		currentPage = targetPage;
		BindUtils.postNotifyChange(null, null, this, "currentPage");
	}
	
	public NavigationPage getCurrentPage() {
		return currentPage;
	}

	public Map<String, Map<String, NavigationPage>> getPageMap() {
		return pageMap;
	}
	
	private void initPageMap() {
		pageMap = new LinkedHashMap<String, Map<String, NavigationPage>>();
		
		addPage("ZK's Pizza", "CHECK LIST", "/list_item/list_item.zul");
		addPage("ZK's Pizza", "About Us", "/home/about_us.zul");
		addPage("ZK's Pizza", "Menu", "/home/menu.zul");
		addPage("ZK's Pizza", "FAQ", "/home/faq.zul");
		addPage("ZK's Pizza", "Press", "/home/press.zul");

		addPage("Customers", "Active Members", "/customers/customers.zul", "active");
		addPage("Customers", "Inactive Members", "/customers/customers.zul", "inactive");

		addPage("Orders", "In Preparation", "/orders/orders.zul", "in-preparation");
		addPage("Orders", "Ready for Shipping", "/orders/orders.zul", "ready");
		addPage("Orders", "Shipping", "/orders/orders.zul", "shipping");
		addPage("Orders", "Specified for Later", "/orders/orders.zul", "later");
		
		addPage("Fan Service", "Events", "/fan_service/events.zul");
		addPage("Fan Service", "Promotion", "/fan_service/promotion.zul");
	}

	private void addPage(String title, String subTitle, String includeUri) {
		addPage(title, subTitle, includeUri, null);
	}
	
	private void addPage(String title, String subTitle, String includeUri, String data) {
		String folder = "/widgets/menu/navbar/pages";
		Map<String, NavigationPage> subPageMap = pageMap.get(title);
		if(subPageMap == null) {
			subPageMap = new LinkedHashMap<String, NavigationPage>();
			pageMap.put(title, subPageMap);
		}
		NavigationPage navigationPage = new NavigationPage(title, subTitle,
				folder + includeUri + "?random=" + Math.random(), data) {
			@Override
			public boolean isSelected() {
				return currentPage == this;
			}
		};
		subPageMap.put(subTitle, navigationPage);
	}
}
