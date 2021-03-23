package org.example.ecommerce.converter;


import org.example.ecommerce.dao.Type;
import org.example.menu.util.BsColor;
import org.zkoss.bind.BindContext;
import org.zkoss.bind.Converter;
import org.zkoss.zk.ui.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Convert {@link org.example.ecommerce.dao.Type} to a font awesome icon class
 */
public class TypeColorConverter implements Converter<String, Type, Component> {
    static private Map<Type, String> borderColorMap = new HashMap() {{
        put(Type.Customer, BsColor.PRIMARY.getCssClass());
        put(Type.Order, BsColor.WARNING.getCssClass());
        put(Type.Task, BsColor.DANGER.getCssClass());
        put(Type.Request, BsColor.SUCCESS.getCssClass());
    }};

    @Override
    public String coerceToUi(Type type, Component component, BindContext bindContext) {
        return borderColorMap.get(type);
    }

    @Override
    public Type coerceToBean(String s, Component component, BindContext bindContext) {
        //no need in our case
        return null;
    }
}
