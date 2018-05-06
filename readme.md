### springboot1-demo

- 启动mysql
`mysql.server start`

- 启动redis
` redis-server &`

- 启动mongo
`mongod -f /usr/local/etc/mongod.conf`

- 打jar包，并运行
```
    mvn clean package  -Dmaven.test.skip=true
    nohup java -jar target/spring-boot-scheduler-1.0.0.jar &
    java -Xms10m -Xmx80m -jar target/springboot1-demo-0.0.1-SNAPSHOT.jar --spring.profiles.active=dev
```

- 打war包，并运行
```
    1、修改pom文件中打包方式为 <packaging>war</packaging>
    2、排除tomcat：
    <dependency>
    	<groupId>org.springframework.boot</groupId>
    	<artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
    	<groupId>org.springframework.boot</groupId>
    	<artifactId>spring-boot-starter-tomcat</artifactId>
    	<scope>provided</scope>
    </dependency>
    3、创建ServletInitializer.java，继承SpringBootServletInitializer ，覆盖configure()，把启动类Application注册进去。外部web应用服务器构建Web Application Context的时候，会把启动类添加进去。
     public class ServletInitializer extends SpringBootServletInitializer {
         @Override
         protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
             return application.sources(Application.class);
         }
     }
     4、mvn clean package  -Dmaven.test.skip=true
```

- maven插件中配置可执行
```
    <plugin>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-maven-plugin</artifactId>
        <configuration>
            <executable>true</executable>
        </configuration>
    </plugin>
    
    ./yourapp.jar
```
