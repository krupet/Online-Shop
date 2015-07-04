package ua.com.krupet;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.com.krupet.config.TestHibernateOrmConfig;

/**
 * Created by krupet on 04.07.2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestHibernateOrmConfig.class)
public class BaseDaoTest {
}
