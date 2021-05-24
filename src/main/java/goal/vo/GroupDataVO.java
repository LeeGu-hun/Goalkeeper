package goal.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class GroupDataVO {
	private int gno;
	private int dno;
	private int uno;
	private int data_cnt;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date data_regDate;
	int week_count;
}
