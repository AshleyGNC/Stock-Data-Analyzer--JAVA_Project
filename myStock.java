package hw2;

import java.io.*;
import java.math.*;
import java.util.*;
import yahoofinance.*;

// to store the stock name and price
class stockInfo {
	private String name;
	public BigDecimal price;  
	public stockInfo(String nameIn, BigDecimal priceIn) {
		name = nameIn; price = priceIn;
	}
	public String toString() {return name + " " + price.toString();}
}

//override Tree Set comparator, compare by price value not name.
class myComp implements Comparator<Map.Entry<String, stockInfo>>{
    @Override
    public int compare(Map.Entry<String, stockInfo> e1, Map.Entry<String, stockInfo> e2) {
        return e2.getValue().price.compareTo(e1.getValue().price);
    }
}  

public class myStock {
	public static HashMap<String, stockInfo> stocks;
	public static TreeSet<Map.Entry<String, stockInfo>> topStock;
	
	public myStock() {
		stocks = new HashMap<String, stockInfo>();
		topStock = new TreeSet<Map.Entry<String, stockInfo>> (new myComp());		
	}
		

	public void insertOrUpdate(String symbol, stockInfo stock) {
		stocks.put(symbol, stock);
		for (Map.Entry<String, stockInfo> e : stocks.entrySet()) {
			topStock.add(e);
		}		
	}

	public stockInfo get(String symbol) {
		stockInfo retrieve = stocks.get(symbol);
		return retrieve;
	}

	public List<Map.Entry<String, stockInfo>> top(int k) {
		List<Map.Entry<String, stockInfo>> top10 =  new ArrayList<Map.Entry<String,stockInfo>>();
		
		// Creating an iterator
		Iterator<Map.Entry<String, stockInfo>> value = topStock.iterator();
		
        // Displaying the values after iterating through the set
        for (int i=0; i<k; i++) {
	        top10.add(value.next());  
        }
		return top10;
	}

	public static void main(String[] args) throws IOException {
		// testing code
		myStock techStock = new myStock();
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader("./US-Tech-Symbols.txt"));
			String line = reader.readLine();
			while (line != null) {
				String[] var = line.split(":");
				
				// YahooFinance API is used and make sure the lib files are included in the project build path
				Stock stock = null;
				try {
					stock = YahooFinance.get(var[0]);
				} catch (IOException e) {
					System.out.println("do nothing and skip the invalid stock");
				}
				
				// test the insertOrUpdate method when initializing the database
				if (stock != null && stock.getQuote().getPrice() != null) {
					techStock.insertOrUpdate(var[0], new stockInfo(var[1], stock.getQuote().getPrice()));
				}
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		int i = 1;
		System.out.println("===========Top 10 stocks===========");

		// test the top method
		for (Map.Entry<String, stockInfo> element : techStock.top(10)) {
			System.out.println("[" + i + "]" + element.getKey() + " " + element.getValue());
			i++;

		}

		// test the get method
		System.out.println("===========Stock info retrieval===========");
		System.out.println("VMW" + " " + techStock.get("VMW"));
		System.out.println("BIDU" + " " + techStock.get("BIDU"));
	}
}

 
