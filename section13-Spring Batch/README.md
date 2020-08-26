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