package goal.vo;

import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Alias("groupUserVO")
public class GroupUserVO {
	private int sno, uno, gno;
	private String g_role, userId;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date g_date;
	private String userFileCheck;
}
