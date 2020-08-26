## 二、Spring 元件與 IOC 容器
### 1. 什麼是 Spring 元件
#### 在 Java 類別加上特定的標記，將會被視為「元件」，例如「@Controller」與「@Service」標記就是。

### 2. 什麼是 IOC 容器
#### IOC（Inverse of Control）：控制反轉，控制反轉模式（也稱作依賴性介入）的基本概念是：不創建物件，但是描述創建它們的方式，將物件的建立權反轉給（交給）IOC 容器。
#### 在代碼中不直接與物件和服務連接，但在配置文件中描述哪一個組件需要哪一項服務。
### <img src="images/ioc.jpg">

### 3. 範例說明: 
    class Student{
      Address address;
      Student(){
        address=new Address();
      }
    }
    
### 上面的示例代碼顯示了Student和Address之間的依賴關係。你可以說學生和地址緊密相連。
### IoC使代碼鬆散耦合。 上面使用的例子可以重做如下:
    class Student{
      Address address;
      Student(Address address) {
        this.address=address;
      }
    }



