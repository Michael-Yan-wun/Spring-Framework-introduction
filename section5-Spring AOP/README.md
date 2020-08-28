## 五、Spring AOP
### 1. 什麼是 AOP (面向切面編程)?
#### AOP全名為Aspect-Oriented Programming，有關於AOP的許多名詞術語都過於抽象，單從字面上並不容易理解其名詞意義。
#### 簡單的說，它只是一個攔截器攔截的一些過程，例如，當一個方法執行，Spring AOP 可以劫持一個執行的方法，在方法執行之前或之後新增額外的功能。
#### [名詞術語]:
#### ○ Cross-cutting concern:
#### 類似於日誌這類的動作，如安全 （Security）檢查、交易（Transaction）等系統層面的服務（Service），在一些應用程式之中常被見到安插至各個物件的處理流程之 中，這些動作在AOP的術語中被稱之為Cross-cutting concerns。
#### Cross-cutting concerns若直接撰寫在負責某商務的物件之流程中，會使得維護程式的成本增高，例如若您今天要將物件中的記錄功能修改或是移除該服務，則必須修改所 有撰寫曾記錄服務的程式碼，然後重新編譯，另一方面，Cross-cutting concerns混雜於商務邏輯之中，使得商務物件本身的邏輯或程式的撰寫更為複雜。
#### 現在為了要加入日誌（Logging）與安全（Security）檢查等服務，物件的程式碼中若被硬生生的寫入相關的Logging、Security程式片段，則可使用以下圖解表示出Cross-cutting與Cross-cutting concerns的概念：
#### ○ Aspect: 
#### 將散落於各個商務物件之中的Cross-cutting concerns收集起來，設計各個獨立可重用的物件，這些物件稱之為Aspect。
#### ○ Advice: 
#### Aspect的具體實作稱之為Advice，以日誌的動作而言，Advice中會包括真正的日誌程式碼是如何實作的，Advice中包括了Cross-cutting concerns的行為或所要提供的服務。
#### ○ Joinpoint: 
#### Aspect在應用程式執行時加入商務流程的點或時機稱之為Joinpoint，具體來說，就是Advice在應用程式中被呼叫執行的時機，這個時機可能是某個方法被呼叫之前或之後（或兩者都有），或是某個例外發生的時候。
#### ○ Pointcut: 
#### Pointcut是一個定義，藉由這個定義您可以指定某個Aspect在哪些Joinpoint時被應用至應用程式之上。
#### ○ Target: 
#### 一個Advice被應用的對象或目標物件。
#### ○ Introduction: 
#### 對於一個現存的類別，Introduction可以為其增加行為，而不用修改該類別的程式，具體的說，您可以為某個已撰寫、編譯完成的類別，在執行時期動態加入一些方法或行為，而不用修改或新增任何一行程式碼。
#### ○ Proxy: 
#### 一個類被 AOP 織入 advice, 就會產生一個結果類, 它是融合了原類和增強邏輯的代理類.在 Spring AOP 中, 一個 AOP 代理是一個 JDK 動態代理物件或 CGLIB 代理物件.
#### ○ Weave: 
#### Advice被應用至物件之上的過程稱之為織入（Weave），在AOP中織入的方式有幾個時間點：編譯時期（Compile time）、類別載入時期（Classload time）、執行時期（Runtime）。
#### Spring 採用動態代理織入(執行時期), 而AspectJ採用編譯器織入(編譯時期)和類裝載期織入(類別載入時期)
#### <img src="../images/AOPConcept-1.jpg">

#### 在Spring AOP中，有 5 種型別通知(Advices)：
#### (1)@Before:通知(Advice)之前 - 該方法執行前執行
#### (2)@After:通知(Advice)返回之後 – 該方法執行後執行，該方法返回一個結果
#### (3)@AfterReturning:通知(Advice)執行後 - 該方法執行後並擷取返回值後執行。
#### (4)@AfterThrowing:通知(Advice)丟擲之後 – 該方法丟擲異常後執行。
#### (5)@Around:環繞通知 – 在執行的前後插入執行。
#### 結合動態代理的實例，將以上介紹過的AOP相關名詞具體的使用圖片來加以表示，有助於您對每一個名詞的理解與認識：
#### <img src="../images/AOPConcept-2.jpg">

### 2. AOP 範例說明 - [Github 連結](https://github.com/RameshMF/spring-aop-tutorial/ "範例連結")
### 3. 練習 - 使用 AOP 進行日誌記錄
