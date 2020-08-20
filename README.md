# <img src="images/spring-logo.png" width="48" height="48"> Spring-Framework 入門教學
## 一、Spring Framework 簡介
### 1. Spring Framework 是甚麼?
### <img src="images/ecosys.png">
### 2. Spring 的起源
### 3. Spring 的關鍵特性
### 4. Spring 版本歷史
#### <img src="images/spring-ejb-timeline.png">
### 5. Spring 的體系架構
#### <img src="images/structure.png">

## 二、Spring 元件與 IOC 容器
### 1. 什麼是 Spring 元件
### 2. 什麼是 IOC 容器
### 3. 元件的標記
### 4. 元件的注入
### 5. 元件的依賴
### <img src="images/ioc.jpg">

## 三、Spring Boot
### 1.Spring Boot 簡介
### 2.教程 - 建立一個 Spring Boot 專案 
### [參考資料](https://matthung0807.blogspot.com/2018/03/springbootweb.html/ "教學網站及範例")
### <img src="images/spring-boot.png">

## 四、Dependency Injection
### 1. 什麼是依賴注入?
### 2. 為什麼需要依賴注入?


## 五、Spring AOP
### 1. 什麼是 AOP (面向切面編程)?
### 2. AOP 範例說明
### 3. 練習 - 使用 AOP 進行日誌記錄

## 六、Spring 整合資料庫
### 1. Spring Data Access/Integration 模組簡介
#### (1)Spring-JDBC：提供以JDBC訪問資料庫的支援
#### (2)Spring-TX：提供編程式和宣告式的事務支援
#### (3)Spring-ORM：提供對物件/關係對映技術的支援
#### (4)Spring-OXM：提供對物件/xml對映技術的支援
#### (5)Spring-JMS：提供對Java Message Service 的支援

## 七、交易控管
### 1. 什麼是交易?
### 2. Spring 交易管理的介面說明
### 1. 編程式的交易管理
### 2. 宣告式的交易管理

## 八、Spring Data JPA
### 1. 什麼是 Spring Data JPA
### 2. Spring JPA + Hibernate 範例說明
### 3. 練習 -- 使用 Spring JPA + Hibernate 建立一個學生資料表

## 九、Spring 整合 MongoDB
### 1. MongoDB 簡介
### 2. Spring Data MongoDB + MongoDB 簡單範例
### 3. 練習 -- 註冊 Cloud MongoDB 帳號並建立一個專案Cluster 以及 Collection , 學生資料表,並用 Spring Data MongoDB 連線存取學生資料 

## 十、Spring 與訊息(Message)整合
### 1. 基本概念
#### JMS是java的消息服務，JMS的客戶端之間可以通過JMS服務進行異步的消息傳輸。
#### JMS（JAVA Message Service,java消息服務）API是一個消息服務的標準或者說是規範，允許應用程序組件基於JavaEE平臺創建、發送、接收和讀取消息。它使分布式通信耦合度更低，消息服務更加可靠以及異步性。
### 2. 消息模型
####  ○ Point-to-Point(P2P) : 點對點模型
#### <img src="images/JMSQueue.png">
####  ○ Publish/Subscribe(Pub/Sub) : 發布訂閱模型
#### <img src="images/JMSTopic.png">
### 3. 範例說明
### 4. 練習 -- 發送訊息給指定主題訂閱者

## 十一、Spring Web MVC
### <img src="images/spring-mvc.jpg">
### 1. 範例說明
### 2. 練習 -- 使用 Spring Web MVC 製作一個學生資料管理系統(CRUD+Search) ! 

## 十二、Spring Security
### 1. 什麼是Spring Security?
#### Spring Security是一個提供認證與授權的軟體架構
#### ○ 認證: 確保使用者人如其名。Spring Security提供認證的機制有Http Basic, Form Based … 等。
#### ○ 授權: 確保使用者僅可存取允許的資源。Spring Security提供幾種授權的層級，有http請求、http方法、物件等。
#### <img src="images/spring-security-authenticate.jpg">
### 2. 範例說明:
### 3. 練習 -- 建立一個專案，使用Spring Security 實作 admin、user 角色註冊、驗證及權限管理機制

## 十三、Spring Batch
### Spring Batch 是一個輕量級的、全面的批處理框架，用於開發對企業系統的日常操作至關重要的健壯批處理應用程式。Spring  Batch提供了處理大量記錄所必需的可重用功能，包括日誌/跟蹤、事務管理、作業處理統計資訊、  作業重啟、跳過和資源管理。它還提供了更高階的技術服務和特性，這些特性將通過優化和分割槽技術支援極高容量和高效能的批處理作業。
### 1. Features(特性)：
#### Transaction management   事務管理
#### Chunk based processing   基於塊的處理
#### Declarative I/O          宣告式I / O
#### Start/Stop/Restart       啟動/停止/啟動
#### Retry/Skip               重試/跳過
#### Web based administration interface (Spring Cloud Data Flow)  基於Web的管理介面(Spring Cloud Data Flow)
### 2. Spring batch 主要由以下部分組成：
#### JobRepository  用來註冊job的容器
#### JobLauncher    用來啟動Job的介面
#### Job            實際執行的任務，包含一個或多個Step
#### Step           step包含ItemReader、ItemProcessor和ItemWriter
#### ItemReader     用來讀取資料的介面
#### ItemProcessor  用來處理資料的介面
#### ItemWriter     用來輸出資料的介面
#### <img src="images/spring-batch-component.png">
#### 一個Job有一個或多個Step組成，Step有讀、處理、寫三部分操作組成；通過JobLauncher啟動Job，啟動時從JobRepository獲取Job Execution；當前執行的Job及Step的結果及狀態儲存在JobRepository中。
### 3. 範例說明: Springboot整合Spring Batch 的 一個 [HelloSpringBatch Demo](https://github.com/code-not-found/spring-batch/tree/master/spring-batch-hello-world/ "源代碼") 

