
public abstract class Order implements OrderInterface{
	private int tipRate;
	private int taxRate;
	
	public Order(int tipRate, int taxRate) {
		this.tipRate = tipRate;
		this.taxRate = taxRate;
	}
	
	public int getTipRate() {
		return tipRate;
	}

	public void setTipRate(int tipRate) {
		this.tipRate = tipRate;
	}

	public int getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(int taxRate) {
		this.taxRate = taxRate;
	}

	public abstract double getSubTotal();
	
	public abstract String getMenu();
	
	public abstract double prompUser();
	
	public double getTip() {
		return getSubTotal()*getTipRate();
	}
	
	public double getTax() {
		return getSubTotal()*getTaxRate();
	}
	
	public double getTotal() {
		return getSubTotal() + getTip() + getTax();
	}
}
