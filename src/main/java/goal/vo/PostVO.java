package goal.vo;


import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("postVO")
public class PostVO {
	
	private String u_id;
	
	private String b_content;
	
	private Date b_date;
	
	private String r_content;
	
}
