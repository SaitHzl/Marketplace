package elements;
/**
 * This class is designed to keep selling orders objects in the system. SellingOrders class extends and inherits some methods of Order class and implements Comparable method.
 * @author sagolinata111
 *
 */
public class SellingOrder extends Order implements Comparable<SellingOrder>{
	/**
	 *  This constructor takes parameters and builds the selling order objects in the system. 
	 * @param traderID The id of the trader. Each id is unique.
	 * @param amount The amount of the PQin that trader wants to sell.
	 * @param price	The price of the one PQin in terms of dollar that trader wants to take.
	 */
	public SellingOrder(int traderID, double amount, double price) {
		super(traderID, amount, price);
	}

	/**
	 * This method overrides the comparable interface's compareTo method in order to sort the SellingOrder objects in a given queue.
	 * @param e SellingOrder object
	 * @return 1,-1, or 0 accoring to sorting algorithm.
	 */
	public int compareTo(SellingOrder e) {
		if(super.getPrice()>e.getPrice()) {
			return 1;
		}
		if(super.getPrice() == e.getPrice()) {
			if(super.getAmount()< e.getAmount()) {
				return 1;
			}
			if(super.getAmount() > e.getAmount()) {
				return -1;
			}
			if(super.getAmount() == e.getAmount()) {
				if(super.getTraderID() > e.getTraderID()) {
					return 1;
				}
				if(super.getTraderID() < e.getTraderID()) {
					return -1;
				}
				
			}
		}
		if(super.getPrice()<e.getPrice()) {
			return -1;
		}
		return 0;
	}
	
	
}
