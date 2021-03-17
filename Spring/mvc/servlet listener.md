#### 原始监听器实现

##### UserServlet.java

```java
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
    UserService userService = app.getBean(UserService.class);
    userService.service();
}
```

弊端：应用文件加载多次，上下文对象创建多次；

解决：

1. 使用 ServletContextListener 监听 Web应用的启动，在启动时记载 Spring 配置文件并创建上下文对象；

    （未访问时便创建）

2. 将其保存在 ServletContext 域中；



监听器 ContextLoaderListener.java

```java
public class ContextLoaderListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent sce) {
        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        ServletContext servletContext = sce.getServletContext();
        servletContext.setAttribute("app", app);
    }

    public void contextDestroyed(ServletContextEvent sce) {

    }
}

```

配置监听器 web.xml

```xml
<listener>
    <listener-class>com.cjm.listener.ContextLoaderListener</listener-class>
</listener>
```

修改 UserServlet.java

```java
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = req.getServletContext();
        ApplicationContext app = (ApplicationContext)servletContext.getAttribute("app");
        UserService userService = app.getBean(UserService.class);
        userService.service();
    }
}
```

- 优化一：针对ContextLoaderListener.java 中 ApplicationContext 高耦合创建；

    1. web.xml 配置 applicationContext.xml

    ```xml
    <!--全局初始化参数-->
    <context-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>applicationContext.xml</param-value>
    </context-param>
    ```

    2. 变量获取 ApplicationContext：

    ```java
    ServletContext servletContext = sce.getServletContext();
    String contextConfigLocation = servletContext.getInitParameter("contextConfigLocation");
    ApplicationContext app = new ClassPathXmlApplicationContext(contextConfigLocation);
    ```

    

#### Spring 监听器实现

1. web.xml 中配置 ContextLoaderListener 监听器；
2. 使用 WebApplicationContextUtils 获得上下文对象 ApplicationContext