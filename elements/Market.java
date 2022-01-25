package elements;

import java.util.*;
/**
 * Market class in the system. Mainly, this class is designed to do operations in the market. Almost all operationsa are conducted through thiss class such as Open Market Operations, giving buy order, giving sell order.
 * @author sagolinata111
 *
 */
public class Market{
	/**
	 * The Priority Queue that keeps the sellingOrder obejcts in it.
	 */
	private PriorityQueue<SellingOrder> sellingOrders;
	/**
	 * The priority Queue that keeps the buyingOrder objects in it.
	 */
	private PriorityQueue<BuyingOrder> buyingOrders;
	/**
	 * The Arraylist that keeps transaction objects in it.
	 */
	private ArrayList<Transaction> transactions;
	/**
	 * The comission that market gets when transactions happen.
	 */
	private double fee;
	/**
	 * Total number of Invalid queries in the system.
	 */
	private int totalnOfInvalidQueries;
	/**
	 * Total number of succesful transactions in the system.
	 */
	private int totalnOfSuccesfulTransactions;
	/**
	 * The Arraylist that keeps traders objects in it.
	 */
	private ArrayList<Trader> traders;
	
	/**
	 * This constructor builds the market according to given parameter. sellingOrders, buyingOrders, transactions and traders are initialized.
	 * @param fee
	 */
	public Market(int fee) {
		this.fee = fee;
		this.sellingOrders = new PriorityQueue<SellingOrder>();
		this.buyingOrders = new PriorityQueue<BuyingOrder>();
		this.transactions = new ArrayList<Transaction>();
		this.totalnOfInvalidQueries = 0;
		this.totalnOfSuccesfulTransactions = 0;
		this.traders = new ArrayList<Trader>();
		
	}
	
	/**
	 * All transactions are made through this method. Takes trader arraylist and makes transactions. The main logic behind transaction is to overlap sellers and buyers if they satisfy certain conditions. When this method called, first it looks whether prices on the top of queues are overlapping. 
	 * If there is overlap, transactions are made until there is no overlapping. In each successful transaction, total number of successful transactions is incremented by one.
	 * @param traders Traders in the market.
	 */
	public void checkTransactions(ArrayList<Trader> traders) {
			while(sellingOrders.peek()!=null && buyingOrders.peek()!=null && sellingOrders.peek().getPrice() <= buyingOrders.peek().getPrice()){
				if(buyingOrders.peek().getAmount() > sellingOrders.peek().getAmount()) {
					Transaction aTransaction = new Transaction(sellingOrders.peek(),buyingOrders.peek());
					transactions.add(aTransaction);
					traders.get(buyingOrders.peek().getTraderID()).getWallet().setCoins(traders.get(buyingOrders.peek().getTraderID()).getWallet().getCoins()+sellingOrders.peek().getAmount());
					traders.get(buyingOrders.peek().getTraderID()).getWallet().setBlockedDollars(traders.get(buyingOrders.peek().getTraderID()).getWallet().getBlockedDollars() - (sellingOrders.peek().getAmount()*buyingOrders.peek().getPrice()));
					traders.get(buyingOrders.peek().getTraderID()).getWallet().setDollars(traders.get(buyingOrders.peek().getTraderID()).getWallet().getDollars() + ((buyingOrders.peek().getPrice()-sellingOrders.peek().getPrice())*sellingOrders.peek().getAmount()));
					traders.get(sellingOrders.peek().getTraderID()).getWallet().setBlockedCoins(traders.get(sellingOrders.peek().getTraderID()).getWallet().getBlockedCoins()-sellingOrders.peek().getAmount());
					traders.get(sellingOrders.peek().getTraderID()).getWallet().setDollars(traders.get(sellingOrders.peek().getTraderID()).getWallet().getDollars() + (sellingOrders.peek().getAmount()*sellingOrders.peek().getPrice())*(1-(double)fee/1000));
					buyingOrders.peek().setAmount(buyingOrders.peek().getAmount()-sellingOrders.peek().getAmount());
					BuyingOrder buyPolled = buyingOrders.poll();
					buyingOrders.add(buyPolled);
					sellingOrders.peek().setAmount(0);
					sellingOrders.poll();
					this.totalnOfSuccesfulTransactions += 1;
				}
				
				else if(buyingOrders.peek().getAmount() == sellingOrders.peek().getAmount()) {
					Transaction aTransaction = new Transaction(sellingOrders.peek(),buyingOrders.peek());
					transactions.add(aTransaction);
					traders.get(buyingOrders.peek().getTraderID()).getWallet().setCoins(traders.get(buyingOrders.peek().getTraderID()).getWallet().getCoins()+sellingOrders.peek().getAmount());
					traders.get(buyingOrders.peek().getTraderID()).getWallet().setBlockedDollars(traders.get(buyingOrders.peek().getTraderID()).getWallet().getBlockedDollars() - (buyingOrders.peek().getAmount()*buyingOrders.peek().getPrice()));
					traders.get(buyingOrders.peek().getTraderID()).getWallet().setDollars(traders.get(buyingOrders.peek().getTraderID()).getWallet().getDollars() + ((buyingOrders.peek().getPrice()-sellingOrders.peek().getPrice())*sellingOrders.peek().getAmount()));
					traders.get(sellingOrders.peek().getTraderID()).getWallet().setBlockedCoins(traders.get(sellingOrders.peek().getTraderID()).getWallet().getBlockedCoins() - sellingOrders.peek().getAmount());
					traders.get(sellingOrders.peek().getTraderID()).getWallet().setDollars(traders.get(sellingOrders.peek().getTraderID()).getWallet().getDollars() + (sellingOrders.peek().getAmount()*sellingOrders.peek().getPrice())*(1-(double)fee/1000));
					sellingOrders.peek().setAmount(0);
					buyingOrders.peek().setAmount(0);
					sellingOrders.poll();
					buyingOrders.poll();
					this.totalnOfSuccesfulTransactions += 1;
				}
				
				else if(buyingOrders.peek().getAmount() < sellingOrders.peek().getAmount()) {
					Transaction aTransaction = new Transaction(sellingOrders.peek(),buyingOrders.peek());
					transactions.add(aTransaction);
					traders.get(buyingOrders.peek().getTraderID()).getWallet().setCoins(traders.get(buyingOrders.peek().getTraderID()).getWallet().getCoins()+buyingOrders.peek().getAmount());
					traders.get(buyingOrders.peek().getTraderID()).getWallet().setBlockedDollars(traders.get(buyingOrders.peek().getTraderID()).getWallet().getBlockedDollars() - (buyingOrders.peek().getAmount()*buyingOrders.peek().getPrice()));
					traders.get(buyingOrders.peek().getTraderID()).getWallet().setDollars(traders.get(buyingOrders.peek().getTraderID()).getWallet().getDollars() + ((buyingOrders.peek().getPrice()-sellingOrders.peek().getPrice())*buyingOrders.peek().getAmount()));
					traders.get(sellingOrders.peek().getTraderID()).getWallet().setBlockedCoins(traders.get(sellingOrders.peek().getTraderID()).getWallet().getBlockedCoins()-buyingOrders.peek().getAmount());
					traders.get(sellingOrders.peek().getTraderID()).getWallet().setDollars(traders.get(sellingOrders.peek().getTraderID()).getWallet().getDollars() + (buyingOrders.peek().getAmount()*sellingOrders.peek().getPrice())*(1-(double)fee/1000));
					sellingOrders.peek().setAmount(sellingOrders.peek().getAmount()-buyingOrders.peek().getAmount());
					SellingOrder sellPolled = sellingOrders.poll();
					sellingOrders.add(sellPolled);
					buyingOrders.peek().setAmount(0);
					buyingOrders.poll();
					this.totalnOfSuccesfulTransactions += 1;
				}
				
			}
		
	}
	/**
	 * This method is called when a trader or the system itself gives buying order.
	 * @param order BuyingOrder that trader wants to give.
	 */
	public void giveBuyOrder(BuyingOrder order) {
		buyingOrders.add(order);
		checkTransactions(traders);
		
		
	}
	/**
	 * This method is called when a trader or the system itself gives selling order.
	 * @param order SellingOrder that trader wants to give.
	 */
	public void giveSellOrder(SellingOrder order) {
		sellingOrders.add(order);
		checkTransactions(traders);

		
	}
	
	/**
	 * This method is called when the system itself (trader0) wants to set the current price to the given parameter price.
	 * The system creates order until the peek of the buyingOrder is less than the given price and peek of the sellingOrder is more than given price.
	 * @param price Price that market wants to converge.
	 */
	public void makeOpenMarketOperation(double price) {
			while(buyingOrders.peek()!=null && buyingOrders.peek().getPrice() >= price) {
				traders.get(buyingOrders.peek().getTraderID()).getWallet().setCoins(traders.get(buyingOrders.peek().getTraderID()).getWallet().getCoins()+buyingOrders.peek().getAmount());
				traders.get(buyingOrders.peek().getTraderID()).getWallet().setBlockedDollars(traders.get(buyingOrders.peek().getTraderID()).getWallet().getBlockedDollars() - (buyingOrders.peek().getAmount()*buyingOrders.peek().getPrice()));
				this.totalnOfSuccesfulTransactions +=1 ;
				buyingOrders.peek().setAmount(0);
				buyingOrders.poll();
				
			}
			while(sellingOrders.peek() != null &&sellingOrders.peek().getPrice() <= price) {
				traders.get(sellingOrders.peek().getTraderID()).getWallet().setBlockedCoins(traders.get(sellingOrders.peek().getTraderID()).getWallet().getBlockedCoins() - sellingOrders.peek().getAmount());
				traders.get(sellingOrders.peek().getTraderID()).getWallet().setDollars(traders.get(sellingOrders.peek().getTraderID()).getWallet().getDollars() + ((sellingOrders.peek().getAmount()*sellingOrders.peek().getPrice())*(1-(double)fee/1000)));
				sellingOrders.peek().setAmount(0);
				sellingOrders.poll();
				this.totalnOfSuccesfulTransactions+=1;
				
		}
		
	}
	/**
	 * getter method for sellingOrders.
	 * @return sellingOrders.
	 */
	public PriorityQueue<SellingOrder> getSellingOrders() {
		return sellingOrders;
	}
	/**
	 * getter method for buyingOrders.
	 * @return buyingOrders.
	 */
	public PriorityQueue<BuyingOrder> getBuyingOrders() {
		return buyingOrders;
	}
	/**
	 * setter method for sellingOrders.
	 * @param sellingOrders
	 */
	public void setSellingOrders(PriorityQueue<SellingOrder> sellingOrders) {
		this.sellingOrders = sellingOrders;
	}
	/**
	 * setter method for buyingOrders.
	 * @param buyingOrders
	 */
	public void setBuyingOrders(PriorityQueue<BuyingOrder> buyingOrders) {
		this.buyingOrders = buyingOrders;
	}
	/**
	 * This method is designed to determine current buying price which is the price of peek of the sellingOrders priority queue.
	 * @return  price of peek of the sellingOrders priority queue if size of the sellingOrders is bigger than 0, Otherwise return 0.
	 */
	public double currentBuyingPrice() {
		if(sellingOrders.size() != 0) {
			return sellingOrders.peek().getPrice();
		}
		else {
			return 0;
		}

	}
	
	/**
	 * This method is designed to determine current selling price which is the price of peek of the buyingOrders priority queue.
	 * @return  price of peek of the buyingOrders priority queue if size of the buyingOrders is bigger than 0, Otherwise return 0.
	 * 
	 */
	public double currentSellingPrice() {
		if(buyingOrders.size() != 0) {
			return buyingOrders.peek().getPrice();
		}
		else {
			return 0;
		}
	}
	
	/**
	 * This method is designed to determine average current price in the market. 
	 * @return If the both priority queues are empty, return 0. If one of the priority queues are empty, return price of the other's peek. If both of them are not empty, return the average of the price of the peeks.
	 */
	public double averagePrice() {
		if(buyingOrders.size() == 0 && sellingOrders.size() != 0) {
			return sellingOrders.peek().getPrice();
		}
		else if(buyingOrders.size() != 0 && sellingOrders.size() == 0) {
			return buyingOrders.peek().getPrice();
		}
		else if(buyingOrders.size() == 0 && sellingOrders.size() == 0) {
			return 0;
		}
		else {
			return (sellingOrders.peek().getPrice() + buyingOrders.peek().getPrice())/(double) 2;
		}
	}
	
	/**
	 * This method basicly returns total number of Invalid queries in the system.
	 * @return total number of invalid queries.
	 */
	public int totalnOfInvalidQueries() {
		this.totalnOfInvalidQueries = 0;
		for(int a = 0;a<traders.size();a++) {
			this.totalnOfInvalidQueries += traders.get(a).getnOfInvalidQueries();
		}
		return this.totalnOfInvalidQueries;
		
	}
	
	/**
	 * getter method for traders.
	 * @return
	 */
	public ArrayList<Trader> getTraders() {
		return traders;
	}
	/**
	 * getter method for successful transactions.
	 * @return
	 */
	public int getTotalnOfSuccesfulTransactions() {
		return totalnOfSuccesfulTransactions;
	}
	/**
	 * This method is designed to determine market size in terms of dollar.
	 * @return Total dollars in buyingOrders priorty queue.
	 */
	public double totalDollarInBuying() {
		double totalDollar = 0;
		Iterator<BuyingOrder> buyIt = buyingOrders.iterator();
		while(buyIt.hasNext()) {
			BuyingOrder next = buyIt.next();
			totalDollar += next.getAmount()*next.getPrice();
		}
		return totalDollar;
	}
	
	/**
	 * This method is designed to determine market size in terms of PQin.
	 * @return Total PQin in sellingOrders priorty queue.
	 */
	public double totalPQinInSelling() {
		double totalPQin = 0;
		Iterator<SellingOrder> selIt = sellingOrders.iterator();
		while(selIt.hasNext()) {
			totalPQin += selIt.next().getAmount();
		}
		return totalPQin;
	}
}
