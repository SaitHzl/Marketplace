package elements;
/**
 * This abstract class is designed to construct buying orders and selling orders objects in the system.
 * @author sagolinata111
 *
 */
public abstract class Order{
	/**
	 * amount of the order.
	 */
	private double amount;
	/**
	 * price of the order.
	 */
	private double price;
	/**
	 * trader id that wants to give this order.
	 */
	private int traderID;
	/**
	 * This method builds the order according to given parameter.
	 * @param traderID Id of the trader.
	 * @param amount Amount of the order.
	 * @param price Price of the order.
	 */
	public Order(int traderID, double amount, double price) {
		this.traderID = traderID;
		this.amount = amount;
		this.price = price;
	}
	
	/**
	 * getter method for amount.
	 * @return amount
	 */
	public double getAmount() {
		return amount;
	}
	
	/**
	 * setter method for amount.
	 * @param amount
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	/**
	 * getter method for price.
	 * @return price
	 */
	public double getPrice() {
		return price;
	}
	
	/**
	 * setter method for the price.
	 * @param price
	 */
	public void setPrice(double price) {
		this.price = price;
	}
	
	/**
	 * getter method for the trader id.
	 * @return
	 */
	public int getTraderID() {
		return traderID;
	}
	
	/**
	 * setter method for the trader id.
	 * @param traderID
	 */
	public void setTraderID(int traderID) {
		this.traderID = traderID;
	}
	
	
	
	
}