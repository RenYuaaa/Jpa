package com.ren.jps;

import com.ren.domain.Customer;
import com.ren.utils.JpaUtils;
import org.junit.Test;

import javax.persistence.*;
import java.util.List;

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

        //3、增删改查--查询全部
        String sql = "from com.ren.domain.Customer";
        Query query = entityManager.createQuery(sql);

        //发送查询，并封装结果集
        List list = query.getResultList();
        list.forEach(System.out::println);

        //4、提交事务
        transaction.commit();

        //5、释放资源
        entityManager.close();
    }

    /**
     * 排序查询
     *
     * 进行jpql查询
     *  1、创建query查询对象
     *  2、对参数进行赋值
     *  3、查询、并得到返回结果
     */
    @Test
    public void testOrders() {
        //1、通过工具类获取entityManager
        EntityManager entityManager = JpaUtils.getEntityManager();

        //2、开启事务
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        //3、增删改查--排序查询
        String sql = "from Customer order by id desc";

        //创建Query查询对象，query对象才是执行sql的对象
        Query query = entityManager.createQuery(sql);

        //发送查询，并封装结果集
        List list = query.getResultList();
        list.forEach(System.out::println);

        //4、提交事务
        transaction.commit();

        //5、释放资源
        entityManager.close();
    }

    /**
     * 查询客户总人数
     */
    @Test
    public void testCount() {
        //1、通过工具类获取entityManager
        EntityManager entityManager = JpaUtils.getEntityManager();

        //2、开启事务
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        //3、增删改查--排序查询
        String sql = "select count(id) from Customer";

        //创建Query查询对象，query对象才是执行sql的对象
        Query query = entityManager.createQuery(sql);

        /**
         * getResultLis：直接将查询结果封装为List集合
         * getSingleResult：得到唯一的结果集
         */
        Object singleResult = query.getSingleResult();
        System.out.println(singleResult);

        //4、提交事务
        transaction.commit();

        //5、释放资源
        entityManager.close();
    }

    /**
     * 分页查询
     */
    @Test
    public void testPaged() {
        //1、通过工具类获取entityManager
        EntityManager entityManager = JpaUtils.getEntityManager();

        //2、开启事务
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        //3、增删改查--分页查询
        String sql = "from Customer";

        //创建Query查询对象，query对象才是执行sql的对象
        Query query = entityManager.createQuery(sql);

        //起始索引
        query.setFirstResult(1);

        //每页查询条数
        query.setMaxResults(1);

        //发送查询，并封装结果集
        List list = query.getResultList();
        list.forEach(System.out::println);

        //4、提交事务
        transaction.commit();

        //5、释放资源
        entityManager.close();
    }

    /**
     * 条件查询
     */
    @Test
    public void testCondition() {
        //1、通过工具类获取entityManager
        EntityManager entityManager = JpaUtils.getEntityManager();

        //2、开启事务
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        //3、增删改查--条件查询
        //占位符要标记为：?1、?2这种形式
        String sql = "from Customer where custName like ?1";

        //创建Query查询对象，query对象才是执行sql的对象
        Query query = entityManager.createQuery(sql);

        //对参数赋值--占位符参数
        //第一个参数，占位符对索引位置（从1开始），第二个参数，取值
        query.setParameter(1, "renjiahui%");


        //发送查询，并封装结果集
        List list = query.getResultList();
        list.forEach(System.out::println);

        //4、提交事务
        transaction.commit();

        //5、释放资源
        entityManager.close();
    }
}
