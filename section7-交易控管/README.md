## 七、交易控管 - Transaction Management
### 1. 什麼是交易(Transaction)?
#### Transaction 是邏輯上的一組操作，要麼都執行，要麼都不執行.
#### 所謂交易管理，其實就是“按照給定的交易規則來執行提交或者回滾操作”。
#### Transaction 的特性（ACID）：
#### (1) 原子性： 交易是最小的執行單位，不允許分割。交易的原子性確保動作要麼全部完成，要麼完全不起作用；
#### (2) 一致性： 執行交易前後，資料保持一致；
#### (3) 隔離性： 併發訪問資料庫時，一個使用者的事物不被其他事物所幹擾，各併發交易之間資料庫是獨立的；
#### (4) 永續性: 一個交易被提交之後。它對資料庫中資料的改變是持久的，即使資料庫發生故障也不應該對其有任何影響。
### 2. Spring 交易管理的介面說明
#### Spring並不直接管理交易，而是提供了多種交易管理器 ，他們將交易管理的職責委託給Hibernate或者JPA等持久化機制所提供的相關平臺框架的交易來實現。 
#### Spring交易管理器的介面是： org.springframework.transaction.PlatformTransactionManager ，通過這個介面，Spring為各個平臺如JDBC、Hibernate等都提供了對應的交易管理器，但是具體的實現就是各個平臺自己的事情了。
#### PlatformTransactionManager介面中定義了三個方法：
	Public interface PlatformTransactionManager()...{  
		// Return a currently active transaction or create a new one, according to the specified propagation behavior（根據指定的傳播行為，返回當前活動的交易或建立一個新交易。）
		TransactionStatus getTransaction(TransactionDefinition definition) throws TransactionException; 
		
		// Commit the given transaction, with regard to its status（使用交易目前的狀態進行提交）
		Void commit(TransactionStatus status) throws TransactionException;  
		
		// Perform a rollback of the given transaction（對執行的交易進行回滾）
		Void rollback(TransactionStatus status) throws TransactionException;  
	}
## Spring支援兩種方式的事務管理：
### 1. 編程式的交易管理
#### 通過Transaction Template手動管理交易，實際應用中很少使用
### 2. 宣告式的交易管理
#### 推薦使用（程式碼侵入性最小），實際是通過 AOP 實現
#### 實現宣告式交易的四種方式：
##### (1)基於 TransactionInterceptor 的宣告式交易: Spring 宣告式交易的基礎，通常也不建議使用這種方式，但是與前面一樣，瞭解這種方式對理解 Spring 宣告式事務有很大作用。
##### (2)基於 TransactionProxyFactoryBean 的宣告式交易: 第一種方式的改進版本，簡化的配置檔案的書寫，這是 Spring 早期推薦的宣告式交易管理方式，但是在 Spring 2.0 中已經不推薦了。
##### (3)基於< tx> 和< aop>名稱空間的宣告式交易管理： 其最大特點是與 Spring AOP 結合緊密，可以充分利用切點表示式的強大支援，使得管理交易更加靈活。
##### (4)基於 @Transactional 的全註解方式： 將宣告式交易管理簡化到了極致。開發人員只需在配置檔案中加上一行啟用相關後處理 Bean 的配置，然後在需要實施交易管理的方法或者類上使用 @Transactional 指定交易規則即可實現交易管理，而且功能也不必其他方式遜色。


