package goal.vo;

import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
@Alias("groupVO")
public class GroupVO {
	private int gno, uno;
	private String g_name, g_intro, g_open, g_cate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date g_date, g_goaldate;
}
