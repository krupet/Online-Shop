package ua.com.krupet.jsfbeans.util;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import ua.com.krupet.Order;
import ua.com.krupet.service.OrdersService;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Lazy loading for orders list. There is a lot of code duplication but can`t solve it yet
 *
 * @author krupet
 */
public class LazyOrderDataModel extends LazyDataModel<Order> implements Serializable {

    private OrdersService ordersService;
    private List<Order> datasource;

    public LazyOrderDataModel(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @Override
    public String getRowKey(Order order) {
        return order.getId();
    }

    @Override
    public Order getRowData(String rowKey) {
        for(Order order : datasource) {
            if(order.getId().equals(rowKey)) return order;
        }

        return null;
    }

    @Override
    public List<Order> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,Object> filters) {
        datasource = ordersService.getOrdersList();

        if(sortField != null) {
            Collections.sort(datasource, new LazySorter(sortField, sortOrder));
        }
        setRowCount(datasource.size());
        return datasource;
    }
}
