# A demo using MyBatis and JPA to access Manticore Search

Because certain MySQL wire protocols have not been implemented by Manticore, some Java data source pool manager, like [HikariCP](https://github.com/brettwooldridge/HikariCP#gear-configuration-knobs-baby), cannot be used with Manticore. In this demo app, we use MyBatis' PooledDataSource, which is configured in DataSourceConfig.java.

## Preparation

#### Connect to Manticore using the MySQL client
 
Run 
```
mysql -P9306 -h127.0.0.1
```

Execute the following SQL statement to create the index 'testrt'.
```
CREATE TABLE testrt (title TEXT, content TEXT, gid INT);
```

## Run the test

```
mvn test
```

The test passed.

## Using the HikariCP datasource

Swtich to use HikariCP in DataSourceConfig.java:
```
    @Bean
    public DataSource dataSource(){
        //return createMyBatisPooledDataSource();
        return createHikariDataSource();
    }

```

run the test again:

```
mvn test
```

Then the following exceptions displayed:

``` 
Caused by: java.sql.SQLException: Could not map transaction isolation '<empty>' to a valid JDBC level.
	at com.mysql.cj.jdbc.exceptions.SQLError.createSQLException(SQLError.java:130) ~[mysql-connector-j-8.2.0.jar:8.2.0]
	at com.mysql.cj.jdbc.exceptions.SQLError.createSQLException(SQLError.java:98) ~[mysql-connector-j-8.2.0.jar:8.2.0]
	at com.mysql.cj.jdbc.exceptions.SQLError.createSQLException(SQLError.java:90) ~[mysql-connector-j-8.2.0.jar:8.2.0]
	at com.mysql.cj.jdbc.exceptions.SQLError.createSQLException(SQLError.java:64) ~[mysql-connector-j-8.2.0.jar:8.2.0]
	at com.mysql.cj.jdbc.ConnectionImpl.getTransactionIsolation(ConnectionImpl.java:1183) ~[mysql-connector-j-8.2.0.jar:8.2.0]
	at com.zaxxer.hikari.pool.PoolBase.checkDefaultIsolation(PoolBase.java:480) ~[HikariCP-5.0.1.jar:?]
	at com.zaxxer.hikari.pool.PoolBase.checkDriverSupport(PoolBase.java:443) ~[HikariCP-5.0.1.jar:?]
	at com.zaxxer.hikari.pool.PoolBase.setupConnection(PoolBase.java:411) ~[HikariCP-5.0.1.jar:?]
	at com.zaxxer.hikari.pool.PoolBase.newConnection(PoolBase.java:364) ~[HikariCP-5.0.1.jar:?]
	at com.zaxxer.hikari.pool.PoolBase.newPoolEntry(PoolBase.java:201) ~[HikariCP-5.0.1.jar:?]
	at com.zaxxer.hikari.pool.HikariPool.createPoolEntry(HikariPool.java:470) ~[HikariCP-5.0.1.jar:?]
	at com.zaxxer.hikari.pool.HikariPool.checkFailFast(HikariPool.java:561) ~[HikariCP-5.0.1.jar:?]
	at com.zaxxer.hikari.pool.HikariPool.<init>(HikariPool.java:100) ~[HikariCP-5.0.1.jar:?]
	at com.zaxxer.hikari.HikariDataSource.<init>(HikariDataSource.java:81) ~[HikariCP-5.0.1.jar:?]
	at com.example.manticore.DataSourceConfig.createHikariDataSource(DataSourceConfig.java:37) ~[classes/:?]
	at com.example.manticore.DataSourceConfig.dataSource(DataSourceConfig.java:22) ~[classes/:?]
	at com.example.manticore.DataSourceConfig$$SpringCGLIB$$0.CGLIB$dataSource$0(<generated>) ~[classes/:?]
	at com.example.manticore.DataSourceConfig$$SpringCGLIB$$2.invoke(<generated>) ~[classes/:?]
	at org.springframework.cglib.proxy.MethodProxy.invokeSuper(MethodProxy.java:258) ~[spring-core-6.0.11.jar:6.0.11]
	at org.springframework.context.annotation.ConfigurationClassEnhancer$BeanMethodInterceptor.intercept(ConfigurationClassEnhancer.java:331) ~[spring-context-6.0.11.jar:6.0.11]
	at com.example.manticore.DataSourceConfig$$SpringCGLIB$$0.dataSource(<generated>) ~[classes/:?]
	at jdk.internal.reflect.DirectMethodHandleAccessor.invoke(DirectMethodHandleAccessor.java:103) ~[?:?]
	at java.lang.reflect.Method.invoke(Method.java:580) ~[?:?]
	at org.springframework.beans.factory.support.SimpleInstantiationStrategy.instantiate(SimpleInstantiationStrategy.java:139) ~[spring-beans-6.0.11.jar:6.0.11]
	at org.springframework.beans.factory.support.ConstructorResolver.instantiate(ConstructorResolver.java:655) ~[spring-beans-6.0.11.jar:6.0.11]
	at org.springframework.beans.factory.support.ConstructorResolver.instantiateUsingFactoryMethod(ConstructorResolver.java:493) ~[spring-beans-6.0.11.jar:6.0.11]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.instantiateUsingFactoryMethod(AbstractAutowireCapableBeanFactory.java:1332) ~[spring-beans-6.0.11.jar:6.0.11]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBeanInstance(AbstractAutowireCapableBeanFactory.java:1162) ~[spring-beans-6.0.11.jar:6.0.11]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:560) ~[spring-beans-6.0.11.jar:6.0.11]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:520) ~[spring-beans-6.0.11.jar:6.0.11]
	at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:326) ~[spring-beans-6.0.11.jar:6.0.11]
	at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:234) ~[spring-beans-6.0.11.jar:6.0.11]
	at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:324) ~[spring-beans-6.0.11.jar:6.0.11]
	at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:200) ~[spring-beans-6.0.11.jar:6.0.11]
	at org.springframework.beans.factory.config.DependencyDescriptor.resolveCandidate(DependencyDescriptor.java:254) ~[spring-beans-6.0.11.jar:6.0.11]
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.doResolveDependency(DefaultListableBeanFactory.java:1417) ~[spring-beans-6.0.11.jar:6.0.11]
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.resolveDependency(DefaultListableBeanFactory.java:1337) ~[spring-beans-6.0.11.jar:6.0.11]
	at org.springframework.beans.factory.support.ConstructorResolver.resolveAutowiredArgument(ConstructorResolver.java:888) ~[spring-beans-6.0.11.jar:6.0.11]
	at org.springframework.beans.factory.support.ConstructorResolver.createArgumentArray(ConstructorResolver.java:791) ~[spring-beans-6.0.11.jar:6.0.11]
	at org.springframework.beans.factory.support.ConstructorResolver.instantiateUsingFactoryMethod(ConstructorResolver.java:550) ~[spring-beans-6.0.11.jar:6.0.11]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.instantiateUsingFactoryMethod(AbstractAutowireCapableBeanFactory.java:1332) ~[spring-beans-6.0.11.jar:6.0.11]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBeanInstance(AbstractAutowireCapableBeanFactory.java:1162) ~[spring-beans-6.0.11.jar:6.0.11]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:560) ~[spring-beans-6.0.11.jar:6.0.11]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:520) ~[spring-beans-6.0.11.jar:6.0.11]
	at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:326) ~[spring-beans-6.0.11.jar:6.0.11]
	at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:234) ~[spring-beans-6.0.11.jar:6.0.11]
	at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:324) ~[spring-beans-6.0.11.jar:6.0.11]
	at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:200) ~[spring-beans-6.0.11.jar:6.0.11]
	at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:313) ~[spring-beans-6.0.11.jar:6.0.11]
	at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:200) ~[spring-beans-6.0.11.jar:6.0.11]
	at org.springframework.context.support.AbstractApplicationContext.getBean(AbstractApplicationContext.java:1155) ~[spring-context-6.0.11.jar:6.0.11]
	at org.springframework.context.support.AbstractApplicationContext.finishBeanFactoryInitialization(AbstractApplicationContext.java:932) ~[spring-context-6.0.11.jar:6.0.11]
	at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:608) ~[spring-context-6.0.11.jar:6.0.11]
	at org.springframework.boot.SpringApplication.refresh(SpringApplication.java:734) ~[spring-boot-3.1.3.jar:3.1.3]
	at org.springframework.boot.SpringApplication.refreshContext(SpringApplication.java:436) ~[spring-boot-3.1.3.jar:3.1.3]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:312) ~[spring-boot-3.1.3.jar:3.1.3]
	at org.springframework.boot.test.context.SpringBootContextLoader.lambda$loadContext$3(SpringBootContextLoader.java:137) ~[spring-boot-test-3.1.3.jar:3.1.3]
	at org.springframework.util.function.ThrowingSupplier.get(ThrowingSupplier.java:58) ~[spring-core-6.0.11.jar:6.0.11]
	at org.springframework.util.function.ThrowingSupplier.get(ThrowingSupplier.java:46) ~[spring-core-6.0.11.jar:6.0.11]
	at org.springframework.boot.SpringApplication.withHook(SpringApplication.java:1406) ~[spring-boot-3.1.3.jar:3.1.3]
	at org.springframework.boot.test.context.SpringBootContextLoader$ContextLoaderHook.run(SpringBootContextLoader.java:545) ~[spring-boot-test-3.1.3.jar:3.1.3]
	at org.springframework.boot.test.context.SpringBootContextLoader.loadContext(SpringBootContextLoader.java:137) ~[spring-boot-test-3.1.3.jar:3.1.3]
	at org.springframework.boot.test.context.SpringBootContextLoader.loadContext(SpringBootContextLoader.java:108) ~[spring-boot-test-3.1.3.jar:3.1.3]
	at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContextInternal(DefaultCacheAwareContextLoaderDelegate.java:187) ~[spring-test-6.0.11.jar:6.0.11]
	at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:119) ~[spring-test-6.0.11.jar:6.0.11]
	... 72 more
```
HikariCP datasource pool tries to get the transaction isolation before it makes a connection to Manticore.

The reason has been described in [Notes on MySQL connectors](https://manual.manticoresearch.com/Connecting_to_the_server/MySQL_protocol?edit#Notes-on-MySQL-connectors)
> ...as the connector can try running certain SQL commands not implemented yet in Manticore.

