package ua.com.krupet.jsfbeans.util;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import ua.com.krupet.User;
import ua.com.krupet.service.UserService;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by krupet on 7/26/15.
 */
public class LazyUserDataModel extends LazyDataModel<User> implements Serializable {

    private UserService userService;
    private List<User> datasource;
    private int rowCount;

    public LazyUserDataModel(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String getRowKey(User user) {
        return user.getId();
    }

    @Override
    public User getRowData(String rowKey) {
        for(User user : datasource) {
            if(user.getId().equals(rowKey)) return user;
        }

        return null;
    }

    @Override
    public List<User> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,Object> filters) {
        datasource = userService.getUsersList();

        if(sortField != null) {
            Collections.sort(datasource, new LazySorter(sortField, sortOrder));
        }
        setRowCount(datasource.size());
        return datasource;
    }
}
