package goal.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class GroupUserVO {
	private int sno, uno, gno;
	private String g_role;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date g_date;
}
