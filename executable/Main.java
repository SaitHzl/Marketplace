package executable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.lang.reflect.Array;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Scanner;

import elements.Market;
import elements.SellingOrder;
import elements.Trader;
/**
 *  This main class is created to read the input file, do the operations, and writing necessary informations on the output file.
 * @author sagolinata111
 *
 */
public class Main{
	public static Random myRandom = new Random();
	/**
	 * Main method triggers the whole project. 
	 * @throws FileNotFoundException Since inputs are given in the format of files, it throws FileNotFoundException to guarantee that file exists.
	 * @param args takes input file and output file's location.
	 */
	public static void main(String[] args) throws FileNotFoundException{
		Scanner in = new Scanner(new File(args[0]));
		PrintStream out = new PrintStream(new File(args[1]));
		myRandom.setSeed(in.nextInt());
		in.nextLine();
		String secondLine = in.nextLine();
		String lineArray[] = secondLine.split(" ");
		Market myMarket = new Market(Integer.valueOf(lineArray[0]));
		Trader.numberOfUsers = Integer.valueOf(lineArray[1]);
		int nOfQueries = Integer.valueOf(lineArray[2]);
		for(int a = 0; a<Trader.numberOfUsers;a++) {
			String userLines = in.nextLine();
			String userArray[] = userLines.split(" ");
			Trader trader = new Trader(Double.valueOf(userArray[0]),Double.valueOf(userArray[1]));
			trader.setId(a);
			myMarket.getTraders().add(trader);
		}
		for(int b = 0; b<nOfQueries;b++) {
			String querieLines = in.nextLine();
			String querieArray[] = querieLines.split(" ");
			if(Integer.valueOf(querieArray[0]) == 10) {
				myMarket.getTraders().get(Integer.valueOf(querieArray[1])).buy(Double.valueOf(querieArray[3]),Double.valueOf(querieArray[2]),myMarket);
			}
			if(Integer.valueOf(querieArray[0]) == 11) {
				if(myMarket.getSellingOrders().size()!=0) {
					myMarket.getTraders().get(Integer.valueOf(querieArray[1])).buy(Double.valueOf(querieArray[2]),myMarket.currentBuyingPrice(),myMarket);
				}
				else {
					myMarket.getTraders().get(Integer.valueOf(querieArray[1])).setnOfInvalidQueries(myMarket.getTraders().get(Integer.valueOf(querieArray[1])).getnOfInvalidQueries()+1);
				}

			}
			if(Integer.valueOf(querieArray[0]) == 20) {
				myMarket.getTraders().get(Integer.valueOf(querieArray[1])).sell(Integer.valueOf(querieArray[3]), Integer.valueOf(querieArray[2]), myMarket);
			}
			if(Integer.valueOf(querieArray[0]) == 21) {
				if(myMarket.getBuyingOrders().size() != 0) {
					myMarket.getTraders().get(Integer.valueOf(querieArray[1])).sell(Integer.valueOf(querieArray[2]), myMarket.currentSellingPrice(), myMarket);
				}
				else {
					myMarket.getTraders().get(Integer.valueOf(querieArray[1])).setnOfInvalidQueries(myMarket.getTraders().get(Integer.valueOf(querieArray[1])).getnOfInvalidQueries()+1);
				}
			}
			if(Integer.valueOf(querieArray[0]) == 3) {
				myMarket.getTraders().get(Integer.valueOf(querieArray[1])).depositToWallet(Double.valueOf(querieArray[2]));
			}
			if(Integer.valueOf(querieArray[0]) == 4) {
				myMarket.getTraders().get(Integer.valueOf(querieArray[1])).withdrawFromWallet(Double.valueOf(querieArray[2]));
			}
			if(Integer.valueOf(querieArray[0]) == 5) {
				out.print("Trader" + " " + myMarket.getTraders().get(Integer.valueOf(querieArray[1])).getId()+ ": ");
				out.printf("%.5f",myMarket.getTraders().get(Integer.valueOf(querieArray[1])).getWallet().getDollars()+myMarket.getTraders().get(Integer.valueOf(querieArray[1])).getWallet().getBlockedDollars());
				out.print("$ ");
				out.printf("%.5f",myMarket.getTraders().get(Integer.valueOf(querieArray[1])).getWallet().getCoins()+myMarket.getTraders().get(Integer.valueOf(querieArray[1])).getWallet().getBlockedCoins());
				out.print("PQ");
				out.println();
			}
			if(Integer.valueOf(querieArray[0]) == 777) {
				for(int c = 0; c<Trader.numberOfUsers;c++) {
					myMarket.getTraders().get(c).getWallet().setCoins(myMarket.getTraders().get(c).getWallet().getCoins()+myRandom.nextDouble()*10);
				}
			}
			if(Integer.valueOf(querieArray[0]) == 666) {
				myMarket.makeOpenMarketOperation(Double.valueOf(querieArray[1]));
			}
			if(Integer.valueOf(querieArray[0]) == 500) {
				out.print("Current market size: ");
				out.printf("%.5f",myMarket.totalDollarInBuying());
				out.print(" ");
				out.printf("%.5f", myMarket.totalPQinInSelling());
				out.println();
			}
			if(Integer.valueOf(querieArray[0]) == 501) {
				out.println("Number of successful transactions: " + myMarket.getTotalnOfSuccesfulTransactions());
			}
			if(Integer.valueOf(querieArray[0]) == 502) {
				out.println("Number of invalid queries: " + myMarket.totalnOfInvalidQueries());
			}
			if(Integer.valueOf(querieArray[0]) == 505) {
				out.print("Current prices: ");
				out.printf("%.5f", myMarket.currentSellingPrice());
				out.print(" ");
				out.printf("%.5f", myMarket.currentBuyingPrice());
				out.print(" ");
				out.printf("%.5f", myMarket.averagePrice());
				out.println();
			}
			if(Integer.valueOf(querieArray[0]) == 555) {
				for(int d = 0; d<Trader.numberOfUsers;d++) {
					out.print("Trader " + d + ": ");
					out.printf("%.5f", myMarket.getTraders().get(d).getWallet().getDollars()+myMarket.getTraders().get(d).getWallet().getBlockedDollars());
					out.print("$");
					out.print(" ");
					out.printf("%.5f", myMarket.getTraders().get(d).getWallet().getBlockedCoins()+myMarket.getTraders().get(d).getWallet().getCoins());
					out.print("PQ");
					out.println();
				}
			}
			
		}
	
}
}

