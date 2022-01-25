package elements;
/**
 * This class is designed to create trader objects. Traders make transactions such as giving buy order, giving sell order.
 * @author sagolinata111
 *
 */
public class Trader {
	/**
	 * Id of the trader.
	 */
	private int id;
	/**
	 * Wallet of the trader.
	 */
	private Wallet wallet;
	/**
	 * total number of users in the system.
	 */
	public static int numberOfUsers = 0;
	/**
	 * total number of invalid queries.
	 */
	private int nOfInvalidQueries = 0;
	/**
	 * When a trader object is created, it takes parameter and creates wallet object inside the constructor.
	 * @param dollars The amount of dollar in the trader's wallet.
	 * @param coins The amount of PQin in the trader's wallet.
	 */
	public Trader(double dollars, double coins) {
		this.wallet = new Wallet(dollars,coins);
		this.id = 0;
	}
	/**
	 * This method is called when a trader wants to sell some PQins in the market. When this method is called, if trader can satisfy some conditions, the sell order is added to the sellingOrders priority queue in the market.
	 * If trader is not capable of giving sell order, the number of invalid queries is incremented by one.
	 * @param amount The amount of PQin that trader wants to sell.
	 * @param price The price of each PQin.
	 * @param market The market in the system.
	 * @return 0.
	 */
	public int sell(double amount, double price, Market market) {
		if(amount <= wallet.getCoins()) {
			SellingOrder sellOrder = new SellingOrder(id,amount,price);
			wallet.setBlockedCoins(wallet.getBlockedCoins()+amount);
			wallet.setCoins(wallet.getCoins()-amount);
			market.giveSellOrder(sellOrder);
			return 0;
		}
		else {
			this.nOfInvalidQueries+=1;
			return 0;
		}
		
	}
	
	/**
	 * This method is called when a trader wants to buy some PQins in the market. When this method is called, if trader can satisfy some conditions, the buy order is added to the buyingOrders priority queue in the market.
	 * If trader is not capable of giving buy order, the number of invalid queries is incremented by one.
	 * @param amount The amount of PQin that trader wants to buy.
	 * @param price The price of each PQin.
	 * @param market The market in the system.
	 * @return 0.
	 */
	public int buy(double amount, double price, Market market) {
		if(amount*price <= wallet.getDollars()) {
			BuyingOrder buyOrder = new BuyingOrder(id,amount,price);
			wallet.setBlockedDollars(wallet.getBlockedDollars()+(amount*price));
			wallet.setDollars(wallet.getDollars()-(amount*price));
			market.giveBuyOrder(buyOrder);
			return 0;
		}
		else {
			this.nOfInvalidQueries += 1;
			return 0;
		}
	}
	
	/**
	 * When this method is called, the amount of dollar in trader's wallet is incremented by given parameter.
	 * @param amount Amount that trader wants to deposit to wallet.
	 */
	public void depositToWallet(double amount) {
		wallet.setDollars(wallet.getDollars()+amount);
	}
	
	/**
	 * When this method is called, If the parameter amount is less than or equal to trader's dollars, trader withdraw parameter amount from his/her wallet. Otherwise, the number of invalid queries is incremented by one.
	 * @param amount Amount that trader wants to withdraw from wallet.
	 */
	public void withdrawFromWallet(double amount) {
		if(amount <= wallet.getDollars()) {
			wallet.setDollars(wallet.getDollars()-amount);
		}
		else {
			this.nOfInvalidQueries += 1;
		}
	}
	
	/**
	 * getter method for the number of invalid queries.
	 * @return number of invalid queries.
	 */
	public int getnOfInvalidQueries() {
		return nOfInvalidQueries;
	}
	
	/**
	 * getter method for trader's wallet.
	 * @return wallet
	 */
	public Wallet getWallet() {
		return wallet;
	}
	
	/**
	 * getter method for trader's id.
	 * @return id.
	 */
	public int getId() {
		return id;
	}
	/**
	 * setter method for trader's id.
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * Setter method for number of invalid queries.
	 * @param nOfInvalidQueries
	 */
	public void setnOfInvalidQueries(int nOfInvalidQueries) {
		this.nOfInvalidQueries = nOfInvalidQueries;
	}
}
