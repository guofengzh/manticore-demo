# A demo using MyBatis and JPA to access Manticore Search

## Prerequisite
1. OpenJDK 17+
2. set **mysql_version_string = 5.0.37** according to [Notes on MySQL connectors](https://manual.manticoresearch.com/Connecting_to_the_server/MySQL_protocol#Notes-on-MySQL-connectors).

## Create an index in Manticore

Connect to Manticore using the MySQL client.
 
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
mvnw test
```

The test passed.

But you will see the exceptions on the console:
```
java.sql.SQLException: Could not map transaction isolation '<empty>' to a valid JDBC level.
	at com.mysql.cj.jdbc.exceptions.SQLError.createSQLException(SQLError.java:130) ~[mysql-connector-j-8.2.0.jar:8.2.0]
	at com.mysql.cj.jdbc.exceptions.SQLError.createSQLException(SQLError.java:98) ~[mysql-connector-j-8.2.0.jar:8.2.0]
	at com.mysql.cj.jdbc.exceptions.SQLError.createSQLException(SQLError.java:90) ~[mysql-connector-j-8.2.0.jar:8.2.0]
	at com.mysql.cj.jdbc.exceptions.SQLError.createSQLException(SQLError.java:64) ~[mysql-connector-j-8.2.0.jar:8.2.0]
	at com.mysql.cj.jdbc.ConnectionImpl.getTransactionIsolation_aroundBody0(ConnectionImpl.java:1183) ~[mysql-connector-j-8.2.0.jar:8.2.0]
	at com.mysql.cj.jdbc.ConnectionImpl$AjcClosure1.run(ConnectionImpl.java:1) ~[mysql-connector-j-8.2.0.jar:8.2.0]
	at org.aspectj.runtime.reflect.JoinPointImpl.proceed(JoinPointImpl.java:179) ~[aspectjweaver-1.9.20.1.jar:?]
	at com.example.manticore.aspect.GetTransactionIsolationAspect.aroundAdvice(GetTransactionIsolationAspect.java:25) ~[classes/:?]
	at com.mysql.cj.jdbc.ConnectionImpl.getTransactionIsolation(ConnectionImpl.java:1171) ~[mysql-connector-j-8.2.0.jar:8.2.0]
	at com.zaxxer.hikari.pool.PoolBase.checkDefaultIsolation(PoolBase.java:480) ~[HikariCP-5.0.1.jar:?]
    ...
```
This is because [HikariCP](https://github.com/brettwooldridge/HikariCP#gear-configuration-knobs-baby) want to get the default transaction isolation level from Manticore but Manticore response with the wrong string value. Ref: [1618](https://github.com/manticoresoftware/manticoresearch/issues/1618#issuecomment-1825929815).

This has been described in [Notes on MySQL connectors](https://manual.manticoresearch.com/Connecting_to_the_server/MySQL_protocol#Notes-on-MySQL-connectors)
> ...as the connector can try running certain SQL commands not implemented yet in Manticore.

To work arround with this issue, AspectJ is used to capture the excetion and return an arbitrary isolation level for Manticore to work with HikariCP.

