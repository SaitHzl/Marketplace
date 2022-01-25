package elements;
/**
 * This class is designed to create transaction objects in the system. 
 * @author sagolinata111
 *
 */
public class Transaction {
	private SellingOrder sellingOrder;
	private BuyingOrder buyingOrder;
	public Transaction(SellingOrder sellingOrder, BuyingOrder buyingOrder) {
		this.buyingOrder = buyingOrder;
		this.sellingOrder = sellingOrder;
	}
	
	
}
