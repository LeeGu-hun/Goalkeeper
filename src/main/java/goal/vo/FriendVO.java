package goal.vo;

import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Alias("friendVO")
public class FriendVO {
	private int uno, friendNo;
	private String friendId;
	private String userFileCheck;
	private String userBackCheck;
	private int friendCnt;
	private int boardCnt;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date friendBirthdate;
}
