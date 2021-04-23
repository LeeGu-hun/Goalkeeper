package goal.vo;

import java.util.Date;

import lombok.Data;

@Data
public class GroupVO {
	private int gno;
	private String g_name;
	private String g_content;
	private Date g_date;
}
