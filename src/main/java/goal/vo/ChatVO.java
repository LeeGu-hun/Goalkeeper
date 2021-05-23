package goal.vo;


import org.apache.ibatis.type.Alias;

import lombok.Data;


@Data
@Alias("chatVO")
public class ChatVO {
	private int uno, friendNo;
	private String userId;
}
