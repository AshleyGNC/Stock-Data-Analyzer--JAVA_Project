# Stock-Data-Analyzer-JAVA_Project
A Simple Stock Data Analyzer

Goals:

● Learn how to choose a data structure for the desired performance.

● Practice with more Java collections, like HashMap and TreeSet.

● Learn how to customize a comparable data structure.

● Learn how to build an in-memory database for data analysis.




Tasks:

In this project, we would create a tool to conduct simple analysis with real-time stock market
data. With the symbol of the New York Stock Exchange stock, one can get its current trading
price by extracting the information from a web page. For example, the current stock price of
Google (NYSE symbol = GOOG) can be extracted from the page:
http://finance.yahoo.com/quote/GOOG.

We would use a Java API, called YahooFinance, to obtain the stock price based on this idea. The
following code snippet shows how to retrieve the current price of GOOG using the API: (More
information about the API can be found here https://financequotes-api.com/ and the library file is
included in the starter code).

Stock stock = YahooFinance.get("GOOG");
BigDecimal price = stock.getQuote().getPrice();

The tool implements an in-memory database that stores real-time market data in the memory to
allow fast retrieval. Each record stored in the database includes the key (i.e., stock symbol) and
the value (i.e., company name and current price).

You would create a myStock class to implement the database and provide the following
functionalities such that users can conduct simple stock data analysis.

● Users can quickly retrieve the current price and company name using the stock symbol.

● Users can quickly insert and update the records.

● Users can quickly retrieve the stock record with top k prices


To achieve these functionalities, your task is to implement the following methods in
myStock.java which is provided in the starter code. :

myStock() // initialize the in-memory database with proper data structures

stockInfo get(String symbol) // get the record using the symbol; runtime is O(1)

void insertOrUpdate(String symbol, stockInfo stock) // insert or update one record; runtime is at least O(log(n))

List<Map.Entry<String, stockInfo>> top(int k) //returns a list of records with top k prices; runtime is O(k)

A list of stocks is provided in the file US-Tech-Symbols.txt where each line contains a stock
symbol and the company name. The real-time price for each stock in the file would be retrieved
using the YahooFinance API, which would be used by you to initialize the database. According
to the test code in myStock.java, the following output is expected (the actual price of the stock
may vary).



The response gives the following correct output:
![CSC17_HW2_Output](https://user-images.githubusercontent.com/68672421/234996693-41a9d912-0f17-4fc8-995a-5853b25a7015.jpg)
