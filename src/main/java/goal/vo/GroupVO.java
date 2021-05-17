package goal.vo;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;

@Data
@Alias("groupVO")
public class GroupVO {
	private int gno, uno;
	private String g_name, g_intro, g_open, g_cate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date g_date, g_goaldate;
	private String file_check, bgi_check;
	private List<GroupUserVO> groupUser;
}
