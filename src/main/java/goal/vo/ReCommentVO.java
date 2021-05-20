package goal.vo;

import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Alias("reCmtVO")
public class ReCommentVO {
	private int cno, rno;
	private String recmtContent, recmtWriter;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date recmtDate;
	private int profileCheck;
}
