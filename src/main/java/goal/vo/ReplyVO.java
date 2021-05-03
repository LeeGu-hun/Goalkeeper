package goal.vo;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("replyVO")
public class ReplyVO {
	private String replyWriter, replyContent, replyDate;
	private int bno;
}
