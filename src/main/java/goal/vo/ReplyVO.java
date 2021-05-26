package goal.vo;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Alias("replyVO")
public class ReplyVO {
	private int rno;
	private String replyWriter, replyContent;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date replyDate;
	private int bno;
	private int profileCheck;
	private List<ReCommentVO> recmtList;
	private int uno;
}
