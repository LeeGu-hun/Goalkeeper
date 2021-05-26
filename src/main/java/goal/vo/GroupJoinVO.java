package goal.vo;

import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Alias("groupJoinVO")
public class GroupJoinVO {
	private int gno, uno;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date join_date;
	private String userId;
	private String join_msg;
	private String userFileCheck;
}
