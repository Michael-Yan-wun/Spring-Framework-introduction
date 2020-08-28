## 十一、Spring Web MVC
### 在Web MVC架構中，使用者並不直接連接至所需的資源，而必須先連接至前端控制器（Front controller），由前端控制器判斷使用者的請求要分派（Dispatch）給哪一個控制物件（Controller）來處理請求，藉此執到控制使 用者可請求的資源之目的。
### 在Spring的Web MVC框架中，擔任前端控制器角色的是org.springframework.web.servlet.DispatcherServlet， 
### DispatcherServlet負責將客戶的請求分派給對應於請求的控制物件，所以使用Spring Web MVC的第一步，就是在web.xml中定義 DispatcherServlet：
### <img src="../images/spring-mvc.jpg">
### 範例 : 
### 1. 傳統 Spring Framework WEB MVC 專案配置
#### Maven 配置: pom.xml
    <dependencies>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-web</artifactId>
            <version>5.1.3.RELEASE</version>
        </dependency>

        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
            <version>5.1.3.RELEASE</version>
        </dependency>
    </dependencies>
    
#### web.xml
    <servlet>
        <servlet-name>dispatcherServlet</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath:spring.xml</param-value>
        </init-param>
        <load-on-startup>1</load-on-startup>
    </servlet>

    <servlet-mapping>
        <servlet-name>dispatcherServlet</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>
    
#### Spring.xml
	<context:component-scan base-package="study.class"/>
    <mvc:annotation-driven/>
    <bean id="viewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <property name="viewClass" value="org.springframework.web.servlet.view.JstlView"/>
        <property name="prefix" value="/WEB-INF/jsp/"/>
        <property name="suffix" value=".jsp"/>
    </bean>
    
#### Controller
	@Controller
	public class HelloSpringController {

		@RequestMapping("")
		public String index(){
			return "index";
		}
	}
### 2. Spring Boot 簡化 MVC 專案

### 練習 -- 使用 Spring Web MVC 製作一個學生資料管理系統(CRUD+Search) ! 
