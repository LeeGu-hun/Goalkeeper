package goal.vo;


import java.util.Date;
import java.util.List;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("postVO")
public class PostVO {
	
	private String u_id, b_content;
	private Date b_date;
	private int bno;
	private List<ReplyVO> replyList;
}
