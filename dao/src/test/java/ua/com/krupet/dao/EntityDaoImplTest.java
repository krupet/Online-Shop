package ua.com.krupet.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ua.com.krupet.BaseDaoTest;
import ua.com.krupet.Order;
import ua.com.krupet.OrderStatus;
import ua.com.krupet.RoleTypes;
import ua.com.krupet.entity.OrderEntity;
import ua.com.krupet.entity.ProductEntity;
import ua.com.krupet.entity.RoleEntity;
import ua.com.krupet.entity.UserEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by krupet on 04.07.2015.
 */
public class EntityDaoImplTest extends BaseDaoTest{

    @Autowired
    private SessionFactory sessionFactory;

    @Test
    @Transactional
    public void addOrderToUserTest() {
        Session session = sessionFactory.getCurrentSession();

        UserEntity userEntity = (UserEntity) session.get(UserEntity.class, 1L);
        List<OrderEntity> orderEntities = userEntity.getOrders();
        OrderEntity orderEntity1 = new OrderEntity(new Date().getTime(), OrderStatus.PREPARING, userEntity);
        OrderEntity orderEntity2 = new OrderEntity(new Date().getTime(), OrderStatus.PREPARING, userEntity);
        OrderEntity orderEntity3 = new OrderEntity(new Date().getTime(), OrderStatus.PREPARING, userEntity);

        orderEntities.add(orderEntity1);
        orderEntities.add(orderEntity2);
        orderEntities.add(orderEntity3);
        userEntity.setOrders(orderEntities);

        session.update(userEntity);
        session.save(orderEntity1);
        session.save(orderEntity2);
        session.save(orderEntity3);
        session.flush();
    }

    /*
        Hardcoded test
     */
    @Test
    @Transactional
    public void AddProductsIaAOrderTest() {
        Session session = sessionFactory.getCurrentSession();

        OrderEntity orderEntity = (OrderEntity) session.get(OrderEntity.class, 2L);
        ProductEntity productEntity1 = (ProductEntity) session.get(ProductEntity.class, 1L);
        ProductEntity productEntity4 = (ProductEntity) session.get(ProductEntity.class, 1L);
        ProductEntity productEntity2 = (ProductEntity) session.get(ProductEntity.class, 2L);
        ProductEntity productEntity3 = (ProductEntity) session.get(ProductEntity.class, 3L);

        orderEntity.getProductIDList().add(productEntity1);
        orderEntity.getProductIDList().add(productEntity2);
        orderEntity.getProductIDList().add(productEntity3);
        orderEntity.getProductIDList().add(productEntity4);

        session.update(orderEntity);
        session.flush();

    }
}
