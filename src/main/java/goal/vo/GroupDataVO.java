package goal.vo;

import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Alias("groupDataVO")
public class GroupDataVO {
	private int mno;
	private int gno;
	private int dno;
	private int uno;
	private int data_cnt;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date data_regDate;
	int week_count;
}
