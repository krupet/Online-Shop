package ua.com.krupet.jsfbeans;

import org.primefaces.model.LazyDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import ua.com.krupet.Order;
import ua.com.krupet.jsfbeans.util.LazyOrderDataModel;
import ua.com.krupet.service.OrdersService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * Created by krupet on 7/30/15.
 */
@ManagedBean
@SessionScoped
public class OrderBean {

    @Autowired
    private OrdersService ordersService;
    private LazyDataModel<Order> lazyDataModel;

    @PostConstruct
    public void init() {
        lazyDataModel = new LazyOrderDataModel(ordersService);
    }

    public LazyDataModel<Order> getLazyDataModel() {
        return lazyDataModel;
    }
}
