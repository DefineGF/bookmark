#### 基于 xml 通知类型

通知类型：

- 前置通知：\<aop:before>
- 后置通知：\<aop:after-returning> 
- 最终通知：\<aop:after> 无论是否有异常都会执行；
- 环绕通知：\<aop:around> 之前和之后都执行
- 异常抛出通知：\<aop:throwing> 异常时执行





#### 基于xml 实例

##### 配置文件

- pom.xml

```xml
<dependency>
    <groupId>org.aspectj</groupId>
    <artifactId>aspectjweaver</artifactId>
    <version>1.9.4</version>
</dependency>

<!--测试框架-->
<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>4.12</version>
</dependency>
```

- applicationContext.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
                            http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop.xsd">


    <!--目标对象-->
    <bean id="target" class="aop.Target"/>

    <!--切面对象-->
    <bean id="myAspect" class="aop.MyAspect"/>

    <!--配置织入 指定需要增强的方法-->
    <aop:config>
        <!--声明切面-->
        <aop:aspect ref="myAspect">
            <!--<aop:before method="myBefore" pointcut="execution(public void aop.Target.save())"/>-->
            <aop:around method="myAround" pointcut="execution(* aop.*.*(..))"/>
        </aop:aspect>
    </aop:config>


</beans>
```



##### 目标文件

- TargetInterface.java

```java
public interface TargetInterface {
    public void save();
}
```

- Target.java

```java
public class Target implements TargetInterface {
    public void save() {
        System.out.println("run the save~");
    }
}
```



##### 切面

```java
public class MyAspect {
    public void myBefore() {
        System.out.println("前置增强");
    }
    
    public Object myAround(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("环绕前...");
        Object proceed = pjp.proceed();
        System.out.println("环绕后...");
        return proceed;
    }   
}
```

##### 测试文件

```java
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class AopTest {
    @Autowired 
    private TargetInterface target;

    @Test
    public void test() {
        target.save();
    }
}
```



#### 基于注解实例

##### 配置文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd
http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop.xsd">


    <!--组件扫描-->
    <context:component-scan base-package="anno_aop"/>

    <!--aop 自动代理-->
    <aop:aspectj-autoproxy/>

</beans>
```



##### 其余组件

```java
// TargetInterface.java
public interface TargetInterface {
    public void save();
}

// Target.java
import org.springframework.stereotype.Component;

@Component("target")
public class Target implements TargetInterface {

    public void save() {
        System.out.println("run the save~");
    }
}

// MyAspect.java
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component("myAspect")
@Aspect // 标注当前 class 是个切面类
public class MyAspect {

    // 配置前置增强
    @Before("execution(* anno_aop.*.*(..))")
    public void myBefore() {
        System.out.println("前置增强");
    }

    public Object myAround(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("环绕前...");
        Object proceed = pjp.proceed();
        System.out.println("环绕后...");
        return proceed;
    }
}
```

