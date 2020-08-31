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
	Public interface PlatformTransactionManager(){  
		// Return a currently active transaction or create a new one, according to the specified propagation behavior（根據指定的傳播行為，返回當前活動的事務或建立一個新事務。）
		TransactionStatus getTransaction(TransactionDefinition definition) throws TransactionException; 
		
    // Commit the given transaction, with regard to its status（使用事務目前的狀態提交事務）
		Void commit(TransactionStatus status) throws TransactionException;  
		
    // Perform a rollback of the given transaction（對執行的事務進行回滾）
		Void rollback(TransactionStatus status) throws TransactionException;  
	}

### 1. 編程式的交易管理
### 2. 宣告式的交易管理
