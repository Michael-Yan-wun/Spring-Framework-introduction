## 四、Dependency Injection
### 1. 什麼是依賴注入?
#### 把被依賴物件注入被動接收物件中
#### IOC(控制反轉)是一種設計原則、一種概念，而依賴注入則是實現這種概念的方式

### 2. 為什麼需要依賴注入?
#### 解決兩個類別間耦合性過高的問題，一般會搭配介面(interface)形式進行注入，
#### 使程式結構保有較高的彈性，以便在需求發生變化時能夠靈活的抽換，達到控制反轉(IOC, Inversion Of Control)的效果。

### 3.案例說明：
#### 小明是個愛乾淨的人，但他工作時常加班導致房間雜亂，他不能忍受此狀況，所以小明去找一個清潔阿姨每天幫忙他打掃家裡，如果阿姨哪天有事不能打掃，小明就必須要再去找人來幫忙打掃，由此可知小明耦合阿姨!!!
#### 如果今天是....
#### 小明把他要的條件給「打掃仲介公司」，仲介公司幫他尋找有沒有符合小明需求的打掃阿姨，假如今天A阿姨請假了，仲介公司會自動找另一個符合需求B阿姨幫忙打掃...
#### 原本小明需耦合於打掃阿姨，現在被「仲介公司」做了控制反轉讓「仲介公司」來提供打掃阿姨。
#### 小明不用管是否今天有人會來打掃，「仲介公司」會幫小明找到一個掃地阿姨。
#### 「仲介公司」可看作 依賴注入容器 ( IOC Container )
#### 「小明」可看作 被動接收物件
#### 「打掃阿姨」可看作 被依賴物件

#### 下面範例來說明上面的例子
#### (1) 小明自己依賴於掃地阿姨: 依賴程式碼寫在小明類別內部日後要更改只能動內部程式碼。
	/// <summary>
	/// 小明直接依賴 Aunt 不是依賴抽象
	/// 日後要改必須動內部
	/// </summary>
	public class Mine
	{
		public Aunt aunt = new Aunt();

		public void Room()
		{
			aunt.Swapping();
		}
	}

#### 呼叫使用時:
	Mine mine = new Mine();
	mine.Room();
  
#### (2) 小明找仲介公司: 仲介公司(Ioc容器)
#### 在仲介公司內註冊需求，讓仲介公司日後幫你找人（註冊的類別）
	/// <summary>
	/// 仲介公司
	/// </summary>
	/// <returns></returns>
	private static IContainer MiddleCompany()
	{
		ContainerBuilder builder = new ContainerBuilder();

		//在仲介公司裡寫需求人申請單
		builder.RegisterType<MineWithMiddle>();
		//小明所需打掃阿姨需求
		builder.RegisterType<Aunt>().As<ISwapable>();

		return builder.Build();
	}
#### 使用起來:
	IContainer middleCompany = MiddleCompany();
	//仲介公司(IOC AutoFac)自動幫小明注入一個打掃阿姨
	MineWithMiddle mineWithMiddle = middleCompany.Resolve<MineWithMiddle>();

	mineWithMiddle.Room();
#### 總結：
#### 雖然上面程式碼執行結果一樣，但內部結構和日後擴展性卻截然不同
