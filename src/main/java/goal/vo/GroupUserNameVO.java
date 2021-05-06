package goal.vo;

import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Alias("groupUserVO")
public class GroupUserNameVO {
	private int sno;
	private String userId;
	private int gno;
	private String g_role;
	@DateTimeFormat(pattern = "yyyy/mm/dd")
	private Date g_date;
}
