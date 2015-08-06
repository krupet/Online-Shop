package ua.com.krupet.jsfbeans.util;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * Created by krupet on 8/6/15.
 */
@FacesConverter("ua.com.krupet.jsfbeans.util.RoleTypeConverter")
public class RoleTypeConverter implements Converter{
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {

        return ((String) o).replaceFirst("ROLE_", "");
    }
}
