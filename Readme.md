### 四、JPA的基本操作
JPA操作的步骤：
* 加载配置文件创建实体管理器工厂
    * Persisitence：静态方法（根据持久化单元名称创建实体管理器工厂）
        * createEntityManagerFactory（持久化单元名称）
* 根据实体管理器工厂，创建实体管理器
    * EntityManagerFactory：获取EntityManager对象
        * 方法：createEntityManager
            * 内部维护很多内容：
                        数据库信息、
                        缓存信息、
                        所有的实体管理器对象、
                        再创建EntityManagerFactory的过程中会根据配置创建数据库表
            * EntityManagerFactory的创建过程比较浪费资源
                    特点：线程安全的对象
                        多个线程访问同一个EntityManagerFactory不会有线程安全问题
                    如何解决EntityManagerFactory的创建过程浪费资源（耗时）问题？
                        思路：创建一个公共的EntityManagerFactory对象
                        借助静态代码块的形式创建EntityManagerFactory
* 创建事务对象，开启事务
    * EntityManager对象：实体类管理器（JPA操作的核心）
            beginTransaction：创建事务对象
            presist：保存
            merge：更新
            remove：删除
            find/getRefrence：根据Id查询
    * Transaction对象：事务
            begin：开启事务
            commit：提交事务
            rollback：回滚事务
* 增删改查操作
* 提交事务
* 释放资源