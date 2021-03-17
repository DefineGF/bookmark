#### import: 引入其他配置文件

```xml
<import resource = "applicationContext-xxx.xml"/>
```



#### bean

- id
- class：类完整路径

- scope：作用范围
    - singleton：单例；默认
    - prototype：多例
    - request：web中将对象存入request中
    - session：web中将对象存入session中
    - global session：web中应用protlet环境
- init-method：类中初始化方法
- destroy-method：类中销毁方法

实例化：

- 无参构造

- 工厂静态方法：

    ```java
    public class StaticFactory {
        public static UserDao getUserDao() {
            return new UserDaoImpl();
        }
    }
    ```

    配置：

    ```xml
    <bean id = "userDao" class = "com.xxx.StaticFactory" 
          factory-method="getUserDao"></bean>
    ```

    

- 工厂实例方法

    ```
    public class DynamicFactory {
        public UserDao getUserDao() {
            return new UserDaoImpl();
        }
    }
    ```

    配置：

    ```xml
    <bean id = "factory" class = "com.xxx.DynamicFactory"></bean>
    <bean id = "userDao" factory-bean = "factory" factory-method = "getUserDao"></bean>
    ```

    