package ua.com.krupet.jsfbeans.util;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Custom CreationDate converter created because of some mistake with DTO implementation
 * My bad because I defined field creation date as String (it holds string representation of long number),
 * because in that moment I didn't realize that I could define it as Long
 * So yes, this is a "spike"
 *
 * @author krupet
 * @see ua.com.krupet.Product#creationDate
 */
@FacesConverter("ua.com.krupet.jsfbeans.util.CreationDateConverter")
public class CreationDateConverter implements Converter {

    /**
     * Don't use this method
     */
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {

        return null;
    }

    /**
     * Converts String creationDate in formatted string date
     */
    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {

        Date date = new Date(Long.parseLong((String)o));
        Format format = new SimpleDateFormat("dd / MM / yyyy  HH:mm:ss");
//        Format format = new SimpleDateFormat("dd.MM.yyyy");
        return format.format(date);
    }
}