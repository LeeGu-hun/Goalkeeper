package goal.vo;

import lombok.Data;

@Data
public class GroupDataVO {
	private int gno;
	private int uno;
	private String hor_data, ver_data;
	public void setGno(int gno2) {
		gno = gno2;
		
	}
}
