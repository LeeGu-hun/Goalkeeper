package goal.vo;

import lombok.Data;

@Data
public class Criteria {
	private int currentPageNo;
	private int recordsPerPage;
	private int pageSize;
	public Criteria() {
		this.currentPageNo = 1;
		this.recordsPerPage = 12;
		this.pageSize = 10;
	}
}
