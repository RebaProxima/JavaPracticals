import java.util.Scanner;
import java.util.StringTokenizer;
/*
 * Total Marks Main Class: 25
 * Compilation & Correct Execution Marks: 10
 */
public class Main {
	static LinkedQueue<Transaction> buyQueue = new LinkedQueue<Transaction>();
	static LinkedQueue<Transaction> sellQueue = new LinkedQueue<Transaction>();
	static int totalGain = 0;
	
	/**
	 * Process queue of transactions - determine if each transaction is a buy or sell
	 * transaction & add it to the appropriate queue
	 * @param transactions - a queue of buy and sell transactions
	 * 15 marks
	 */
	public static void processTransactions(LinkedQueue<String> transactions) {
		//COMPLETE CODE HERE
		
		while(!transactions.isEmpty()) {
			String transaction = transactions.dequeue();
			System.out.println("Transaction" + transaction);
			
			StringTokenizer tokens = new StringTokenizer(transaction);
			String typeOfTransaction = tokens.nextToken();
			int shares = Integer.parseInt(tokens.nextToken());
			int prices = Integer.parseInt(tokens.nextToken());
			
			
			Transaction trans = new Transaction(shares, prices);
			
			if(typeOfTransaction.equals("BUY")) {
				buyQueue.enqueue(trans);
			} else if(typeOfTransaction.equals("SELL")) {
				sellQueue.enqueue(trans);
			}
			
		}
	}
	
	/**
	 * Calculate capital gain(loss)
	 * @return totalGain
	 * 10 marks
	 */
	public static Integer calculateCapitalGainLoss() {
		//COMPLETE CODE HERE
		
		while(!sellQueue.isEmpty()) {
			Transaction selling = sellQueue.dequeue();
			
			int sharesToSell = selling.getQuantity();
			
			int sellingPrice = selling.getUnitPrice();
			
			while(sharesToSell > 0) {
				
				// Check if the buy queue is empty
				if(buyQueue.isEmpty()) {
					return null;
				}
				
				// buying Transaction
				Transaction buying = buyQueue.first();
				
				int sharesToBuy = buying.getQuantity();
				
				int buyingPrice = buying.getUnitPrice();
				
				//Selling shares
				if(sharesToSell >= sharesToBuy) {
					
					totalGain += sharesToBuy*(sellingPrice - buyingPrice);
					
					sharesToSell-= sharesToBuy;
					
					buyQueue.dequeue();
					
				} else {
					
					totalGain+= sharesToSell*(sellingPrice - buyingPrice);
					
					buying.setQuantity(sharesToBuy - sharesToSell);
					
					sharesToSell = 0;
					
				}
			}
		}
		
		return totalGain;

	}
	
	public static void main(String[] args) {
		String response = "";
		Scanner s = new Scanner(System.in);
		LinkedQueue<String> instructionQueue = new LinkedQueue<String>();
		Integer capGainLoss;
		
		while (!response.toLowerCase().equals("quit")){
			System.out.println("Select option: ");
			System.out.println("1) Enter new transaction");
			System.out.println("2) Calculate capital gain or loss");
			System.out.println("or \"quit\" to quit.");
			response = s.nextLine();
			
			switch(response.toLowerCase()){
				case "1": {
					System.out.println("Enter transaction:");
					response = s.nextLine();
					if (!response.equals(""))
						instructionQueue.enqueue(response);
				}
					break;
				case "2": {
					processTransactions(instructionQueue);
					capGainLoss = calculateCapitalGainLoss();
					if (capGainLoss == null)
						System.out.println("Unmatched sell transaction(s).");
					else
						System.out.println("Capital Gain/Loss: "+capGainLoss);
				}
					break;
				case "quit": break;
				default: System.out.println("Incorrect option selected. Please try again.");
			}			
		}
	}
}