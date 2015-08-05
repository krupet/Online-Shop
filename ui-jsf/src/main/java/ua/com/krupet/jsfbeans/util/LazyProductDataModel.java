package ua.com.krupet.jsfbeans.util;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import ua.com.krupet.Product;
import ua.com.krupet.service.ProductService;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Lazy loading for products list. There is a lot of code duplication but can`t solve it yet
 *
 * @author krupet
 */
public class LazyProductDataModel extends LazyDataModel<Product> implements Serializable {

    private ProductService productService;

    private List<Product> datasource;
    /*
        Total row number
        without this thing <p:dataTable> ... wil gain "No record found"
        and also if don`t specify rows attribute it will not render rows!
         JSF, again WTF???
     */
    private int rowCount;

    public LazyProductDataModel(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public String getRowKey(Product product) {
        return product.getId();
    }

    @Override
    public Product getRowData(String rowKey) {
        for(Product product : datasource) {
            if(product.getId().equals(rowKey)) return product;
        }

        return null;
    }

    @Override
    public List<Product> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,Object> filters) {
        datasource = productService.getProductsList();

        // if sort field is not null then we sort the field according to sortField and sortOrder parameter
        if(sortField != null) {
            Collections.sort(datasource, new LazySorter(sortField, sortOrder));
        }
        setRowCount(datasource.size());
        return datasource;
    }
}
