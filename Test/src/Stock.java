
public class Stock {
	private String tickerSymbol;
	private String companyName;
	private int price;
	private double percentChange;
	private int totalShares;
	private long marketCap;
	
	public Stock (String tickerSymbol, String companyName, int price, int totalShares) {
		this.tickerSymbol = tickerSymbol.toUpperCase();
		this.companyName = companyName;
		this.price = price;
		this.totalShares = totalShares;
		this.percentChange = 0;
		this.marketCap = totalShares * price;
	}
	public void adjustPrice(int change) {
		percentChange = change/price*100;
		price += change;
		
		marketCap = totalShares * price;
	}
	public String getTickerSymbol() {
		return tickerSymbol;
	}
	public double getPercentChange() {
		return percentChange;
	}
	public long getMarketCap() {
		return marketCap;
	}
	
	@Override 
	public String toString() {
		return "Ticker Symbol: "+getTickerSymbol()+"\n"+
				"Company: "+companyName+"\n"+
				"Current Price: $"+price+"(+"+percentChange+"%)"+"\n"+
				"Market Cap: "+getMarketCap();
	}

}
