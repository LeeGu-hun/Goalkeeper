package goal.vo;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("postVO")
public class ReplyVO {
	
	private String r_writer;
	
	private String r_content;
	
	private String r_date;
	
	private int bno;
}
