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