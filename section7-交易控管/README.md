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
#### 交易管理器介面 PlatformTransactionManager 通過 getTransaction(TransactionDefinition definition) 方法來得到一個交易，這個方法裡面的引數是 TransactionDefinition類 ，這個類就定義了一些基本的交易屬性。
#### 交易屬性可以理解成交易的一些基本配置，描述了交易策略如何應用到方法上。交易屬性包含了5個隔離級別。
#### (1) TransactionDefinition.ISOLATION_DEFAULT: 使用後端資料庫預設的隔離級別，Mysql 預設採用的 REPEATABLE_READ隔離級別 Oracle 預設採用的 READ_COMMITTED隔離級別.
#### (2) TransactionDefinition.ISOLATION_READ_UNCOMMITTED: 最低的隔離級別，允許讀取尚未提交的資料變更，可能會導致髒讀、幻讀或不可重複讀
#### (3) TransactionDefinition.ISOLATION_READ_COMMITTED: 允許讀取併發交易已經提交的資料，可以阻止髒讀，但是幻讀或不可重複讀仍有可能發生
#### (4) TransactionDefinition.ISOLATION_REPEATABLE_READ: 對同一欄位的多次讀取結果都是一致的，除非資料是被本身交易自己所修改，可以阻止髒讀和不可重複讀，但幻讀仍有可能發生。
#### (5) TransactionDefinition.ISOLATION_SERIALIZABLE: 最高的隔離級別，完全服從ACID的隔離級別。所有的交易依次逐個執行，這樣交易之間就完全不可能產生干擾，也就是說，該級別可以防止髒讀、不可重複讀以及幻讀。但是這將嚴重影響程式的效能。通常情況下也不會用到該級別。
#### 在典型的應用程式中，多個交易併發執行，經常會操作相同的資料來完成各自的任務（多個使用者對統一資料進行操作）。併發雖然是必須的，但可能會導致一下的問題。

#### (1) 髒讀（Dirty read）: 當一個交易正在訪問資料並且對資料進行了修改，而這種修改還沒有提交到資料庫中，這時另外一個交易也訪問了這個資料，然後使用了這個資料。因為這個資料是還沒有提交的資料，那麼另外一個交易讀到的這個資料是“髒資料”，依據“髒資料”所做的操作可能是不正確的。

#### (2) 丟失修改（Lost to modify）: 指在一個交易讀取一個數據時，另外一個交易也訪問了該資料，那麼在第一個交易中修改了這個資料後，第二個交易也修改了這個資料。這樣第一個交易內的修改結果就被丟失，因此稱為丟失修改。

#### 例如：交易1讀取某表中的資料A=20，交易2也讀取A=20，交易1修改A=A-1，交易2也修改A=A-1，最終結果A=19，交易1的修改被丟失。

#### (3) 不可重複讀（Unrepeatableread）: 指在一個交易內多次讀同一資料。在這個交易還沒有結束時，另一個交易也訪問該資料。那麼，在第一個交易中的兩次讀資料之間，由於第二個交易的修改導致第一個交易兩次讀取的資料可能不太一樣。這就發生了在一個交易內兩次讀到的資料是不一樣的情況，因此稱為不可重複讀。

#### (4) 幻讀（Phantom read）: 幻讀與不可重複讀類似。它發生在一個交易（T1）讀取了幾行資料，接著另一個併發交易（T2）插入了一些資料時。在隨後的查詢中，第一個交易（T1）就會發現多了一些原本不存在的記錄，就好像發生了幻覺一樣，所以稱為幻讀。

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

### [參考資料1:Spring 事務管理詳解](https://www.itread01.com/content/1552179603.html "Spring 事務管理詳解")
### [參考資料2:Spring 程式設計式和宣告式事務例項講解](https://www.itread01.com/content/1552096804.html "Spring 程式設計式和宣告式事務例項講解")
