package goal.domain;

public class Criteria {
	private int pageNum, amount;
	
	public Criteria() {
		this(1,10);
	}
	
	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}
	
	public int getPageNum() {
		return pageNum;
	}
	
	public int getAmount() {
		return amount;
	}
}
