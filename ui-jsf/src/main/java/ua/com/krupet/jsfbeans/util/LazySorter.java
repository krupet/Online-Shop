package ua.com.krupet.jsfbeans.util;

import org.primefaces.model.SortOrder;

import java.lang.reflect.Field;
import java.util.Comparator;

/**
 * Utility class for "generic" sorting by some parameter
 * took from: http://www.primefaces.org/showcase/ui/data/datatable/lazy.xhtml
 */
public class LazySorter<T>  implements Comparator<T>{

    private String sortField;
    private SortOrder sortOrder;

    /**
     * @param sortField - sorting parameter (field of entity)
     * @param sortOrder - asc or desc sorting order
     */
    public LazySorter(String sortField, SortOrder sortOrder) {
        this.sortField = sortField;
        this.sortOrder = sortOrder;
    }

    /**
     * sorting using reflection
     */
    @Override
    public int compare(T object1, T object2) {
        try {
            Field field1 = object1.getClass().getDeclaredField(this.sortField);
            Field field2 = object2.getClass().getDeclaredField(this.sortField);
            field1.setAccessible(true);
            field2.setAccessible(true);
            Object value1 = field1.get(object1);
            Object value2 = field2.get(object2);

            int value = ((Comparable)value1).compareTo(value2);
            return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
        }
        catch(Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
