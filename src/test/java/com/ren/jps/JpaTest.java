package com.ren.jps;

import com.ren.domain.Customer;
import com.ren.utils.JpaUtils;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * @author : renjiahui
 * @date : 2020/11/15 18:21
 * @desc :
 */
public class JpaTest {

    /**
     * JPA操作步骤
     *  1、加载配置文件创建工厂（实体管理类工厂）对象
     *  2、通过尸体管理类工厂获取实体管理器
     *  3、获取事务对象，开启事务
     *  4、完成增删改查操作
     *  5、提交事务（回滚事务）
     *  6、释放资源
     */
    @Test
    public void save() {
        //1、加载配置文件创建工厂（实体管理类工厂）对象
        EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("myJpa");

        //2、通过尸体管理类工厂获取实体管理器
        EntityManager entityManager = managerFactory.createEntityManager();

        //3、获取事务对象，开启事务
        EntityTransaction transaction = entityManager.getTransaction();
        //开启事务
        transaction.begin();

        Customer customer = new Customer();
        customer.setCustName("renjiahui");
        customer.setCustLevel("1");
        customer.setCustSource("自己");
        customer.setCustAddress("M78星云");
        customer.setCustPhone("18595607717");

        //保存
        entityManager.persist(customer);

        //提交事务
        transaction.commit();

        //释放资源
        entityManager.close();
        managerFactory.close();
    }

    @Test
    public void testUtil() {
        EntityManager entityManager = JpaUtils.getEntityManager();

        //获取事务对象，开启事务
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        //进行增删改查操作
        Customer customer = new Customer();
        customer.setCustName("234");
        customer.setCustAddress("123");
        entityManager.persist(customer);

        //提交事务
        transaction.commit();

        //释放资源
        entityManager.close();
    }

    /**
     * 根据ID查询客户
     * find方法查询：
     *  1、查询的对象就是当前客户对象本身
     *  2、在调用find方法的时候，就会发送sql语句查询数据库
     *
     * 立即加载
     */
    @Test
    public void testFind() {
        //1、通过工具类获取entityManager
        EntityManager entityManager = JpaUtils.getEntityManager();

        //2、开启事务
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        //3、增删改查
        /**
         * find: 根据id查询数据
         *      class：查询数据的结果需要包装的实体类类型的字节码
         *      id：查询的主键的取值
         */
        Customer customer = entityManager.find(Customer.class, 1);
        System.out.println(customer);


        //4、提交事务
        transaction.commit();

        //5、释放资源
        entityManager.close();
    }

    /**
     * 根据ID查询客户
     * getReference查询方法：
     *  1、获取的对象时一个动态代理对象
     *  2、调用getReference方法不会立即发送sql语句查询数据库
     *      当调用查询结果对象的时候，才会发送查询的sql语音
     *      什么时候用，什么时候发送sql语句查询数据库
     *
     *  延迟加载（懒加载）
     *      得到的时一个动态代理对象
     *      什么时候用，什么时候才会进行查询
     */
    @Test
    public void testReference() {
        //1、通过工具类获取entityManager
        EntityManager entityManager = JpaUtils.getEntityManager();

        //2、开启事务
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        //3、增删改查
        /**
         * getReference: 根据id查询数据
         *      class：查询数据的结果需要包装的实体类类型的字节码
         *      id：查询的主键的取值
         */
        Customer customer = entityManager.getReference(Customer.class, 1);
        System.out.println(customer);


        //4、提交事务
        transaction.commit();

        //5、释放资源
        entityManager.close();
    }

    /**
     * 删除客户
     */
    @Test
    public void testRemove() {
        //1、通过工具类获取entityManager
        EntityManager entityManager = JpaUtils.getEntityManager();

        //2、开启事务
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        //3、增删改查
        /**
         * 1、根据id查询客户
         * 2、调用remove方法完成删除操作
         */
        Customer customer = entityManager.find(Customer.class, 1);
        entityManager.remove(customer);

        //4、提交事务
        transaction.commit();

        //5、释放资源
        entityManager.close();
    }

    /**
     * 更新客户操作
     */
    @Test
    public void testUpdate() {
        //1、通过工具类获取entityManager
        EntityManager entityManager = JpaUtils.getEntityManager();

        //2、开启事务
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        //3、增删改查--更新操作
        /**
         * 1、根据id查询客户
         * 2、调用merge方法完成更新操作
         */
        Customer customer = entityManager.find(Customer.class, 1);
        customer.setCustAddress("00000000");
        entityManager.merge(customer);

        //4、提交事务
        transaction.commit();

        //5、释放资源
        entityManager.close();
    }

    /**
     * 查询全部
     */
    @Test
    public void testFindAll() {
        //1、通过工具类获取entityManager
        EntityManager entityManager = JpaUtils.getEntityManager();

        //2、开启事务
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        //3、增删改查--更新操作
        /**
         * 1、根据id查询客户
         * 2、调用merge方法完成更新操作
         */
        Customer customer = entityManager.(Customer.class, 1);
        customer.setCustAddress("00000000");
        entityManager.merge(customer);

        //4、提交事务
        transaction.commit();

        //5、释放资源
        entityManager.close();
    }
}
