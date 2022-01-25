package elements;
/**
 * This class is designed to create trader's wallet objects. Each trader has a wallet to keep dollars, PQins, blocked dollars, and blocked PQins.
 * @author sagolinata111
 *
 */
public class Wallet {
	private double dollars;
	private double coins;
	private double blockedDollars;
	private double blockedCoins;
	/**
	 * This method sets given parameter to the fields.
	 * @param dollars The amount of dollar.
	 * @param coins The amount of coin.
	 */
	public Wallet(double dollars, double coins) {
		this.dollars = dollars;
		this.coins = coins;
		this.blockedCoins = 0;
		this.blockedDollars = 0;
	}
	/**
	 * getter method for dollar.
	 * @return amount of dollars in the wallet.
	 */
	public double getDollars() {
		return dollars;
	}
	/**
	 * getter method for the coins.
	 * @return amount of coins in the wallet.
	 */
	public double getCoins() {
		return coins;
	}
	/**
	 * setter method for dollars.
	 * @param dollars
	 */
	public void setDollars(double dollars) {
		this.dollars = dollars;
	}
	/**
	 * setter method for coins.
	 * @param coins
	 */
	public void setCoins(double coins) {
		this.coins = coins;
	}
	/**
	 * getter method for blocked coins.
	 * @return blockedCoins.
	 */
	public double getBlockedCoins() {
		return blockedCoins;
	}
	/**
	 * getter method for blocked dollars.
	 * @return blockedDollars.
	 */
	public double getBlockedDollars() {
		return blockedDollars;
	}
	/**
	 * setter method for blocked coins.
	 * @param blockedCoins
	 */
	public void setBlockedCoins(double blockedCoins) {
		this.blockedCoins = blockedCoins;
	}
	/**
	 * setter method for blocked dollars.
	 * @param blockedDollars
	 */
	public void setBlockedDollars(double blockedDollars) {
		this.blockedDollars = blockedDollars;
	}

}
