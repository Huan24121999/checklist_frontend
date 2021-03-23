package org.example.ecommerce.converter;



import org.example.menu.util.BsColor;
import org.zkoss.bind.BindContext;
import org.zkoss.bind.Converter;
import org.zkoss.zk.ui.Component;

/**
 * Convert integer to a bootstrap color
 */
public class IntColorConverter implements Converter<String, Integer, Component> {

    @Override
    public String coerceToUi(Integer index, Component component, BindContext bindContext) {
        return BsColor.values()[index % BsColor.values().length].getCssClass();
    }

    @Override
    public Integer coerceToBean(String s, Component component, BindContext bindContext) {
        //no need in our case
        return null;
    }
}
